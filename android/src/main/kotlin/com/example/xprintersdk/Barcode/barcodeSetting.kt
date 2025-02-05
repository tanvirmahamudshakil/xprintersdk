package com.example.xprintersdk.Barcode

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class barcodeSetting {
    fun generateBarcode(content: String,dpi: Int = 203, width: Int, height: Int): Bitmap? {
        return try {
            val widthPx = (width * dpi / 25.4f).toInt() // 57mm → 455px
            val heightPx = (height * dpi / 25.4f).toInt() // 63mm → 503px

            // Apply a scale factor for better resolution (2x or 3x recommended)
            val scaleFactor = 1f
            val scaledWidthPx = (widthPx * scaleFactor).toInt()  // 455 * 3 = 1365px
            val scaledHeightPx = (heightPx * scaleFactor).toInt() // 503 * 3 = 1509px
            val bitMatrix: BitMatrix = MultiFormatWriter().encode(
                content, BarcodeFormat.CODE_128, scaledWidthPx, scaledHeightPx
            )
            val barcodeEncoder = BarcodeEncoder()
            barcodeEncoder.createBitmap(bitMatrix)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}