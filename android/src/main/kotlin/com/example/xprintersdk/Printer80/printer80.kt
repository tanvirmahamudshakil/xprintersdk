package com.example.xprintersdk.Printer80

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import com.printer.sdk.PrinterConstants.Connect
import com.printer.sdk.PrinterConstants.PAlign
import com.printer.sdk.PrinterInstance

class printer80(context: Context) {

    var mPrinter: PrinterInstance? = null
    private var mHandler : Handler?= null
    private var CONNECTED: Boolean = false;
    init {
        val device = GetUsbPathNames(context)
        @SuppressLint("HandlerLeak", "ShowToast")
     if(device != null) mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    Connect.SUCCESS -> {
                        CONNECTED = true;
                        Toast.makeText(context, "Connect successfull", Toast.LENGTH_SHORT).show()
                    }
                    Connect.FAILED -> {
                        CONNECTED = false
                    }
                    Connect.CLOSED -> {
                        CONNECTED = false
                    }
                    Connect.NODEVICE -> {
                        CONNECTED = false
                    }

                }
            }
        }

        if(device != null) mPrinter = PrinterInstance.getPrinterInstance(context, device, mHandler);
        if(device != null)  initPrinter()
    }






    fun initPrinter() {
        mPrinter?.initPrinter()
        connectionOpen()
    }

    fun statusCheck() : Int? {
       return mPrinter?.currentStatus
    }

    fun connectionOpen() {
        mPrinter?.openConnection()
    }

    fun closeConnection() {
        mPrinter?.closeConnection()

    }





    fun printBitmap(bitmap : Bitmap) {
        mPrinter?.printImage(
            bitmap,
            PAlign.NONE,
            0,
            128
        )
        mPrinter?.cutPaper(65, 50)
    }

    fun GetUsbPathNames(context: Context): UsbDevice? {
        var usbNames: MutableList<String?>?
        val usbManager = context.getSystemService("usb") as UsbManager
        if(usbManager.deviceList.isNotEmpty()) {
            val usbList = usbManager.deviceList.values.first()
            return  usbList;
        }else{
            return  null;
        }
    }
}