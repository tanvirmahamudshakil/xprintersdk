package com.example.xprintersdk.PrinterService

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.os.AsyncTask
import android.provider.MediaStore
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.DailyReport.Dailyreport
import com.example.xprintersdk.Model.ProductModel.ProductPrint
import com.example.xprintersdk.Nyxprinter.NyxprinterHelp
import com.example.xprintersdk.Printer80.printer80
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.databinding.DailyreportBinding
import com.example.xprintersdk.databinding.ProductprintBinding
import com.example.xprintersdk.xprinter.Xprinter
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class ProductPrinterService(mcontext: Context, var productPrint: ProductPrint, var businessdata: BusinessSetting, var mserviceBinding: Xprinter, var mresult: MethodChannel.Result, var sunmiHelper : SunmiHelp, var saveImage: Boolean, var nyxp : NyxprinterHelp ,var printer80 : printer80) : AsyncTask<String, Int, Bitmap>() {

     private var context: Context

     init {
         context = mcontext
     }
     private fun getBitmapFromView(view: View): Bitmap {
         var bitmaplist : ArrayList<Bitmap>  = ArrayList<Bitmap>();
         val spec = View.MeasureSpec.makeMeasureSpec(
             0,
             View.MeasureSpec.UNSPECIFIED
         )
         view.measure(spec, spec)
         view.layout(0, 0, view.measuredWidth, view.measuredHeight)

         //Define a bitmap with the same size as the view
         val returnedBitmap = Bitmap.createBitmap(
             view.measuredWidth,
             view.measuredHeight,
             Bitmap.Config.ARGB_8888
         )
         //Bind a canvas to it
         val canvas = Canvas(returnedBitmap)
         //Get the view's background
         val bgDrawable = view.background
         if (bgDrawable != null) {
             //has background drawable, then draw it on the canvas
             bgDrawable.draw(canvas)
         } else {
             //does not have background drawable, then draw white background on the canvas
             canvas.drawColor(Color.WHITE)
         }
         // draw the view on the canvas
         view.draw(canvas)

         var bitmap: Bitmap = if (businessdata.paperSize == 80){
             //create resized image and display
             val maxImageSize = 570f
             val ratio = maxImageSize / returnedBitmap.width
             val width = (ratio * returnedBitmap.width).roundToInt()
//             val height = (ratio * returnedBitmap.height).roundToInt()
             val height = businessdata.singleProductPrintHight ?: 239
             Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
         }else {
             val maxImageSize = 390f
             val ratio = maxImageSize / (returnedBitmap.width)
             val width = (ratio * returnedBitmap.width).roundToInt()
//             val height = (ratio * returnedBitmap.height).roundToInt()
             val height = businessdata.singleProductPrintHight ?: 239
             Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
         }

         return bitmap;
     }

     fun printBitmap(bitmap: Bitmap?)  {
         try {
             if(saveImage) {
                 saveBitmapToGallery(context, bitmap!!, "bitmapImage", "scascas");
             }else if (businessdata.selectPrinter!!.lowercase() == "xprinter"){
                 mserviceBinding.printUSBbitamp(bitmap,mresult);
             }else if (businessdata.selectPrinter!!.lowercase() == "nyxprinter") {
                 nyxp.printBitmap(bitmap!!, mresult)
             }else if (businessdata.selectPrinter!!.lowercase() == "printer80") {
                 if (bitmap != null) {
                     printer80.printBitmap(bitmap)
                     mresult.success(true)
                 };
             } else{
                 sunmiHelper.printBitmap(bitmap, 2, mresult)
             }


         } catch (e: java.lang.Exception) {

         }
     }

     fun saveBitmapToGallery(context: Context, bitmap: Bitmap, title: String, description: String) {
         val values = ContentValues().apply {
             put(MediaStore.Images.Media.TITLE, title)
             put(MediaStore.Images.Media.DESCRIPTION, description)
             put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
         }

         val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

         try {
             if (uri != null) {
                 val outputStream: OutputStream? = context.contentResolver.openOutputStream(uri)
                 if (outputStream != null) {
                     bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                     outputStream.close()
                     Toast.makeText(context, "Image saved to Gallery", Toast.LENGTH_SHORT).show()
                 }
             }
         } catch (e: Exception) {
             e.printStackTrace()
             Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
         }
     }


     override fun doInBackground(vararg params: String?): Bitmap {
         var binding = ProductprintBinding.inflate(LayoutInflater.from(context))
         binding.itemName.text = productPrint.name
         binding.itemName.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdata.singleProductNameFont?.toFloat() ?: 20.0f)
         binding.expire.text = productPrint.expire
         binding.expire.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdata.singleProductExpireFont?.toFloat() ?: 20.0f)

         binding.price.text = productPrint.price
         binding.price.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdata.singleProductPriceFont?.toFloat() ?: 20.0f)

         if(productPrint.barcode != null) {
             var barcodeBitmap = genBarcode(productPrint.barcode!!)
             val imageView = ImageView(context).apply {
                 setImageBitmap(barcodeBitmap)
                 layoutParams = ViewGroup.LayoutParams(
                     ViewGroup.LayoutParams.WRAP_CONTENT,
                     ViewGroup.LayoutParams.WRAP_CONTENT
                 )
             }
             binding.items.removeAllViews()
             binding.items.addView(imageView)
         }
         val bitmaplist: Bitmap =  getBitmapFromView(binding.root)
         return  bitmaplist;

     }

    private fun genBarcode(barcode : String) : Bitmap? {
        var widthd = businessdata.barcode_width ?: 250;
        var heightd = businessdata.barcode_hight ?: 100;

        var bitMatrix: BitMatrix? = null
        bitMatrix = MultiFormatWriter().encode(barcode, BarcodeFormat.CODE_128, widthd, heightd)
        val width = bitMatrix.width
        val height = bitMatrix.getHeight()
        val pixels = IntArray(width * height)
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (bitMatrix[j, i]) {
                    pixels[i * width + j] = -0x1000000
                } else {
                    pixels[i * width + j] = -0x1
                }
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }


     override fun onPostExecute(result: Bitmap?) {
         super.onPostExecute(result)
         printBitmap(result)
     }
 }