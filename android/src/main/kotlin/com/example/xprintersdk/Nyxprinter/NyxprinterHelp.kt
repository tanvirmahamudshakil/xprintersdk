package com.example.xprintersdk.Nyxprinter

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.IBinder
import android.os.RemoteException
import com.sunmi.peripheral.printer.InnerPrinterException
import io.flutter.plugin.common.MethodChannel
import net.nyx.printerclient.Nyxpinter
import net.nyx.printerservice.print.IPrinterService
import timber.log.Timber
import java.util.concurrent.Executors


class NyxprinterHelp(context: Context) {
    var NoNyxPrinter = 0x00000000
    var CheckNyxPrinter = 0x00000001
    var FoundNyxPrinter = 0x00000002
    var LostNyxPrinter = 0x00000003
    lateinit var mContext: Context

    var nyxPrinter = CheckNyxPrinter

    private val singleThreadExecutor = Executors.newSingleThreadExecutor()
    private var printerService: IPrinterService? = null

    init {
        mContext = context
    }

    private val connService: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            printerService = null
            nyxPrinter = LostNyxPrinter
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Timber.d("onServiceConnected: %s", name)
            printerService = IPrinterService.Stub.asInterface(service)
        }
    }

    fun initNyxPrinterService() {
        try {
            val ret = Nyxpinter().getInstance().bindService(
                mContext,
                connService
            )
            if (!ret) {
                nyxPrinter = NoNyxPrinter
            }
        } catch (e: InnerPrinterException) {
            e.printStackTrace()
        }
    }

    fun checkNyxPrinter() : Boolean {
        return if (printerService == null) {
            false;
        } else {
            val status: Int = printerService!!.printerStatus
            status != 0 && status != 505
        }
    }

    private fun paperOut() {
        singleThreadExecutor.submit {
            try {
                printerService!!.paperOut(80)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

     fun printBitmap(bitmap: Bitmap, result : MethodChannel.Result) {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printBitmap(
                    bitmap,
                    0,
                    1
                )

                if (ret == 0) {
                    paperOut()
                    result.success(true);
                }else{
                    result.success(false);
                }
            } catch (e: Exception) {
                e.printStackTrace()
                result.success(false);
            }
        }
    }
}