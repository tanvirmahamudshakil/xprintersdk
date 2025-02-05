package com.example.xprintersdk.Barcode

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File

import java.nio.file.Files
import java.nio.file.Paths

import java.util.EnumMap

class BarcodeData {
//    fun generateBarcodeImage(
//        barcodeText: String,
//        format: BarcodeFormat = BarcodeFormat.CODE_128,
//        width: Int = 300,
//        height: Int = 100,
//        filePath: String = "barcode.png"
//    ) {
//        val hints = EnumMap<EncodeHintType, Any>(EncodeHintType::class.java)
//        hints[EncodeHintType.MARGIN] = 1 // Set margin (optional)
//
//        val bitMatrix: BitMatrix = MultiFormatWriter().encode(barcodeText, format, width, height, hints)
//        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", File(filePath).toPath())
//    }
//
//    fun printBarcodeImage(filePath: String) {
//        val imageBytes = Files.readAllBytes(Paths.get(filePath))
//        // Send imageBytes to the printer using the appropriate printer SDK or ESC/POS commands
//        // This part depends on the specific printer and library you are using.
//    }


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