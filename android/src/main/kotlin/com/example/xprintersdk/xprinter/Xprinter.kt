package com.example.xprintersdk.xprinter

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.IBinder
import android.util.Log
import io.flutter.plugin.common.MethodChannel
import net.posprinter.posprinterface.PrinterBinder
import net.posprinter.posprinterface.ProcessData
import net.posprinter.posprinterface.TaskCallback
import net.posprinter.service.PrinterConnectionsService
import net.posprinter.utils.BitmapToByteData
import net.posprinter.utils.DataForSendToPrinterPos80
import net.posprinter.utils.PosPrinterDev

class Xprinter(mcontext : Context) {
    private var context : Context;
    init {
        context = mcontext
    }
    var binder: PrinterBinder? = null
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
        currentBinder.checkLinkedState(targetKey, object : TaskCallback {
            override fun OnSucceed() {
                result.success(true);
            }
            override fun OnFailed() {
                result.success(false);
            }
        })
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
//                lastConnectedPrinterKey = sanitizedIp
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

        currentBinder.connectUsbPort(context, targetPath, object : TaskCallback {
            override fun OnSucceed() {
//                lastConnectedPrinterKey = targetPath
                result.success(true);
            }
            override fun OnFailed() {
                result.success(false);
            }
        })
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
        val processData = object : ProcessData {
            override fun processDataBeforeSend(): MutableList<ByteArray> {
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
                return list
            }
        }

        currentBinder.writeDataByYouself(targetKey, object : TaskCallback {
            override fun OnSucceed() {
                result.success(true)
            }

            override fun OnFailed() {
                result.success(false)
            }
        }, processData)
    }
}
