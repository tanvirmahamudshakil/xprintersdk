package com.example.xprintersdk.PrinterService

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.DailyReport.Dailyreport
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.databinding.DailyreportBinding
import com.example.xprintersdk.xprinter.Xprinter
import com.sunmi.peripheral.printer.InnerResultCallback
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class DailyReportPage(mcontext: Context, report: Dailyreport, businessdata: BusinessSetting, mserviceBinding: Xprinter, mresult: MethodChannel.Result, sunmiHelper : SunmiHelp, saveImage: Boolean) : AsyncTask<String, Int, Bitmap>() {
    private var context: Context
    private  var dailyreport: Dailyreport
    private  var businessname: String
    private  var businessaddress: String
    private  var businessphone: String
    private var fontsize: Int = 30
    private var noofprint: Int =1
    private var businessdatadata: BusinessSetting
    private var serviceBinding: Xprinter
    private var result: MethodChannel.Result
    private var sunmiPrinter : SunmiHelp
    private var bitmapSave: Boolean
    init {
        context = mcontext;
        dailyreport = report;
        serviceBinding = mserviceBinding;
        this.businessname = businessdata.businessname!!;
        this.businessaddress =  businessdata.businessaddress!!;
        this.businessphone =  businessdata.businessphone!!;
        this.fontsize =  businessdata.fontSize!!;
        noofprint = businessdata.printOnCollection!!;
        businessdatadata = businessdata
        result = mresult
        sunmiPrinter = sunmiHelper;
        bitmapSave = saveImage;
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

//        //create resized image and display
//        val maxImageSize = 570f
//        val ratio = maxImageSize / returnedBitmap.width
//        val width = (ratio * returnedBitmap.width).roundToInt()
//        val height = (ratio * returnedBitmap.height).roundToInt()
//        //return the bitmap
//
//        var bitmap = Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
        var bitmap: Bitmap = if (businessdatadata.paperSize == 80){
            //create resized image and display
            val maxImageSize = 570f
            val ratio = maxImageSize / returnedBitmap.width
            val width = (ratio * returnedBitmap.width).roundToInt()
            val height = (ratio * returnedBitmap.height).roundToInt()
            Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
        }else {
            val maxImageSize = 390f
            val ratio = maxImageSize / (returnedBitmap.width)
            val width = (ratio * returnedBitmap.width).roundToInt()
            val height = (ratio * returnedBitmap.height).roundToInt()
            Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
        }

        return bitmap;
    }

    fun printBitmap(bitmap: Bitmap?)  {
        try {
//            val originalBitmap: Bitmap? = bitmap
//            val compressFormat = Bitmap.CompressFormat.JPEG
//            val compressionQuality = 10 // Adjust the quality as needed
//            val compressedData = originalBitmap?.let { compressBitmap(it, compressFormat, compressionQuality) }
//
//            var b2 = resizeImage(byteArrayToBitmap(compressedData!!), 550, true)
            if(bitmapSave) {
                saveBitmapToGallery(context, bitmap!!, "bitmapImage", "scascas");
            }else if (businessdatadata.selectPrinter!!.lowercase() == "xprinter"){
                serviceBinding.printUSBbitamp(bitmap,result);
            }else{
                sunmiPrinter.printBitmap(bitmap, 2, result)
            }


        } catch (e: java.lang.Exception) {

        }
    }

    private fun resizeImage(bitmap: Bitmap?, w: Int, ischecked: Boolean): Bitmap? {
        var resizedBitmap: Bitmap? = null
        val width = bitmap!!.width
        val height = bitmap.height
        if (width == w) {
            return bitmap
        }

        val newHeight = height * w / width
        val scaleWidth = w.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        resizedBitmap = Bitmap.createBitmap(
            bitmap,
            0,
            0,
            width,
            height,
            matrix,
            true
        )
        return resizedBitmap
    }


    fun compressBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, quality, stream)
        return stream.toByteArray()
    }


    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
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
        var binding = DailyreportBinding.inflate(LayoutInflater.from(context))
        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a")
        val totalCashOrderAmount = String.format("%.2f", dailyreport.data!!.totalCashOrderAmount)
        val totalCardOrderAmount = String.format("%.2f", dailyreport.data!!.totalCardOrderAmount)

        binding.reportDate.text = "Date: ${formatter.format(parser.parse(dailyreport.data!!.date)!!)}"
        binding.reportDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalOrderBox.text = dailyreport.data!!.totalOrder.toString()
        binding.totalOrderBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.onlineOrderBox.text = dailyreport.data!!.totalOnlineOrder.toString()
        binding.onlineOrderBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.onlineorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.noofCardPaymentBox.text = dailyreport.data!!.totalCardOrder.toString()
        binding.noofCardPaymentBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.cardpayment.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.noofcashPaymentBox.text = dailyreport.data!!.totalCashOrder.toString()
        binding.noofcashPaymentBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.cashpayment.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        var totalPayment = totalCashOrderAmount.toDouble() + totalCardOrderAmount.toDouble()
        binding.totalPaymentReceiveBox.text = String.format("%.2f", totalPayment) ;
        binding.totalPaymentReceiveBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalpayment.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalCardBox.text = totalCardOrderAmount
        binding.totalCardBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalcard.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalCashBox.text = totalCashOrderAmount
        binding.totalCashBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        binding.totalcash.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize!!.toFloat())
        val bitmaplist: Bitmap =  getBitmapFromView(binding.root)
        return  bitmaplist;

    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        printBitmap(result)
    }
}