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
import android.icu.lang.UProperty.INT_START
import android.os.AsyncTask
import android.os.Build
import android.provider.MediaStore
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.text.bold
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.Nyxprinter.NyxprinterHelp
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.databinding.ModelPrint2Binding
import com.example.xprintersdk.databinding.OnlinePrint2Binding
import com.example.xprintersdk.xprinter.Xprinter
import io.flutter.plugin.common.MethodChannel
import net.nyx.printerclient.Nyxpinter
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt


class printerservice(mcontext: Context, morderModel: OrderData, businessdata: BusinessSetting, mserviceBinding: Xprinter, mresult: MethodChannel.Result, sunmiHelper : SunmiHelp, saveImage: Boolean, nyxp : NyxprinterHelp) :
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
    private var nyxprinter : NyxprinterHelp
    private var header1 : Int = 22
         private var header2 : Int = 22
         private var header3 : Int = 22
         private var header4 : Int = 22
         private var footervatFontSize : Int = 15


    init {
        context = mcontext;
        orderModel = morderModel;
        serviceBinding = mserviceBinding;
        this.businessname = businessdata.businessname ?: "";
        this.businessaddress =  businessdata.businessaddress ?: "";
        this.businessphone =  businessdata.businessphone ?: "";
        this.fontsize =  businessdata.fontSize ?: 30;
        noofprint = businessdata.printOnCollection ?: 1;
        businessdatadata = businessdata
        result = mresult
        sunmiPrinter = sunmiHelper;
        bitmapSave = saveImage;
        header1 = businessdata.header1Size ?: 22;
        header2 = businessdata.header2Size ?: 22;
        header3 = businessdata.header3Size ?: 22;
        header4 = businessdata.header4Size ?: 22;
        footervatFontSize = businessdata.footervatFontSize ?: 12
        this.nyxprinter = nyxp

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
             return if(i?.product?.type?.uppercase() == "COMPONENT") {
                 if (i.product.property != null){
                     if( i.product.property.itemtype != null) {
                         !(((i.product.property.itemtype?.lowercase() == "topping") || (i.product.property.itemtype?.lowercase() == "addon") || (i.product.property.itemtype?.lowercase() == "dressing")))
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

    fun getView( listorderProducts: List<OrderData.OrderProduct?>?, item: OrderData.OrderProduct?, iteamLength : Int ,position: Int, mCtx: Context?, style: Int, fontSize: Int): View? {
        val binding: ModelPrint2Binding = ModelPrint2Binding.inflate(LayoutInflater.from(mCtx))
        var  component: List<OrderData.OrderProduct.Component?>?
        var  extraIteam: List<OrderData.OrderProduct.Component?>? = ArrayList()
        if(orderModel.orderChannel?.uppercase() == "ONLINE") {
            component = item?.components;
        }else{
             component = item?.components?.filter {i-> componentFilter(i)}
             extraIteam = item?.components?.filter { i-> i?.product?.property?.itemtype != null && (i.product.property.itemtype?.lowercase() == "topping" || i.product.property.itemtype?.lowercase() == "addon" || i.product.property.itemtype?.lowercase() == "dressing")}
        }


        val str3 = StringBuilder()
        var price = 0.0
        price = item?.netAmount ?: 0.0
        var discount = item?.discountableAmount ?: 0.0;
        if (position < iteamLength - 1) {
            if ((listorderProducts!![position]?.product?.property?.printorder?.toInt()
                            ?: 0) < (listorderProducts[position + 1]?.product?.property?.printorder?.toInt()
                            ?: 0)) {

                binding.underLine.visibility = View.VISIBLE
            }

        }

        if (!component.isNullOrEmpty()) {
            str3.append(item?.unit).append("x ").append(item?.product?.shortName)
            for (section in component) {
                var _comName = ""
                if (section?.product?.shortName?.uppercase() != "NONE") {
                    _comName = section?.product?.shortName ?: ""
                }
                if ((section?.components != null) && section.components.isNotEmpty()) {
                    for (section2 in section.components) {
                        if (section2?.product?.shortName?.uppercase() != "NONE") {
                            _comName += " -> " + "${section2?.unit ?: 1}x " + section2?.product?.shortName;
                            price += ((section2?.netAmount ?: 0.0) * (section2?.unit ?: 1));
                        }
                    }

                }
                if (_comName != "") {
                    if(businessdatadata.printerStyle == "1") {
                        str3.append("\n").append(_comName)
                    }else{
                        str3.append(" -> ").append(_comName)
                    }

                }
                price += section?.netAmount ?: 0.0;
            }
        } else {
            if (item?.product?.type == "ITEM" || item?.product?.type == "DYNAMIC"){
                str3.append(item.unit).append("x ").append(item.product.shortName)
                if(businessdatadata.printerStyle == "3"){
                    if(item.product.property?.printorder == "2"){
                        str3.append("(Str)")
                    }
                }
            }

        }

        if (extraIteam != null) {
            val topping = StringBuilder()
            if (extraIteam.isNotEmpty()) {
                if(businessdatadata.printerStyle == "1") {
                    topping.append("\n")
                }else{
                    topping.append(" -> ")
                }
//                val topping = java.lang.StringBuilder("\n")
                for (extraItem in extraIteam) {
                    topping.append("  *").append(extraItem?.product?.shortName)
                    price += extraItem?.netAmount!!;
                }
                str3.append(topping.toString())
            }
        }


        if(orderModel.orderChannel?.uppercase() != "ONLINE"){
            price *= (item?.unit ?: 1)
            var totaldiscount = (price * (discount / 100))
            price -= totaldiscount;
        }
        Log.e("price get", "getView: ${price}----")
        if(item?.comment != null && (item.product?.type == "ITEM" || item.product?.type == "DYNAMIC")) str3.append("\nNote : ").append(item.comment)
        binding.itemText.text = str3.toString()
        binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
       if(item?.product?.type == "ITEM" || item?.product?.type == "DYNAMIC"){
           binding.itemPrice.text = "£ ${String.format("%.2f", price)}"
       }else{
           binding.itemPrice.visibility = View.GONE
       }
        binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
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
            } else if (businessdatadata.selectPrinter!!.lowercase() == "nyxprinter") {
                nyxprinter.printBitmap(bitmap!!, result)
            } else {
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

         fun  getOrderType(): String {
             if(orderModel.orderType == "COLLECTION") {
                 return "${businessdatadata.dynamicCollection?.uppercase()}";
             }else if (orderModel.orderType == "DELIVERY") {
                 return "${businessdatadata.dynamicDelivery?.uppercase()}";
             }else if(orderModel.orderType == "EAT IN") {
                 return "${businessdatadata.dynamicEatIn?.uppercase()}";
             }else if (orderModel.orderType == "TAKEAWAY") {
                 return "${businessdatadata.dynamicTakeaway?.uppercase()}";
             }else{
                 return "${orderModel.orderType}";
             }
         }

         @RequiresApi(Build.VERSION_CODES.O)
         override fun doInBackground(vararg params: String?): Bitmap {

             noofprint = if (orderModel.orderType == "DELIVERY"){
                  businessdatadata.printOnDelivery!!
             }else{
                 businessdatadata.printOnCollection!!
             }

             val printSize: Int = fontsize

             val bind: OnlinePrint2Binding = OnlinePrint2Binding.inflate(LayoutInflater.from(context))
             bind.businessName.text = businessname
             bind.businessName.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())

             val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
             val formatter = SimpleDateFormat("dd-MMM hh:mm a")
             val formatter2 = SimpleDateFormat(" dd/MM hh:mm a")
//             Log.e("date formet", "doInBackground: ${dateDifferent(orderModel.orderDate!!, orderModel.requestedDeliveryTimestamp!!)}", )
//             Log.d("order date", "orderrootget: ${orderModel.orderDate}")
             var addedDeliveryCharge = 0.0
             bind.businessLocation.text = businessaddress
             bind.businessLocation.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
             bind.businessPhone.text = businessphone
             bind.businessPhone.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
             bind.branchName.text = orderModel.branch?.name?.uppercase()
             bind.branchName.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())

             if(orderModel.orderType == "TABLE_BOOKING") {
                 bind.orderType.text = "TABLE BOOKING #${orderModel.table_id}"
                 bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
             }else{
                 bind.orderType.text =  getOrderType()
                 bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())

             }

             bind.orderTime.text = "Order at : ${parser.parse(orderModel.orderDate)
                 ?.let { formatter.format(it) }}"
             bind.orderTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
            if(orderModel.orderType == "TABLE_BOOKING") {
                bind.collectionAt.text = "TABLE BOOKING at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
            }else{
                if(orderModel.property?.requestedDeliveryTimestampType != null) {
                    var asapdata = orderModel.property?.requestedDeliveryTimestampType;
                    bind.collectionAt.text = asapdata
                    bind.collectionAt.setTypeface(null, Typeface.BOLD)
                    bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.asapFontSize!!.toFloat())

                }else{
                    bind.collectionAt.text = "REQUESTED at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                    bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                }

            }
             if ((orderModel.orderType?.uppercase() == "DELIVERY" || orderModel.orderType?.uppercase() == "COLLECTION") && getMinutesDifference(orderModel.orderDate
                             ?: "2024-01-22 07:48:10", orderModel.requestedDeliveryTimestamp
                             ?: "2024-01-22 17:20:00") >= (businessdatadata.highlight ?: 15)){
                 if(orderModel.property?.requestedDeliveryTimestampType == null) {
                     bind.collectionAt.setTypeface(null, Typeface.BOLD)
                     bind.collectionAt.paintFlags = Paint.UNDERLINE_TEXT_FLAG
                     bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_DIP, businessdatadata.highlighttextsize!!.toFloat())
                 }
             }
             if(orderModel.orderType == "TABLE_BOOKING") {
                 bind.orderText.text = "Table#"
                 bind.orderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
             }

             if(orderModel.orderChannel?.uppercase() == "ONLINE"){
                 bind.containerOrderNo.visibility = View.VISIBLE
                 bind.orderNo.text = "${orderModel.id}";
                 bind.orderNo.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                 bind.orderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
             }else{
                 if(orderModel.orderType == "TABLE_BOOKING"){
                     bind.containerOrderNo.visibility = View.GONE
//                     bind.orderNo.text = "${orderModel.table_id}";
                     bind.orderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())

                 }else{
                     bind.containerOrderNo.visibility = View.VISIBLE
                     bind.orderNo.text = "${orderModel.localId}";
                     bind.orderNo.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                     bind.orderText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                 }

             }

             var allitemsheight = 0
             bind.items.removeAllViews()
             var itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
             var sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
             if(!sortIteam.isNullOrEmpty()){
                 for (j in sortIteam.indices) {
                     Log.e("iteam sort", "doInBackground: ${j}", )
                     val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j, context, 0, printSize)
                     bind.items.addView(childView)
                     allitemsheight += childView!!.measuredHeight
                 }

             }



             var paidOrNot = "";
             if (orderModel.orderChannel?.uppercase() == "ONLINE") {
                 if(orderModel.status?.uppercase() == "REFUNDED") {
                     paidOrNot = "ORDER is REFUNDED"
                     bind.dueTotalContainer.visibility = View.VISIBLE
                     bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                 }else{
                     if(orderModel.paymentType?.uppercase() == "CARD" || orderModel.paymentType?.uppercase() == "EPOS_CARD"){
                         paidOrNot ="ORDER IS PAID"
                     }else if(orderModel.paymentType?.uppercase() == "CASH" || orderModel.paymentType?.uppercase() == "EPOS_CASH") {
                         if (orderModel.cashEntry == null || orderModel.cashEntry!!.isEmpty()){
                             paidOrNot = "ORDER NOT PAID"
                             bind.dueTotalContainer.visibility = View.VISIBLE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                         }else{
                             paidOrNot ="ORDER IS PAID"
                         }
                     }
                 }

             } else if (orderModel.orderChannel?.uppercase() != "ONLINE") {
                 if(orderModel.status?.uppercase() == "REFUNDED") {
                     paidOrNot = "ORDER is REFUNDED"
                     bind.dueTotalContainer.visibility = View.VISIBLE
                     bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                 }else{
                     if(orderModel.cashEntry != null && orderModel.cashEntry!!.isNotEmpty()) {
                         paidOrNot ="ORDER IS PAID"
                         bind.dueTotal.text = "£ 0.0"
                     }else{
                         if(orderModel.paymentType?.uppercase() == "UNPAID_CASH") {
                             paidOrNot ="ORDER IS UNPAID(CASH)"
                             bind.dueTotalContainer.visibility = View.VISIBLE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                         }else if(orderModel.paymentType?.uppercase() == "UNPAID_CARD") {
                             paidOrNot ="ORDER IS UNPAID(CARD)"
                             bind.dueTotalContainer.visibility = View.VISIBLE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                         }else{
                             paidOrNot = "ORDER NOT PAID"
                             bind.dueTotalContainer.visibility = View.VISIBLE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                         }
                     }
                 }

             } else  {
                 paidOrNot = "ORDER NOT PAID"
                 bind.dueTotalContainer.visibility = View.VISIBLE
                 bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
             }
