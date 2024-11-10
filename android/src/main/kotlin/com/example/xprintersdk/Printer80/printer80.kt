package com.example.xprintersdk.Printer80

import android.graphics.Bitmap
import com.printer.sdk.PrinterConstants.PAlign
import com.printer.sdk.PrinterInstance

class printer80 {

    var mPrinter: PrinterInstance? = null
    init {
        mPrinter = PrinterInstance.mPrinter
        initPrinter()
    }



    fun initPrinter() {
        mPrinter?.initPrinter()
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
    }
}