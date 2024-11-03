package com.example.xprintersdk.LabelPrinter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import net.posprinter.IConnectListener
import net.posprinter.IDeviceConnection
import net.posprinter.POSConnect
import io.flutter.plugin.common.MethodChannel.Result
import net.posprinter.TSPLConst
import net.posprinter.TSPLPrinter
import net.posprinter.model.AlgorithmType

class LabelPrinter(context: Context) {
    var mContext : Context = context;
    var curConnect: IDeviceConnection? = null


    init {
        initalize()
    }
    fun initalize() {
        POSConnect.init(mContext)
    }



    fun connectUSB(pathName: String,result : Result) {
        curConnect?.close()
        curConnect = POSConnect.createDevice(POSConnect.DEVICE_TYPE_USB)
        curConnect!!.connect(pathName) { p0, p1, p2 ->
            if (POSConnect.CONNECT_SUCCESS == p0) {

                result.success(true)
            } else {

                result.success(false)
            }

        }
    }


    fun connectSerial(port: String, boudrate: String, result : Result) {
        curConnect?.close()
        curConnect = POSConnect.createDevice(POSConnect.DEVICE_TYPE_SERIAL)
        curConnect!!.connect("$port,$boudrate") { p0, p1, p2 ->
            if (POSConnect.CONNECT_SUCCESS == p0) {
                result.success(true)
            } else {
                result.success(false)
            }
        }
    }

    fun connectNet(ipAddress: String, result : Result) {
        curConnect?.close()
        curConnect = POSConnect.createDevice(POSConnect.DEVICE_TYPE_ETHERNET)
        curConnect!!.connect(ipAddress){ p0, p1, p2 ->
            if (POSConnect.CONNECT_SUCCESS == p0) {
                result.success(true)
            } else {
                result.success(false)
            }
        }
    }

    fun getAllSerialPort() : List<String> {
        val entries = POSConnect.getSerialPort()
        return entries;
    }

    fun searchUsb(): List<String> {
        val usbNames = POSConnect.getUsbDevices(mContext)
//        var ret = ""
//        if (usbNames.isNotEmpty()) {
//            ret = usbNames[0]
//        }
        return usbNames
    }

    fun printerconnectCheck(result: Result) {
        if(curConnect != null) {
            var printer = TSPLPrinter(curConnect)
            printer.isConnect{
                if (POSConnect.CONNECT_SUCCESS == it) {
                    result.success(true);
                }else{
                    result.success(false);
                }
                Log.e("label printer connect", "printerconnectCheck: ${it}", )
            }
        }else{
            result.success(false);
        }

    }





    fun printPicCode(b: Bitmap, result: Result, width: Double, height: Double) {
//        if(curConnect != null) {
//            var printer = TSPLPrinter(curConnect)
//            printer.isConnect { p0 ->
//                if (POSConnect.CONNECT_SUCCESS == p0) {
//                    printer.sizeMm(width, height)
//                        .cls()
//                        .bitmap(0, 0, TSPLConst.BMP_MODE_OVERWRITE, 600, b, AlgorithmType.Threshold)
//                        .print(1)
//                    result.success(true);
//                }else{
//                    Toast.makeText(mContext, "printer not connect", Toast.LENGTH_SHORT).show()
//                    result.success(false);
//                }
//            }
//        }else{
//            result.success(false);
//        }
    result.success(true);


    }

    fun printBarcode(result: Result) {
        var printer = TSPLPrinter(curConnect)
        printer.sizeMm(60.0, 30.0)
            .gapMm(0.0, 0.0)
            .cls()
            .barcode(60, 50, TSPLConst.CODE_TYPE_128, 108, "abcdef12345")
            .print()
        result.success(true)

    }

     fun printContent(barcode: String, price: String, net: String,total: String, expire: String) {
        var printer = TSPLPrinter(curConnect)
        printer.sizeMm(60.0, 30.0)
            .gapInch(0.0, 0.0)
            .cls()
            .text(30, 10, TSPLConst.FNT_16_24, TSPLConst.ROTATION_0, 1, 1, price)
            .text(200, 10, TSPLConst.FNT_16_24, TSPLConst.ROTATION_0, 1, 1, net)
            .barcode(30, 20, TSPLConst.CODE_TYPE_93, 70, TSPLConst.READABLE_LEFT, TSPLConst.ROTATION_0, 2, 2, barcode)
            .text(30, 185, TSPLConst.FNT_16_24, TSPLConst.ROTATION_0, 1, 1, total)
            .text(200, 185, TSPLConst.FNT_16_24, TSPLConst.ROTATION_0, 1, 1, expire)
            .print(1)
    }



}