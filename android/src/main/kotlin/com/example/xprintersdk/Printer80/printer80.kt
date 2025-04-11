package com.example.xprintersdk.Printer80
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
import com.printer.sdk.usb.USBPort

class printer80(context: Context) {
    var mPrinter: PrinterInstance? = null
    private var mHandler : Handler?= null
    private var CONNECTED: Boolean = false;
    var mcontext : Context = context;

    init {
        val device = GetUsbPathNames(mcontext)

        if(device != null) {

            mHandler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    when (msg.what) {
                        Connect.SUCCESS -> {
                            CONNECTED = true;
                            Toast.makeText(mcontext, "Connect successfull", Toast.LENGTH_SHORT).show()
                        }
                        Connect.FAILED -> {
                            Toast.makeText(mcontext, "Connect Faild", Toast.LENGTH_SHORT).show()
                            CONNECTED = false
                        }
                        Connect.CLOSED -> {
                            Toast.makeText(mcontext, "Connect Closed", Toast.LENGTH_SHORT).show()
                            CONNECTED = false
                        }
                        Connect.NODEVICE -> {
                            Toast.makeText(mcontext, "No Device Found", Toast.LENGTH_SHORT).show()
                            CONNECTED = false
                        }
                    }
                }
            }

            mPrinter = PrinterInstance.getPrinterInstance(mcontext, device, mHandler);
            connectedPrinter()
        }
    }



    fun connectedPrinter() {
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
        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val devices: HashMap<String, UsbDevice> = usbManager.getDeviceList()
        if(devices.isNotEmpty()) {
            val usbList = devices.values.find { USBPort.isUsbPrinter(it) }
            return  usbList;
        }else{
            return  null;
        }
    }
}