package com.example.xprintersdk.xprinter

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.IBinder
import android.util.Log
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import androidx.core.content.ContextCompat
import com.example.xprintersdk.xprinter.Service.XprinterConnectedService
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import net.posprinter.posprinterface.PrinterBinder
import net.posprinter.posprinterface.ProcessData
import net.posprinter.posprinterface.TaskCallback
import net.posprinter.utils.BitmapToByteData
import net.posprinter.utils.DataForSendToPrinterPos80
import net.posprinter.utils.PosPrinterDev
import kotlin.collections.ArrayDeque
import kotlin.coroutines.resume

class xprinterService(mcontext : Context) {
    private var context : Context = mcontext;
    private val usbManager: UsbManager by lazy { context.getSystemService(Context.USB_SERVICE) as UsbManager }
    private val ACTION_USB_PERMISSION = "com.example.xprintersdk.USB_PERMISSION"
    private var usbPermissionReceiver: BroadcastReceiver? = null
    private var pendingUsbPath: String? = null
    private var pendingUsbResult: MethodChannel.Result? = null
    var binder: PrinterBinder? = null
    private val retryScope = CoroutineScope(Dispatchers.IO)
    private val pendingJobQueues = mutableMapOf<String, PendingPrintQueue>()
    private val pendingJobLock = Any()

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
        val posService = Intent(context, XprinterConnectedService::class.java)
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


    fun disconnect(printerKey: String?, result: MethodChannel.Result) {
        val currentBinder = binder ?: run {
            result.success(false)
            return
        }

        if (printerKey.isNullOrBlank()) {
            currentBinder.disconnectAll(object : TaskCallback {
                override fun OnSucceed() { result.success(true) }
                override fun OnFailed() { result.success(false) }
            })
        } else {
            currentBinder.disconnectCurrentPort(printerKey, object : TaskCallback {
                override fun OnSucceed() { result.success(true) }
                override fun OnFailed() { result.success(false) }
            })
        }
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

        currentBinder.checkLinkedState(targetKey, object : TaskCallback {
            override fun OnSucceed() { result.success(true) }
            override fun OnFailed() {
                if(isNetworkKey(targetKey)) {
                    connectNet(targetKey, result)
                }else{
                    connetUSB(targetKey, result)
                }
            }
        })
    }

    private fun isNetworkKey(key: String?): Boolean {
        if (key.isNullOrBlank()) return false
        return key.contains('.') || key.contains(':')
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

        currentBinder.connectNetPort(sanitizedIp, object : TaskCallback {
            override fun OnSucceed() {
                result.success(true)
            }

            override fun OnFailed() {
                result.success(false)
            }
        })



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
        // If we already have permission for the matching device, connect immediately.
        val device = findUsbDeviceForPath(targetPath)
        if (device != null && usbManager.hasPermission(device)) {
            currentBinder.connectUsbPort(context, targetPath, object : TaskCallback {
                override fun OnSucceed() { result.success(true) }
                override fun OnFailed() { result.success(false) }
            })
            return
        }

        // Otherwise request permission and connect automatically after user taps OK.
        pendingUsbPath = targetPath
        pendingUsbResult = result

        if (usbPermissionReceiver == null) {
            usbPermissionReceiver = object : BroadcastReceiver() {
                override fun onReceive(ctx: Context?, intent: Intent?) {

                    if (intent?.action == ACTION_USB_PERMISSION) {
                        val dev: UsbDevice? = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                        val granted = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
                        val path = pendingUsbPath
                        val res = pendingUsbResult

                        // One-shot receiver; unregister immediately
                        try { context.unregisterReceiver(this) } catch (_: Exception) {}
                        usbPermissionReceiver = null
                        Log.e("printer commnected", "onReceive: ${granted}---${path}", )
                        if (path != null) {
                            val b = binder
                            if (b == null) {
                                res?.success(false)
                            } else {

                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(1000)

                                    b.connectUsbPort(context, path, object : TaskCallback {
                                        override fun OnSucceed() { res?.success(true) }
                                        override fun OnFailed() { res?.success(false) }
                                    })
                                }
                            }
                        } else {
                            res?.success(false)
                        }

                        pendingUsbPath = null
                        pendingUsbResult = null
                    }
                }
            }
            ContextCompat.registerReceiver(
                context,
                usbPermissionReceiver,
                IntentFilter(ACTION_USB_PERMISSION),
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
        }

        val flags = try { PendingIntent.FLAG_IMMUTABLE } catch (_: Throwable) { 0 }
        val permissionIntent = PendingIntent.getBroadcast(context, 0, Intent(ACTION_USB_PERMISSION), flags)

        if (device != null) {
            usbManager.requestPermission(device, permissionIntent)
        } else {
            // Fallback: request permission for all connected devices
            usbManager.deviceList.values.forEach { dev ->
                if (!usbManager.hasPermission(dev)) {
                    usbManager.requestPermission(dev, permissionIntent)
                }
            }
        }
    }

