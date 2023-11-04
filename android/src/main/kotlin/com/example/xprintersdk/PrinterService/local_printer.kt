package com.example.xprintersdk.PrinterService

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.LocalOrderData.LocalOrderData
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.databinding.ModelPrint2Binding
import com.example.xprintersdk.databinding.OnlinePrint2Binding
import com.example.xprintersdk.xprinter.Xprinter
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class local_printer(mcontext: Context, mlocalModel: LocalOrderData, businessdata: BusinessSetting, mserviceBinding: Xprinter, mresult: MethodChannel.Result) {

    private var context: Context
    private  var orderModel: LocalOrderData
    private  var businessname: String
    private  var businessaddress: String
    private  var businessphone: String
    private var fontsize: Int = 30
    private var noofprint: Int =1
    private var businessdatadata: BusinessSetting
    private var serviceBinding: Xprinter
    private var result: MethodChannel.Result

    init {
        context = mcontext;
        orderModel = mlocalModel;
        serviceBinding = mserviceBinding;
        this.businessname = businessdata.businessname!!;
        this.businessaddress =  businessdata.businessaddress!!;
        this.businessphone =  businessdata.businessphone!!;
        this.fontsize =  businessdata.fontSize!!;
        noofprint = businessdata.printOnCollection!!;
        businessdatadata = businessdata
        result = mresult
    }

     @SuppressLint("SetTextI18n", "SimpleDateFormat")
     fun doInBackground(): Bitmap {
         if (orderModel.orderType == "DELIVERY"){
             noofprint = businessdatadata.printOnDelivery!!
         }else if(orderModel.orderType == "COLLECTION"){
             noofprint = businessdatadata.printOnCollection!!
         }else{
             noofprint = businessdatadata.printOnTackwayOrder!!
         }
         val printSize: Int = fontsize
         val bind: OnlinePrint2Binding = OnlinePrint2Binding.inflate(LayoutInflater.from(context))
         bind.businessName.text = businessname


         val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH)
         val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a")

         Log.d("order date", "orderrootget: ${orderModel.orderDate}")
         var addedDeliveryCharge = 0.0
         bind.businessLocation.text = businessaddress
         bind.businessPhone.text = businessphone
         bind.orderType.text = "${orderModel.orderType}"
         bind.orderTime.text = "Order at : ${parser.parse(orderModel.orderDate)
             ?.let { formatter.format(it) }}"
         bind.collectionAt.text = "${orderModel.orderType} at : ${
             orderModel.requestedDeliveryTimestamp?.let {
                 parser.parse(it)
                     ?.let { formatter.format(it) }
             }
         }"
         bind.orderNo.text = "${orderModel.localId}";
         var allitemsheight = 0
         bind.items.removeAllViews()
         for (j in orderModel.items!!.indices) {
             val childView = getView(j, context, 0, printSize)
             bind.items.addView(childView)
             allitemsheight += childView!!.measuredHeight
         }
         var paidOrNot = "";
         if (!orderModel.paymentType!!.uppercase().equals("NOTPAY")) {
             paidOrNot ="ORDER IS PAID"
         } else  {
             paidOrNot = "ORDER NOT PAID"
             bind.dueTotalContainer.visibility = View.VISIBLE
//            bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
             bind.dueTotal.text = "£ " + String.format("%.2f", (orderModel.payableAmount!! - orderModel.discountedAmount!!) + orderModel.deliveryCharge!!)
         }

         bind.orderPaidMessage.text = paidOrNot
         bind.refundContainer.visibility = View.GONE
         val subTotal: Double = orderModel.netAmount!! - addedDeliveryCharge
         bind.subTotal.text = "£ " + String.format( "%.2f", subTotal)
         bind.txtDeliveryCharge.text = "Delivery Charge";
         bind.deliveryCharge.text = "£ " + orderModel.deliveryCharge!!.toString()
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
                 "£ " + String.format( "%.2f", orderModel.discountedAmount!!)
         }
         bind.plasticBagContainer.visibility = View.GONE
         bind.containerBagContainer.visibility = View.GONE
         bind.adjustmentContainer.visibility = View.GONE
         bind.tipsContainer.visibility = View.GONE
         bind.containerOrderNo.visibility = View.VISIBLE
         bind.total.text =
             "£ " +String.format( "%.2f",(orderModel.payableAmount!! - orderModel.discountedAmount!!) + orderModel.deliveryCharge!!)
         var dlAddress = "Service charge is not included\n\n"
         if (orderModel.orderType == "DELIVERY" || orderModel.orderType == "COLLECTION" || orderModel.orderType == "TAKEOUT") {
             val customerModel:  LocalOrderData.Customer? = orderModel.customer
             dlAddress += "Name : ${customerModel!!.firstName} ${customerModel!!.lastName}\n"
             dlAddress += "Phone : ${customerModel.phone}"
             if (customerModel.address != null ) {
                 val address:  LocalOrderData.Customer.Address? = customerModel.address
                 if (address != null) {
                     val pro:  LocalOrderData.Customer.Address? = address
                     // CustomerAddressProperties pro = customerModel.addresses.get(0).properties;
                     val building = pro?.building ?: ""
//                    val streetNumber = if (pro.street_number != null) pro.street_number else ""
                     val streetName = pro?.street ?: ""
                     val city = pro?.city ?: ""

                     val zip = pro?.postcode ?: ""
                     dlAddress += "\nAddress : $building $streetName $zip"
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
    @SuppressLint("SetTextI18n")
    fun getView(position: Int, mCtx: Context?, style: Int, fontSize: Int): View? {
        val binding: ModelPrint2Binding = ModelPrint2Binding.inflate(LayoutInflater.from(mCtx))
        val item = orderModel.items?.get(position)
        val str3 = StringBuilder()
        if (position < orderModel.items!!.size - 1) {
//            if (orderModel.items!![position].components.sortOrder!! < orderModel.orderProducts!![position + 1].product!!.sortOrder!!) {
//                binding.underLine.visibility = View.VISIBLE
//            }
            binding.underLine.visibility = View.VISIBLE;

        }
//        if (style == 0) {
//            if (item.components!!.isNotEmpty()) {
//                str3.append(item.unit).append(" x ").append(item.shortName)
//                for (section in item.components) {
//                    var _comName = ""
//                    if (section.shortName!!.uppercase() != "NONE") {
//                        _comName = section.shortName!!
//                    }
//                    if (section.components != null) {
//                        if (section.components!!.shortName!!.uppercase() != "NONE") {
//                            _comName += " -> " + section.components!!.shortName
//                        }
//                    }
//                    if (_comName != "") {
//                        str3.append("\n").append(_comName)
//                    }
//                }
//            } else {
//                str3.append(item.unit).append(" x ").append(item!!.shortName)
//            }
//        } else {
//            if (item.components!!.isNotEmpty()) {
//                for (section in item.components!!) {
//                    var _comName = ""
//                    if (section.shortName != "NONE") {
//                        _comName = section.shortName!!
//                    }
//                    if (section.shortName != "NONE") _comName += " -> " + section.shortName
//                    str3.append(item.unit).append(" x ").append(item.shortName).append(" : ")
//                        .append(_comName)
//                }
//            } else {
//                str3.append(item.unit).append(" x ").append(item.shortName)
//            }
//        }
//        var price = 0.0
//        price = item.price!!
//        if(item.comment != null) str3.append("\nNote : ").append(item.comment)
//        binding.itemText.text = str3.toString()
//        binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
//        binding.itemPrice.text = "£ ${String.format("%.2f", price.toFloat())}"
//        binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize.toFloat())
//        binding.root.buildDrawingCache(true)

        if (style == 0) {
            if (item!!.components!!.size > 0) {
                str3.append(item!!.unit).append(" x ").append(item.shortName)
                for (section in item.components!!) {
                    var _comName = ""
                    if (section!!.shortName!!.uppercase() != "NONE" && !section!!.shortName!!.uppercase().equals("NORMAL")) {
                        _comName = section!!.shortName.toString();
                    }
                    if (section.components != null) {
                        if (section.components!!.shortName!!.uppercase() != "NONE" && !section.components!!.shortName!!.uppercase().equals("NORMAL")) {
                            _comName += " -> " + section.components!!.shortName
                        }
                    }
                    if (_comName != "") {
                        str3.append("\n").append(_comName)
                    }
                }
            } else {
                str3.append(item.unit).append(" x ").append(item.shortName)
            }
        } else {
            if (item!!.components!!.size > 0) {
                for (section in item.components!!) {
                    var _comName = ""
                    if (!section!!.shortName!!.uppercase().equals("NONE") && !section!!.shortName!!.uppercase().equals("NORMAL")) {
                        _comName = section!!.shortName.toString()
                    }
                    if (section.components != null) {
                        if (!section.components!!.shortName!!.uppercase().equals("NONE") && !section.components!!.shortName!!.uppercase().equals("NORMAL")) _comName += " -> " + section.components!!.shortName
                    }
                    str3.append(item.unit).append(" x ").append(item.shortName).append(" : ")
                        .append(_comName)
                }
            } else {
                str3.append(item.unit).append(" x ").append(item.shortName)
            }
        }

        if (item!!.extra!!.size > 0) {
            val topping = java.lang.StringBuilder("\n")
//            val addon = java.lang.StringBuilder("\nAddon :")
//            val dressing = java.lang.StringBuilder("\nDressing :")
//            var toppinglist = item.extra.filter { it-> it.comment!!.lowercase() == "topping" }
//            var addonlistlist = item.extra.filter { it-> it.comment!!.lowercase()  == "addon" }
//            var dressinglist = item.extra.filter { it-> it.comment!!.lowercase()  == "dressing" }


            for (extraItem in item.extra!!) {
                topping.append("  *").append(extraItem!!.shortName)
            }
//            for (extraItem in addonlistlist) {
//                addon.append("  *").append(extraItem.shortName)
//            }
//            for (extraItem in dressinglist) {
//                dressing.append("  *").append(extraItem.shortName)
//            }
//          if(toppinglist.isNotEmpty())  str3.append(topping.toString())
//            if(addonlistlist.isNotEmpty())   str3.append(addon.toString())
//            if(dressinglist.isNotEmpty())   str3.append(dressing.toString())
            str3.append(topping.toString())
        }


        var price = 0.0

        if (item.isDiscountApplied != null && item.isDiscountApplied == true){
            price = item.discountPrice!!.toDouble() * item.unit!!
        }else{
            price += item.price!! * item.unit!!;
            if (item.components != null) {
                if (item.components.isNotEmpty()) {
                    for ( component in item.components) {
                        price += component!!.price!!;
                        if (component.components != null) {
                            price += component.components.price!!;
                        }
                    }
                }
            }
        }

        if (item.comment!!.isNotEmpty()) str3.append("\nNote : ").append(item.comment)
        binding.itemText.text = str3.toString()
        binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
        binding.itemPrice.text =
            "£ " + String.format(Locale.getDefault(), "%.2f", price)
        binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize.toFloat())
        binding.root.buildDrawingCache(true)

        return binding.root
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

        //create resized image and display
        val maxImageSize = 570f
        val ratio = maxImageSize / returnedBitmap.width
        val width = (ratio * returnedBitmap.width).roundToInt()
        val height = (ratio * returnedBitmap.height).roundToInt()

        var bitmap = Bitmap.createScaledBitmap(returnedBitmap, width, height, true)

        return bitmap;
    }
    suspend fun printBitmap()  {
        try {
           var bitmap =  CoroutineScope(Dispatchers.IO).async {
                var data = doInBackground()
               data
            }
            val originalBitmap: Bitmap? = bitmap.await()
            val compressFormat = Bitmap.CompressFormat.JPEG
            val compressionQuality = 10 // Adjust the quality as needed
            val compressedData = originalBitmap?.let { compressBitmap(it, compressFormat, compressionQuality) }

            var b2 = resizeImage(byteArrayToBitmap(compressedData!!), 550, true)
            serviceBinding.printUSBbitamp(b2,result);

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



}