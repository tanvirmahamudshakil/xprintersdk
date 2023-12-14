package com.example.xprintersdk.PrinterService

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Typeface
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.databinding.ModelPrint2Binding
import com.example.xprintersdk.databinding.OnlinePrint2Binding
import com.example.xprintersdk.xprinter.Xprinter
import com.sunmi.peripheral.printer.InnerResultCallback
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt


class printerservice(mcontext: Context, morderModel: OrderData, businessdata: BusinessSetting, mserviceBinding: Xprinter, mresult: MethodChannel.Result, sunmiHelper : SunmiHelp, saveImage: Boolean) :
    AsyncTask<String, Int, Bitmap>()
     {

    private var context: Context
    private  var orderModel: OrderData
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
        orderModel = morderModel;
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


    fun capitalize(str: String): String? {
        return str.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
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
         fun getResizedBitmap(originalBitmap: Bitmap, targetWidth: Int, targetHeight: Int): Bitmap {
             val ratioX = targetWidth.toFloat() / originalBitmap.width
             val ratioY = targetHeight.toFloat() / originalBitmap.height
             val ratio = minOf(ratioX, ratioY)

             val width = (ratio * originalBitmap.width).toInt()
             val height = (ratio * originalBitmap.height).toInt()

             return Bitmap.createScaledBitmap(originalBitmap, width, height, true)
         }

         fun componentFilter( i: OrderData.OrderProduct.Component?) : Boolean {
             return if(i!!.product!!.type!!.uppercase() == "COMPONENT") {
                 if (i!!.product!!.property != null){
                     if( i!!.product!!.property!!.itemtype != null) {
                         !(i!!.product!!.property!!.itemtype!!.lowercase() == "topping" || i!!.product!!.property!!.itemtype!!.lowercase() == "addon" || i!!.product!!.property!!.itemtype!!.lowercase() == "dressing")
                     }else{
                         true;
                     }

                 }else{
                     true;
                 }
             }else{
                 false;
             }
         }

    fun getView( item: OrderData.OrderProduct?, iteamLength : Int ,position: Int, mCtx: Context?, style: Int, fontSize: Int): View? {
        val binding: ModelPrint2Binding = ModelPrint2Binding.inflate(LayoutInflater.from(mCtx))
//        var itemproduict = orderModel.orderProducts!!.filter { i-> i!!.product!!.type == "ITEM" }
//        itemproduict.sortedBy { it!!.product!!.property!!.printorder!!.toInt() }
//        val item = itemproduict[position]
        var  component: List<OrderData.OrderProduct.Component?>?
        var  extraIteam: List<OrderData.OrderProduct.Component?>? = ArrayList()
        if(orderModel.orderChannel!!.uppercase() == "ONLINE") {
            component = item!!.components;
        }else{
             component = item!!.components!!.filter {i-> componentFilter(i)}
             extraIteam = item!!.components!!.filter { i-> i!!.product!!.property != null && i!!.product!!.property!!.itemtype != null && (i!!.product!!.property!!.itemtype!!.lowercase() == "topping" || i!!.product!!.property!!.itemtype!!.lowercase() == "addon" || i!!.product!!.property!!.itemtype!!.lowercase() == "dressing")}
        }


        val str3 = StringBuilder()
        var price = 0.0
        price = item!!.netAmount!!
        var discount = item.discountableAmount ?: 0.0;
        if (position < iteamLength - 1) {
            if (orderModel.orderProducts!![position]!!.product!!.property!!.printorder!!.toInt()!! < orderModel.orderProducts!![position + 1]!!.product!!.property!!.printorder!!.toInt()!!) {
                binding.underLine.visibility = View.VISIBLE
            }

        }

        if (component!!.isNotEmpty() ) {
            str3.append(item.unit).append(" x ").append(item.product!!.shortName)
            for (section in component) {
                var _comName = ""
                if (section!!.product!!.shortName!!.uppercase() != "NONE") {
                    _comName = section.product!!.shortName!!
                }
                if (section.components!!.isNotEmpty()) {
                    if (section.components.first()!!.product!!.shortName!!.uppercase() != "NONE") {
                        _comName += " -> " + section.components.first()!!.product!!.shortName;
                        price += section.components.first()!!.netAmount!!;
                    }
                }
                if (_comName != "") {
                    str3.append("\n").append(_comName)
                }
                price += section.netAmount!!;
            }
        } else {
            if (item.product!!.type == "ITEM"){
                str3.append(item.unit).append(" x ").append(item.product!!.shortName)
            }

        }

        if (extraIteam != null) {
            if (extraIteam.isNotEmpty()) {
                val topping = java.lang.StringBuilder("\n")
                for (extraItem in extraIteam) {
                    topping.append("  *").append(extraItem!!.product!!.shortName)
                    price += extraItem.netAmount!!;
                }
                str3.append(topping.toString())
            }
        }

        if(orderModel.orderChannel!!.uppercase() != "ONLINE"){
            price *= (item.unit!! ?: 1)
            var totaldiscount = (price * (discount / 100))



            price -= totaldiscount;
        }
        Log.e("price get", "getView: ${price}----", )
        if(item.comment != null && item.product!!.type == "ITEM") str3.append("\nNote : ").append(item.comment)
        binding.itemText.text = str3.toString()
        binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
       if(item.product!!.type == "ITEM"){
           binding.itemPrice.text = "£ ${String.format("%.2f", price)}"
       }else{
           binding.itemPrice.visibility = View.GONE
       }
        binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize.toFloat())
        binding.root.buildDrawingCache(true)
        return binding.root
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


         fun getMinutesDifference(date1: String, date2: String): Long {
             val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

             try {
                 val parsedDate1: Date = dateFormat.parse("$date1 00:00:00")
                 val parsedDate2: Date = dateFormat.parse("$date2 00:00:00")

                 // Calculate the difference in milliseconds
                 val diffInMillis: Long = parsedDate2.time - parsedDate1.time

                 // Convert milliseconds to minutes
                 val minutesDifference: Long = diffInMillis / (1000 * 60)

                 return minutesDifference
             } catch (e: Exception) {
                 e.printStackTrace()
                 return -1 // Handle the exception according to your requirements
             }
         }

         @RequiresApi(Build.VERSION_CODES.O)
         override fun doInBackground(vararg params: String?): Bitmap {
             Log.e("tanvirdoninbackground", "doInBackground: ${params}", )
             noofprint = if (orderModel.orderType == "DELIVERY"){
                 businessdatadata.printOnDelivery!!
             }else{
                 businessdatadata.printOnCollection!!
             }

             val printSize: Int = fontsize
             val bind: OnlinePrint2Binding = OnlinePrint2Binding.inflate(LayoutInflater.from(context))
             bind.businessName.text = businessname


             val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
             val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a")
             val formatter2 = SimpleDateFormat(" dd/MM hh:mm a")
//             Log.e("date formet", "doInBackground: ${dateDifferent(orderModel.orderDate!!, orderModel.requestedDeliveryTimestamp!!)}", )
//             Log.d("order date", "orderrootget: ${orderModel.orderDate}")
             var addedDeliveryCharge = 0.0
             bind.businessLocation.text = businessaddress
             bind.businessPhone.text = businessphone
             if(orderModel.orderType == "TABLE_BOOKING") {
                 bind.orderType.text = "TABLE BOOKING #${orderModel.table_id}"
             }else{
                 bind.orderType.text = "${orderModel.orderType}"
             }

             bind.orderTime.text = "Order at : ${parser.parse(orderModel.orderDate)
                 ?.let { formatter.format(it) }}"
            if(orderModel.orderType == "TABLE_BOOKING") {
                bind.collectionAt.text = "TABLE BOOKING at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
            }else{
                if(orderModel.property?.requestedDeliveryTimestampType != null) {
                    bind.underline.visibility = View.GONE
                    bind.asap.visibility = View.VISIBLE
                    bind.collectionAt.text = "${orderModel.orderType} at : "
                    bind.asap.text = "${orderModel.property?.requestedDeliveryTimestampType}"
                    bind.date.text = "${formatter2.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                    bind.asap.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0F)
                    bind.asap.setTypeface(null, Typeface.BOLD)
                    bind.asap.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                }else{
                    bind.underline.visibility = View.GONE
                    bind.asap.visibility = View.GONE
                    bind.collectionAt.text = "${orderModel.orderType} at : "
                    bind.date.text = "${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                }

            }
             if ((orderModel.orderType!!.uppercase() == "DELIVERY" ||orderModel.orderType!!.uppercase() == "COLLECTION") &&  getMinutesDifference(orderModel.orderDate!!, orderModel.requestedDeliveryTimestamp!!) >= businessdatadata.highlight!!){
                 if(orderModel.property?.requestedDeliveryTimestampType == null) {
                     bind.collectionAt.setTypeface(null, Typeface.BOLD)
                     bind.date.setTypeface(null, Typeface.BOLD)
                     bind.underline.visibility = View.VISIBLE
                 }

             }
             if(orderModel.orderType == "TABLE_BOOKING") {
                 bind.orderText.text = "Table#"
             }

             if(orderModel.orderChannel!!.uppercase() == "ONLINE"){
                 bind.containerOrderNo.visibility = View.VISIBLE
                 bind.orderNo.text = "${orderModel.id}";
             }else{
                 if(orderModel.orderType == "TABLE_BOOKING"){
                     bind.containerOrderNo.visibility = View.GONE
//                     bind.orderNo.text = "${orderModel.table_id}";

                 }else{
                     bind.containerOrderNo.visibility = View.VISIBLE
                     bind.orderNo.text = "${orderModel.localId}";
                 }

             }
//             if(businessdatadata.showOrderNoInvoice == true){
//                 bind.containerOrderNo.visibility = View.VISIBLE
//             }else{
//                 bind.containerOrderNo.visibility = View.GONE
//             }

             var allitemsheight = 0
             bind.items.removeAllViews()
             var itemproduict = orderModel.orderProducts!!.filter { i-> i!!.product!!.type == "ITEM" }
             var sortIteam = itemproduict.sortedWith(compareBy {it!!.product!!.property!!.printorder!!.toInt() })
             for (j in sortIteam.indices) {
                 val childView = getView(sortIteam[j],sortIteam.size, j, context, 0, printSize)
                 bind.items.addView(childView)
                 allitemsheight += childView!!.measuredHeight
             }


             var paidOrNot = "";
             if (orderModel.orderChannel!!.uppercase() == "ONLINE" && orderModel.paymentType!!.uppercase() == "CARD") {
                 paidOrNot ="ORDER IS PAID"
             } else if (orderModel.orderChannel!!.uppercase() != "ONLINE" && orderModel.cashEntry!!.isNotEmpty()) {
                 paidOrNot ="ORDER IS PAID"
             } else if (orderModel.orderChannel!!.uppercase() == "ONLINE" && orderModel.paymentType!!.uppercase() == "CASH") {
                 if (orderModel.cashEntry!!.isEmpty()){
                     paidOrNot = "ORDER NOT PAID"
                     bind.dueTotalContainer.visibility = View.VISIBLE
                     bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                 }else{
                     paidOrNot ="ORDER IS PAID"
                 }
             }
             else  {
                 paidOrNot = "ORDER NOT PAID"
                 bind.dueTotalContainer.visibility = View.VISIBLE
                 bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
             }


             bind.orderPaidMessage.text = paidOrNot
             bind.refundContainer.visibility = View.GONE


             val subTotal: Double = orderModel.netAmount!!
             bind.subTotal.text = "£ " + String.format( "%.2f", subTotal)



             bind.txtDeliveryCharge.text = "Delivery Charge";
             bind.deliveryCharge.text = "£ " + orderModel.deliveryCharge!!.toFloat().toString()

             bind.cardPayContainer.visibility = View.GONE
             bind.cashPayContainer.visibility = View.GONE
             if (orderModel.orderChannel!!.uppercase() == "ONLINE") {
                 if (orderModel.discountedAmount!! > 0) {
                     bind.discount.text =
                         "£ " +  String.format( "%.2f",  orderModel.discountedAmount)
                 } else bind.discount.text =
                     "£ " + String.format("%.2f", 0.0)
             } else {
                 var discountStr = "Discount"
                 bind.discountTitle.text = discountStr
                 var discountAmount = 0.0
                 bind.discount.text =
                     "£ " + String.format( "%.2f", orderModel.discountedAmount)
             }


             bind.plasticBagContainer.visibility = View.GONE

             bind.containerBagContainer.visibility = View.GONE

             bind.adjustmentContainer.visibility = View.GONE
             bind.tipsContainer.visibility = View.GONE

             Log.e("sdjvnskdj", "doInBackground: ${orderModel.payableAmount!!}", )
             bind.total.text =
                 "£ " +String.format( "%.2f",(orderModel.payableAmount!!))


             var dlAddress = "Service charge is not included\n\n"



             if (orderModel.requesterGuest != null){
                 val customerModel: OrderData.RequesterGuest? = orderModel.requesterGuest
                 dlAddress += "Name : ${customerModel!!.firstName} ${customerModel!!.lastName}\n"
                 dlAddress += "Phone : ${customerModel.phone}"
                 if (orderModel.shippingAddress != null) {
                     val address: OrderData.ShippingAddress? = orderModel.shippingAddress
                     if (address!!.property != null) {
                         val pro: OrderData.ShippingAddress.Property = address.property!!
                         // CustomerAddressProperties pro = customerModel.addresses.get(0).properties;
                         val building = pro.house ?: ""
//                    val streetNumber = if (pro.street_number != null) pro.street_number else ""
                         val streetName = pro.state ?: ""
                         val city = pro.town ?: ""
                         val state = pro.state ?: ""
                         val zip = pro.postcode ?: ""
                         dlAddress += "\nAddress : $building $streetName\n$city $state $zip"
                     }
                 }
             }else{
                 if(orderModel.requester != null) {
                     val customerModel: OrderData.Requester? = orderModel.requester!!
                     dlAddress += "Name : ${customerModel!!.name}\n"
                     dlAddress += "Phone : ${customerModel.phone}"
                     if (orderModel.shippingAddress != null) {
                         val address: OrderData.ShippingAddress? = orderModel.shippingAddress
                         if (address!!.property != null) {
                             val pro: OrderData.ShippingAddress.Property = address.property!!
                             // CustomerAddressProperties pro = customerModel.addresses.get(0).properties;
                             val building = pro.house ?: ""
//                    val streetNumber = if (pro.street_number != null) pro.street_number else ""
                             val streetName = pro.state ?: ""
                             val city = pro.town ?: ""
                             val state = pro.state ?: ""
                             val zip = pro.postcode ?: ""
                             dlAddress += "\nAddress : $building $streetName\n$city $state $zip"
                         }
                     }
                 }

             }

             var comment = "Comments : ${if(orderModel.comment != null) orderModel.comment else ""}"

             comment += """




        """.trimIndent()


             bind.comments.text = comment
             bind.address.text = dlAddress

             bind.address.setTextSize(TypedValue.COMPLEX_UNIT_DIP, printSize.toFloat())


             val bitmaplist: Bitmap =  getBitmapFromView(bind.root)

             return  bitmaplist;
         }


         override fun onPostExecute(result: Bitmap?) {
             super.onPostExecute(result)
             printBitmap(result)
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

    }