    private fun findUsbDeviceForPath(path: String): UsbDevice? {
        try {
            val devices = usbManager.deviceList?.values ?: return null
            return devices.firstOrNull { dev ->
                val name = dev.deviceName ?: return@firstOrNull false
                path == name || path.contains(name) || name.contains(path)
            }
        } catch (_: Throwable) { }
        return null
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

    private fun buildProcessData(printBmp: Bitmap): ProcessData {
        val height = printBmp.height
        return ProcessData {
            val list: MutableList<ByteArray> = ArrayList()
            list.add(DataForSendToPrinterPos80.initializePrinter())
            if (height > 200) {
                val bitmaplist = cutBitmap(200, printBmp)
                if (bitmaplist.isNotEmpty()) {
                    for (bitmap in bitmaplist) {
                        val chunk = bitmap ?: continue
                        list.add(
                            DataForSendToPrinterPos80.printRasterBmp(
                                0,
                                chunk,
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

        val processData = buildProcessData(printBmp)

        currentBinder.writeDataByYouself(targetKey, object : TaskCallback {
            override fun OnSucceed() {
                result.success(true)
            }

            override fun OnFailed() {
                enqueuePendingPrint(targetKey, printBmp)
                result.success(false)
            }

        }, processData)
    }

    private fun enqueuePendingPrint(printerKey: String, bitmap: Bitmap) {
        val queue = synchronized(pendingJobLock) {
            pendingJobQueues.getOrPut(printerKey) { PendingPrintQueue(printerKey) }
        }
        Log.w(TAG, "Queueing pending print job for $printerKey")
        queue.enqueue(PendingPrintJob(printerKey, bitmap))
    }

    private suspend fun ensurePrinterConnected(printerKey: String): Boolean {
        return suspendCancellableCoroutine { continuation ->
            val currentBinder = binder ?: run {
                continuation.resume(false)
                return@suspendCancellableCoroutine
            }

            currentBinder.checkLinkedState(printerKey, object : TaskCallback {
                override fun OnSucceed() {
                    continuation.resume(true)
                }

                override fun OnFailed() {
                    val reconnectCallback = object : TaskCallback {
                        override fun OnSucceed() {
                            continuation.resume(true)
                        }

                        override fun OnFailed() {
                            continuation.resume(false)
                        }
                    }

                    try {
                        if (isNetworkKey(printerKey)) {
                            currentBinder.connectNetPort(printerKey.trim(), reconnectCallback)
                        } else {
                            currentBinder.connectUsbPort(context, printerKey, reconnectCallback)
                        }
                    } catch (t: Throwable) {
                        Log.e(TAG, "Failed to reconnect printer $printerKey", t)
                        continuation.resume(false)
                    }
                }
            })
        }
    }

    private suspend fun trySendPendingJob(job: PendingPrintJob): Boolean {
        val bitmap = job.bitmap
        if (bitmap.isRecycled) {
            Log.w(TAG, "Bitmap already recycled for ${job.printerKey}, dropping pending job")
            return true
        }
        if (!ensurePrinterConnected(job.printerKey)) {
            return false
        }
        val currentBinder = binder ?: return false
        val processData = buildProcessData(bitmap)
        return suspendCancellableCoroutine { continuation ->
            currentBinder.writeDataByYouself(job.printerKey, object : TaskCallback {
                override fun OnSucceed() {
                    Log.i(TAG, "Pending print job sent for ${job.printerKey}")
                    continuation.resume(true)
                }

                override fun OnFailed() {
                    Log.w(TAG, "Pending print job failed for ${job.printerKey}")
                    continuation.resume(false)
                }
            }, processData)
        }
    }

    private fun removeQueue(key: String, queue: PendingPrintQueue) {
        synchronized(pendingJobLock) {
            val existing = pendingJobQueues[key]
            if (existing === queue) {
                pendingJobQueues.remove(key)
            }
        }
    }

    private inner class PendingPrintQueue(private val printerKey: String) {
        private val pendingJobs = ArrayDeque<PendingPrintJob>()
        private var processor: Job? = null

        fun enqueue(job: PendingPrintJob) {
            synchronized(pendingJobs) {
                pendingJobs.addLast(job)
                startProcessorLocked()
            }
        }

        private fun startProcessorLocked() {
            if (processor?.isActive == true) {
                return
            }
            val job = retryScope.launch {
                processJobs()
            }
            processor = job
            job.invokeOnCompletion {
                synchronized(pendingJobs) {
                    processor = null
                    if (pendingJobs.isNotEmpty()) {
                        startProcessorLocked()
                    } else {
                        removeQueue(printerKey, this@PendingPrintQueue)
                    }
                }
            }
        }

        private suspend fun processJobs() {
            while (true) {
                val pendingJob = synchronized(pendingJobs) {
                    if (pendingJobs.isEmpty()) return
                    pendingJobs.first()
                }
                val success = trySendPendingJob(pendingJob)
                if (success) {
                    synchronized(pendingJobs) {
                        if (pendingJobs.isNotEmpty()) {
                            pendingJobs.removeFirst()
                        }
                        if (pendingJobs.isEmpty()) {
                            return
                        }
                    }
                } else {
                    delay(RETRY_DELAY_MS)
                }
            }
        }
    }

    private data class PendingPrintJob(
        val printerKey: String,
        val bitmap: Bitmap,
        val createdAt: Long = System.currentTimeMillis()
    )

    companion object {
        private const val RETRY_DELAY_MS = 4000L
        private const val TAG = "xprinterService"
    }
}
