package com.example.xprintersdk.PrinterService

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
import android.widget.Toast
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.DailyReport.Dailyreport
import com.example.xprintersdk.Nyxprinter.NyxprinterHelp
import com.example.xprintersdk.Printer80.printer80
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.databinding.DailyreportBinding
import com.example.xprintersdk.xprinter.PrinterIdentifierResolver
import com.example.xprintersdk.xprinter.xprinterService
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class DailyReportPage(mcontext: Context, report: Dailyreport, businessdata: BusinessSetting, mserviceBinding: xprinterService, mresult: MethodChannel.Result, sunmiHelper : SunmiHelp, saveImage: Boolean, nyxp : NyxprinterHelp, printer80D : printer80) : AsyncTask<String, Int, Bitmap>() {
    private var context: Context
    private  var dailyreport: Dailyreport
    private  var businessname: String
    private  var businessaddress: String
    private  var businessphone: String
    private var fontsize: Int = 30
    private var noofprint: Int =1
    private var businessdatadata: BusinessSetting
    private var serviceBinding: xprinterService
    private var result: MethodChannel.Result
    private var sunmiPrinter : SunmiHelp
    private var bitmapSave: Boolean
    private var nyxprinter : NyxprinterHelp
    private var printer80: printer80
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
        printer80 = printer80D
        this.nyxprinter = nyxp
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
            if(bitmapSave) {
                saveBitmapToGallery(context, bitmap!!, "bitmapImage", "scascas");
            }else if (businessdatadata.selectPrinter!!.lowercase() == "xprinter"){
                val printerKey = PrinterIdentifierResolver.resolve(businessdatadata) ?: serviceBinding.getDefaultPrinterKey()
                serviceBinding.printBitmap(printerKey, bitmap,result);
            }else if (businessdatadata.selectPrinter!!.lowercase() == "nyxprinter") {
                nyxprinter.printBitmap(bitmap!!, result)
            }else if (businessdatadata.selectPrinter!!.lowercase() == "printer80") {
                if (bitmap != null) {
                    printer80.printBitmap(bitmap)
                    result.success(true)
                };
            } else{
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
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val totalCashOrderAmount = String.format("%.2f", dailyreport.data?.totalCashOrderAmount)
        val totalCardOrderAmount = String.format("%.2f", dailyreport.data?.totalCardOrderAmount)
        val totalBankOrderAmount = String.format("%.2f", dailyreport.data?.totalBankOrderAmount ?: 0.0)

        val totalRefundCashAmount = dailyreport.data?.totalrefundcashAmount
     //   var totalChangeAmount = dailyreport.data?.totalChange
       // val totalRefundCardAmount = dailyreport.data?.totalrefundcardAmount

        binding.reportDate.text = "Date: ${formatter.format(parser.parse(dailyreport.data?.date))}"
        binding.reportDate.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalOrderBox.text = dailyreport.data?.totalOrder.toString()
        binding.totalOrderBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.onlineOrderBox.text = dailyreport.data?.totalOnlineOrder.toString()
        binding.onlineOrderBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.onlineorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)

        binding.localOrderBox.text = dailyreport.data?.totalLocalOrder.toString()
        binding.localOrderBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.localorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)

        // refund order
        binding.refundOrderBox.text = dailyreport.data?.totalRefund.toString()
        binding.refundOrderBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.refundorder.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)


        // epos cash
        binding.EposCashAmount.text = "£ "+ dailyreport.data?.epos_cash_amount.toString()
        binding.EposCashAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.eposcash.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)


        // epos card
        binding.eposCardAmount.text = "£ "+ dailyreport.data?.epos_card_amount.toString()
        binding.eposCardAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.eposcard.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)




        // online card
        binding.OnlineCardAmount.text = "£ "+ dailyreport.data?.online_card_amount.toString()
        binding.OnlineCardAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.OnlineCard.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)


        // online cash
        binding.OnlineCashAmount.text ="£ "+ dailyreport.data?.online_cash_amount.toString()
        binding.OnlineCashAmount.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.Onlinecash.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)



//        binding.noofCardPaymentBox.text = dailyreport.data?.totalCardOrder.toString()
//        binding.noofCardPaymentBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//        binding.cardpayment.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//
//        binding.noofcashPaymentBox.text = dailyreport.data?.totalCashOrder.toString()
//        binding.noofcashPaymentBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//        binding.cashpayment.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)




        var totalPayment = totalCashOrderAmount.toDouble() + totalCardOrderAmount.toDouble() + totalBankOrderAmount.toDouble()
        binding.totalPaymentReceiveBox.text = "£ " + String.format("%.2f", totalPayment) ;
        binding.totalPaymentReceiveBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalpayment.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)

        // total refund amount
        var totalRefundAmount = totalRefundCashAmount?.toDouble() ?: 0.0
        binding.totalRefundReceiveBox.text = "£ " + String.format("%.2f", totalRefundAmount) ;
        binding.totalRefundReceiveBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalRefund.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)


        // total Change amount
 //       var totalChange = totalChangeAmount?.toDouble() ?: 0.0
//        binding.totalChangeBox.text = "£ " + String.format("%.2f", totalChange) ;
//        binding.totalChangeBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//        binding.totalChange.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//


        // total card
        binding.totalCardBox.text = "£ " + totalCardOrderAmount
        binding.totalCardBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalcard.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        // total cash
        binding.totalCashBox.text = "£ " + totalCashOrderAmount
        binding.totalCashBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalcash.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalBankBox.text = "£ " + totalBankOrderAmount
        binding.totalBankBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalbank.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)




        // total Refund card
//        binding.totalRefundCardBox.text = "£ " + totalRefundCardAmount
//        binding.totalRefundCardBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//        binding.totalRefundcard.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//        // total Refund cash
//        binding.totalRefundCashBox.text = "£ " + totalRefundCashAmount
//        binding.totalRefundCashBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
//        binding.totalRefundcash.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)

        // total balance
        val totalBalance = totalPayment - totalRefundAmount ;
        binding.totalBalanceBox.text = "£ " + String.format("%.2f", totalBalance)
        binding.totalBalanceBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)
        binding.totalBalance.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.fontSize?.toFloat() ?: 16f)

        val bitmaplist: Bitmap =  getBitmapFromView(binding.root)
        return  bitmaplist;

    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        printBitmap(result)
    }
}
