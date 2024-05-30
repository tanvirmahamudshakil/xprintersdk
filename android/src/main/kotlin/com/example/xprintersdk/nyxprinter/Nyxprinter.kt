package com.example.xprintersdk.nyxprinter

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Bitmap
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import net.nyx.printerclient.Nyxpinter
import net.nyx.printerservice.print.IPrinterService
import net.nyx.printerservice.print.PrintTextFormat
import timber.log.Timber
import java.util.ServiceConfigurationError
import java.util.concurrent.Executors


class Nyxprinter(context : Context) {
    private val singleThreadExecutor = Executors.newSingleThreadExecutor()
    var mContext: Context

    init {
        mContext =  context
    }

    private var printerService: IPrinterService? = null
    private val connService: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            Log.e( "Connection","printer service disconnected, try reconnect")
            printerService = null

        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Timber.d("onServiceConnected: %s", name)
            printerService = IPrinterService.Stub.asInterface(service)
        }
    }


    fun initnyxPrinterService() {
        try {
            val ret = Nyxpinter().getInstance().bindService(
                mContext,
                connService
            )

        } catch (e: ServiceConfigurationError) {
            e.printStackTrace()
        }
    }


    private val qscReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("com.android.NYX_QSC_DATA" == intent.action) {
                val qsc = intent.getStringExtra("qsc")

                printText("qsc-quick-scan-code\n$qsc")
            }
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



    private fun printText(text: String) {
        singleThreadExecutor.submit {
            try {
                val textFormat = PrintTextFormat()
                // textFormat.setTextSize(32);
                // textFormat.setUnderline(true);
                val ret = printerService!!.printText(text, textFormat)

                if (ret == 0) {
                    paperOut()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private fun printBarcode() {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printBarcode("123456789", 300, 160, 1, 1)

                if (ret == 0) {
                    paperOut()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private fun printQrCode() {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printQrCode("123456789", 300, 300, 1)

                if (ret == 0) {
                    paperOut()
                }
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

     fun printBitmap(bitmap : Bitmap) {
        singleThreadExecutor.submit {
            try {
                val ret = printerService!!.printBitmap(
                    bitmap,
                    0,
                    1
                )

                if (ret == 0) {
                    paperOut()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }




}