//             else if (orderModel.orderChannel!!.uppercase() == "ONLINE" && orderModel.paymentType!!.uppercase() == "CASH") {
//                 if (orderModel.cashEntry!!.isEmpty()){
//                     paidOrNot = "ORDER NOT PAID"
//                     bind.dueTotalContainer.visibility = View.VISIBLE
//                     bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
//                 }else{
//                     paidOrNot ="ORDER IS PAID"
//                 }
//             }



             bind.orderPaidMessage.text = paidOrNot
             bind.orderPaidMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())
             bind.refundContainer.visibility = View.GONE


             val subTotal: Double = orderModel.netAmount ?: 0.0
             bind.subTotal.text = "£ " + String.format( "%.2f", subTotal)


             if(orderModel.orderType == "DELIVERY") {
                 bind.deliveryChargeContainer.visibility = View.VISIBLE
                 bind.txtDeliveryCharge.text = "Delivery Charge";
                 bind.deliveryCharge.text = "£ " + orderModel.deliveryCharge!!.toFloat().toString()
             }else{
                 bind.deliveryChargeContainer.visibility = View.GONE
             }

             bind.change.text = "£ " +  String.format( "%.2f",  orderModel.changeAmount)


             bind.cardPayContainer.visibility = View.GONE
             bind.cashPayContainer.visibility = View.GONE
             if (orderModel.orderChannel?.uppercase() == "ONLINE") {
                 if ((orderModel.discountedAmount ?: 0.0) > 0) {
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
             if(orderModel.vat_amount == 0.0) {
                 bind.vatContainer.visibility = View.GONE
             }else{
                 bind.vatContainer.visibility = View.VISIBLE
                 bind.vatAmount.text = "£ " + String.format( "%.2f", orderModel.vat_amount)
             }


             bind.total.text =
                 "£ " +String.format( "%.2f",(orderModel.payableAmount!!))


             var dlAddress = "Service charge is not included\n\n"



             if (orderModel.requesterGuest != null){
                 val customerModel: OrderData.RequesterGuest? = orderModel.requesterGuest
                 dlAddress += "Name : ${customerModel?.firstName} ${customerModel?.lastName}\n"
                 dlAddress += "Phone : ${customerModel?.phone}"
                 if (orderModel.shippingAddress != null) {
                     val address: OrderData.ShippingAddress? = orderModel.shippingAddress
                     if (address?.property != null) {
                         val pro: OrderData.ShippingAddress.Property = address.property
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
                     dlAddress += "Name : ${customerModel?.name}\n"
                     dlAddress += "Phone : ${customerModel?.phone}"
                     if (orderModel.shippingAddress != null) {
                         val address: OrderData.ShippingAddress? = orderModel.shippingAddress
                         if (address?.property != null) {
                             val pro: OrderData.ShippingAddress.Property = address.property
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
             bind.comments.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())
             bind.address.text = dlAddress
             bind.address.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())


             if(!businessdatadata.vatNumber.isNullOrEmpty() || !businessdatadata.vatCompanyName.isNullOrEmpty()) {
                 bind.vatNumberCompany.text = "VAT no ${businessdatadata.vatNumber}"+", ${businessdatadata.vatCompanyName}"

                 bind.vatNumberCompany.visibility = View.VISIBLE
                 bind.vatNumberCompany.setTextSize(TypedValue.COMPLEX_UNIT_SP, footervatFontSize.toFloat())
             }else{
                 bind.vatNumberCompany.visibility = View.GONE
             }

             if(!businessdatadata.vatNote.isNullOrEmpty() ) {
                 bind.vatNote.text = "${businessdatadata.vatNote}"
                 bind.vatNote.visibility = View.VISIBLE
                 bind.vatNote.setTextSize(TypedValue.COMPLEX_UNIT_SP, footervatFontSize.toFloat())
             }else{
                 bind.vatNote.visibility = View.GONE
             }

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