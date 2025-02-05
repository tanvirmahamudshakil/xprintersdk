package com.example.xprintersdk.Bitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.view.View

class bitmapSetting {
    fun labelprinterBitmap(view: View, dpi: Int = 203, widthMm: Int = 76, heightMm: Int = 76): Bitmap {
        // Convert mm to pixels
        val widthPx = (widthMm * dpi / 25.4f).toInt() // 57mm → 455px
        val heightPx = (heightMm * dpi / 25.4f).toInt() // 63mm → 503px

        // Apply a scale factor for better resolution (2x or 3x recommended)
        val scaleFactor = 1f
        val scaledWidthPx = (widthPx * scaleFactor).toInt()  // 455 * 3 = 1365px
        val scaledHeightPx = (heightPx * scaleFactor).toInt() // 503 * 3 = 1509px

        // Measure and layout the view at higher resolution
        val specWidth = View.MeasureSpec.makeMeasureSpec(scaledWidthPx, View.MeasureSpec.EXACTLY)
        val specHeight = View.MeasureSpec.makeMeasureSpec(scaledHeightPx, View.MeasureSpec.EXACTLY)
        view.measure(specWidth, specHeight)
        view.layout(0, 0, scaledWidthPx, scaledHeightPx)

        // Create a high-resolution bitmap
        val bitmap = Bitmap.createBitmap(scaledWidthPx, scaledHeightPx, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Enable Anti-Aliasing and Filtering for smooth rendering
        canvas.setDrawFilter(PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG))

        // Center the view on the canvas
        val offsetX = (scaledWidthPx - view.measuredWidth) / 2
        val offsetY = (scaledHeightPx - view.measuredHeight) / 2
        canvas.translate(offsetX.toFloat(), offsetY.toFloat())

        // Draw the background and the view
        view.background?.draw(canvas) ?: canvas.drawColor(Color.WHITE)
        view.draw(canvas)

        // Scale down to the original print size (455px x 503px)
        val finalBitmap = Bitmap.createScaledBitmap(bitmap, widthPx, heightPx, true)

        return finalBitmap
    }
}