package com.example.xprintersdk.xprinter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import io.flutter.plugin.common.MethodChannel
import net.posprinter.posprinterface.PrinterBinder
import net.posprinter.posprinterface.ProcessData
import net.posprinter.posprinterface.TaskCallback
import net.posprinter.service.PrinterConnectionsService
import net.posprinter.utils.BitmapToByteData
import net.posprinter.utils.DataForSendToPrinterPos80
import net.posprinter.utils.PosPrinterDev
import android.os.Handler
import android.os.Looper

class Xprinter(mcontext : Context) {
    private var context : Context = mcontext;
    var binder: PrinterBinder? = null
    // Track last successful network use to detect stale/idle sockets
    private val lastNetUse: MutableMap<String, Long> = HashMap()
    private val netIdleReconnectMs: Long = 20_000 // reconnect if idle > 20s
    private val mainHandler: Handler = Handler(Looper.getMainLooper())

    private data class PrinterJob(
        val key: String,
        val data: ProcessData,
        val result: MethodChannel.Result
    )

    private val pendingJobs: MutableList<PrinterJob> = mutableListOf()
    private val flushingKeys: MutableSet<String> = mutableSetOf()
//    private var lastConnectedPrinterKey: String? = null
    var conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            binder = iBinder as PrinterBinder
            Log.e("binder", "connected")
        }
        override fun onServiceDisconnected(componentName: ComponentName) {
            Log.e("disbinder", "disconnected")
            binder = null
        }
    }

    fun initBinding() {

        val posService = Intent(context, PrinterConnectionsService::class.java)
        context.bindService(posService, conn, Context.BIND_AUTO_CREATE)

    }

    fun disposeBinding(result: MethodChannel.Result) {
        val currentBinder = binder ?: run {
            result.success(false)
            return
        }
        currentBinder.disconnectAll(object : TaskCallback {
            override fun OnSucceed() {
                result.success(true)
            }
            override fun OnFailed() {
                result.success(false)
            }
        })
    }

    fun checkConnection(printerKey: String?, result: MethodChannel.Result) {
        val currentBinder = binder ?: run {
            result.success(false)
            return
        }
        val targetKey = printerKey
        if (targetKey.isNullOrBlank()) {
            result.success(false)
            return
        }

        var connection = currentBinder.isConnect(targetKey)
        result.success(connection);
//        currentBinder.checkLinkedState(targetKey, object : TaskCallback {
//            override fun OnSucceed() {
//                result.success(true);
//            }
//            override fun OnFailed() {
//                result.success(false);
//            }
//        })
    }

    fun connectNet(ipAddress: String?, result: MethodChannel.Result) {
        val sanitizedIp = ipAddress?.trim()
        val currentBinder = binder ?: run {
            result.success(false)
            return
        }
        if (sanitizedIp.isNullOrEmpty()) {
            result.success(false)
            return
        }
        currentBinder.clearBuffer(sanitizedIp)


        var conncted = currentBinder.isConnect(sanitizedIp)

        if(conncted){
            lastNetUse[sanitizedIp] = System.currentTimeMillis()
            result.success(true)
        }else{
            currentBinder.connectNetPort(sanitizedIp, object : TaskCallback {
                override fun OnSucceed() {
//                lastConnectedPrinterKey = sanitizedIp
                    lastNetUse[sanitizedIp] = System.currentTimeMillis()
                    // After successful connect, try flushing any queued jobs for this IP
                    flushPendingForKey(sanitizedIp, currentBinder)
                    result.success(true)
                }

                override fun OnFailed() {
                    result.success(false)
                }
            })
        }

    }

    fun connetUSB(preferredPath: String?, result: MethodChannel.Result) {
        val currentBinder = binder ?: run {
            result.success(false)
            return
        }

        val usbList = PosPrinterDev.GetUsbPathNames(context)
        val targetPath = preferredPath?.takeIf { it.isNotBlank() } ?: usbList?.firstOrNull()

        if (targetPath.isNullOrBlank()) {
            result.success(false)
            return
        }

        var conncted = currentBinder.isConnect(targetPath)
        if(conncted){
            result.success(true);
        }else{
            currentBinder.connectUsbPort(context, targetPath, object : TaskCallback {
                override fun OnSucceed() {
                    result.success(true);
                }
                override fun OnFailed() {
                    result.success(false);
                }
            })
        }


    }

    fun availableUsbDevices(): List<String>? {
        return PosPrinterDev.GetUsbPathNames(context)
    }

    fun getDefaultPrinterKey(): String? = if(availableUsbDevices().isNullOrEmpty()) null else availableUsbDevices()?.first()

    private fun cutBitmap(h: Int, bitmap: Bitmap?): List<Bitmap?> {
        val width = bitmap!!.width
        val height = bitmap.height
        val full = height % h == 0
        val n = if (height % h == 0) height / h else height / h + 1
        var b: Bitmap?
        val bitmaps: MutableList<Bitmap?> = ArrayList()
        for (i in 0 until n) {
            b = if (full) {
                Bitmap.createBitmap(bitmap, 0, i * h, width, h)
            } else {
                if (i == n - 1) {
                    Bitmap.createBitmap(bitmap, 0, i * h, width, height - i * h)
                } else {
                    Bitmap.createBitmap(bitmap, 0, i * h, width, h)
                }
            }
            bitmaps.add(b)
        }
        return bitmaps
    }


    fun printBitmap(printerKey: String?, printBmp: Bitmap?, result: MethodChannel.Result) {
        val targetKey = printerKey
        val currentBinder = binder ?: run {
            result.success(false)
            return
        }

        if (targetKey.isNullOrBlank() || printBmp == null) {
            result.success(false)
            return
        }

        val height = printBmp.height
        val processData = ProcessData {
            val list: MutableList<ByteArray> = ArrayList()
            list.add(DataForSendToPrinterPos80.initializePrinter())
            if (height > 200) {
                val bitmaplist = cutBitmap(200, printBmp)
                if (bitmaplist.isNotEmpty()) {
                    for (bitmap in bitmaplist) {
                        list.add(
                            DataForSendToPrinterPos80.printRasterBmp(
                                0,
                                bitmap,
                                BitmapToByteData.BmpType.Threshold,
                                BitmapToByteData.AlignType.Center,
                                576
                            )
                        )
                    }
                }
            } else {
                list.add(
                    DataForSendToPrinterPos80.printRasterBmp(
                        0,
                        printBmp,
                        BitmapToByteData.BmpType.Threshold,
                        BitmapToByteData.AlignType.Center,
                        600
                    )
                )
            }

            list.add(DataForSendToPrinterPos80.printAndFeedForward(2))
            list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(66, 1))
            list
        }

        val job = PrinterJob(targetKey, processData, result)
        if (isNetworkKey(targetKey)) {
            // If it's a network printer, enqueue and ensure connectivity; it will auto flush after connect
            enqueueAndMaybeSend(job, currentBinder)
        } else {
            // USB: direct write; if fail, retry once
            writeOnce(job, currentBinder) { ok ->
                if (ok) {
                    result.success(true)
                } else {
                    writeOnce(job, currentBinder) { ok2 -> result.success(ok2) }
                }
            }
        }
    }

    private fun isNetworkKey(key: String): Boolean {
        // Heuristic: IP or host (contains '.' or ':') vs USB path
        return key.contains('.') || key.contains(':')
    }

    private fun ensureNetConnected(
        key: String,
        currentBinder: PrinterBinder,
        force: Boolean = false,
        cb: (Boolean) -> Unit
    ) {
        val now = System.currentTimeMillis()
        val last = lastNetUse[key] ?: 0L
        val stale = now - last > netIdleReconnectMs
        val connected = currentBinder.isConnect(key)

        if (!connected || force || stale) {
            currentBinder.clearBuffer(key)
            currentBinder.connectNetPort(key, object : TaskCallback {
                override fun OnSucceed() {
                    lastNetUse[key] = System.currentTimeMillis()
                    // flush queued jobs for this key upon (re)connect
                    flushPendingForKey(key, currentBinder)
                    cb(true)
                }

                override fun OnFailed() {
                    cb(false)
                }
            })
        } else {
            cb(true)
        }
    }

    private fun enqueueAndMaybeSend(
        job: PrinterJob,
        currentBinder: PrinterBinder
    ) {
        // If not connected (or stale), try to reconnect and queue job; it will auto flush on success
        ensureNetConnected(job.key, currentBinder) { ready ->
            if (ready) {
                // Connected now: attempt to send immediately
                sendWithRetry(job, currentBinder)
            } else {
                synchronized(pendingJobs) { pendingJobs.add(job) }
                // Optionally try once more after short delay to auto recover
                mainHandler.postDelayed({
                    ensureNetConnected(job.key, currentBinder) { r2 ->
                        if (r2) flushPendingForKey(job.key, currentBinder)
                    }
                }, 2000)
            }
        }
    }

    private fun sendWithRetry(
        job: PrinterJob,
        currentBinder: PrinterBinder
    ) {
        writeOnce(job, currentBinder) { ok ->
            if (ok) {
                job.result.success(true)
            } else {
                // Force reconnect then retry exactly once
                ensureNetConnected(job.key, currentBinder, force = true) { ready2 ->
                    if (!ready2) {
                        job.result.success(false)
                    } else {
                        writeOnce(job, currentBinder) { ok2 -> job.result.success(ok2) }
                    }
                }
            }
        }
    }

    private fun flushPendingForKey(key: String, currentBinder: PrinterBinder) {
        if (flushingKeys.contains(key)) return
        flushingKeys.add(key)
        fun next() {
            val job: PrinterJob? = synchronized(pendingJobs) {
                val idx = pendingJobs.indexOfFirst { it.key == key }
                if (idx >= 0) pendingJobs.removeAt(idx) else null
            }
            if (job == null) {
                flushingKeys.remove(key)
                return
            }
            sendWithRetry(job, currentBinder)
            // Chain to next after small delay to avoid flooding
            mainHandler.postDelayed({ next() }, 200)
        }
        next()
    }

    private fun writeOnce(
        job: PrinterJob,
        currentBinder: PrinterBinder,
        onDone: (Boolean) -> Unit
    ) {
        currentBinder.clearBuffer(job.key)
        currentBinder.writeDataByYouself(job.key, object : TaskCallback {
            override fun OnSucceed() {
                if (isNetworkKey(job.key)) {
                    lastNetUse[job.key] = System.currentTimeMillis()
                }
                Toast.makeText(context, "successfull print ${job.key}--connect -> ${currentBinder.isConnect(job.key)}" ,Toast.LENGTH_SHORT).show()
                onDone(true)
            }

            override fun OnFailed() {
                Toast.makeText(context, "failed print ${job.key}--connect -> ${currentBinder.isConnect(job.key)}" ,Toast.LENGTH_SHORT).show()
                onDone(false)
            }
        }, job.data)
    }
}
