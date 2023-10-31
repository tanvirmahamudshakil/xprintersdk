package com.example.xprintersdk.PrinterService

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.databinding.ModelPrint2Binding
import com.example.xprintersdk.databinding.OnlinePrint2Binding
import com.example.xprintersdk.xprinter.Xprinter
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt


class printerservice(mcontext: Context, morderModel: OrderData, businessdata: BusinessSetting)
     {

    private var context: Context
    private  var orderModel: OrderData
    private  var businessname: String
    private  var businessaddress: String
    private  var businessphone: String
    private var fontsize: Int = 30
    private var noofprint: Int =1
         private var businessdatadata: BusinessSetting;

    init {
        context = mcontext;
        orderModel = morderModel;
        this.businessname = businessdata.businessname!!;
        this.businessaddress =  businessdata.businessaddress!!;
        this.businessphone =  businessdata.businessphone!!;
        this.fontsize =  businessdata.fontSize!!;
        noofprint = businessdata.printOnCollection!!;
        businessdatadata = businessdata
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun orderrootget(): LinearLayout {

          if (orderModel.orderType == "DELIVERY"){
              noofprint = businessdatadata.printOnDelivery!!
          }else{
              noofprint = businessdatadata.printOnCollection!!
          }

        val printSize: Int = fontsize
        val bind: OnlinePrint2Binding = OnlinePrint2Binding.inflate(LayoutInflater.from(context))
        bind.businessName.text = businessname


        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a")

        Log.d("order date", "orderrootget: ${orderModel.orderDate}")
        var addedDeliveryCharge = 0.0
        bind.businessLocation.text = businessaddress
        bind.businessPhone.text = businessphone
        bind.orderType.text = "${orderModel.orderType}"
        bind.orderTime.text = "Order at : ${parser.parse(orderModel.orderDate)
            ?.let { formatter.format(it) }}"
        bind.collectionAt.text = "${orderModel.orderType} at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"

        bind.orderNo.text = "${orderModel.id}";


        var allitemsheight = 0
        bind.items.removeAllViews()
        var itemproduict = orderModel.orderProducts!!.filter { i-> i!!.product!!.type == "ITEM" }
        for (j in itemproduict.indices) {
            val childView = getView(j, context, 0, printSize)
            bind.items.addView(childView)
            allitemsheight += childView!!.measuredHeight
        }


        var paidOrNot = "";
        if (orderModel.paymentType == "CARD") {
            paidOrNot ="ORDER IS PAID"
        } else  {
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
        bind.containerOrderNo.visibility = View.VISIBLE


        bind.total.text =
            "£ " +String.format( "%.2f",(orderModel.payableAmount!!))


        var dlAddress = "Service charge is not included\n\n"



        if (orderModel.orderType == "DELIVERY" || orderModel.orderType == "COLLECTION" || orderModel.orderType == "TAKEOUT") {
           if (orderModel.requesterGuest != null){
               val customerModel: OrderData.RequesterGuest? = orderModel.requesterGuest
               dlAddress += "Name : ${customerModel!!.firstName} ${customerModel!!.lastName}\n"
               dlAddress += "Phone : ${customerModel.phone}"
               if (orderModel.orderType != "COLLECTION" && orderModel.orderType != "TAKEOUT"){
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
           }else{
               val customerModel: OrderData.Requester? = orderModel.requester!!
               dlAddress += "Name : ${customerModel!!.name}\n"
               dlAddress += "Phone : ${customerModel.phone}"
               if (orderModel.orderType != "COLLECTION" && orderModel.orderType != "TAKEOUT"){
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
        }

        var comment = "Comments : ${if(orderModel.comment != null) orderModel.comment else ""}"

        comment += """




        """.trimIndent()

        bind.comments.text = comment
        bind.address.text = dlAddress

        bind.address.setTextSize(TypedValue.COMPLEX_UNIT_DIP, printSize.toFloat())

        return bind.root;

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

        //create resized image and display
        val maxImageSize = 570f
        val ratio = maxImageSize / returnedBitmap.width
        val width = (ratio * returnedBitmap.width).roundToInt()
        val height = (ratio * returnedBitmap.height).roundToInt()
        //return the bitmap

        var bitmap = Bitmap.createScaledBitmap(returnedBitmap, width, height, true)

        return bitmap;
    }

    fun getView(position: Int, mCtx: Context?, style: Int, fontSize: Int): View? {
        val binding: ModelPrint2Binding = ModelPrint2Binding.inflate(LayoutInflater.from(mCtx))
        var itemproduict = orderModel.orderProducts!!.filter { i-> i!!.product!!.type == "ITEM" }
        val item = itemproduict[position]
        val str3 = StringBuilder()
        if (position < itemproduict.size - 1) {
            if (orderModel.orderProducts!![position]!!.product!!.sortOrder!! < orderModel.orderProducts!![position + 1]!!.product!!.sortOrder!!) {

                binding.underLine.visibility = View.VISIBLE
            }
        }
        if (style == 0) {
            if (item!!.components!!.isNotEmpty() ) {
                str3.append(item.unit).append(" x ").append(item.product!!.shortName)
                for (section in item.components!!) {
                    var _comName = ""
                    if (section!!.product!!.shortName!!.uppercase() != "NONE") {
                        _comName = section.product!!.shortName!!
                    }
                    if (section.components!!.isNotEmpty()) {
                        if (section.components.first()!!.product!!.shortName!!.uppercase() != "NONE") {
                            _comName += " -> " + section.components.first()!!.product!!.shortName
                        }
                    }
                    if (_comName != "") {
                        str3.append("\n").append(_comName)
                    }
                }
            } else {
                if (item.product!!.type == "ITEM"){
                    str3.append(item.unit).append(" x ").append(item.product!!.shortName)
                }

            }
        } else {
            if (item!!.components!!.isNotEmpty()) {
                for (section in item.components!!) {
                    var _comName = ""
                    if (section!!.product!!.shortName != "NONE") {
                        _comName = section.product!!.shortName!!
                    }
                    if (section.product!!.shortName != "NONE") _comName += " -> " + section.product!!.shortName
                    str3.append(item.unit).append(" x ").append(item.product!!.shortName).append(" : ")
                        .append(_comName)
                }
            } else  {
                str3.append(item.unit).append(" x ").append(item.product!!.shortName)
            }
        }
        var price = 0.0
        price = item.netAmount!!
        if(item.comment != null && item.product!!.type == "ITEM") str3.append("\nNote : ").append(item.comment)
        binding.itemText.text = str3.toString()
        binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.toFloat())
       if(item.product!!.type == "ITEM"){
           binding.itemPrice.text = "£ ${String.format("%.2f", price.toFloat())}"
       }else{
           binding.itemPrice.visibility = View.GONE
       }
        binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fontSize.toFloat())
        binding.root.buildDrawingCache(true)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun printxprinteripdata(serviceBinding: Xprinter, result: MethodChannel.Result)  {
        val bitmaplist: Bitmap =  getBitmapFromView(orderrootget())
        printBitmap(bitmaplist, serviceBinding, result)
    }



    fun printBitmap(bitmap: Bitmap?, serviceBinding: Xprinter, result: MethodChannel.Result)  {
        try {
            val originalBitmap: Bitmap? = bitmap
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



//    private fun printUSBbitamp(printBmp: Bitmap, serviceBinding: Xprinter)  {
//        val height = printBmp.height
//
//        // if height > 200 cut the bitmap
//        if (height > 200) {
//            serviceBinding.binder!!.WriteSendData(object : TaskCallback {
//                override fun OnSucceed() {
//                    process.onSuccess()
//                }
//
//                override fun OnFailed() {
//                    process.onError("error")
//                }
//            }, ProcessData {
//                val list: MutableList<ByteArray> = java.util.ArrayList()
//                list.add(DataForSendToPrinterPos80.initializePrinter())
//                var bitmaplist: List<Bitmap?>? = java.util.ArrayList()
//                bitmaplist = cutBitmap(200, printBmp) //cut bitmap
//                if (bitmaplist!!.isNotEmpty()) {
//                    for (i in bitmaplist.indices) {
//                        list.add(
//                            DataForSendToPrinterPos80.printRasterBmp(
//                                0,
//                                bitmaplist[i],
//                                BitmapToByteData.BmpType.Threshold,
//                                BitmapToByteData.AlignType.Center,
//                                576
//                            )
//                        )
//                    }
//                }
//                list.add(DataForSendToPrinterPos80.printAndFeedForward(2))
//                list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(66, 1))
//                list
//            })
//        } else {
//            serviceBinding.binder!!.WriteSendData(object : TaskCallback {
//                override fun OnSucceed() {
//                    process.onSuccess()
//                }
//
//                override fun OnFailed() {
//                    process.onError("error")
//                }
//            }, ProcessData {
//                val list: MutableList<ByteArray> = java.util.ArrayList()
//                list.add(DataForSendToPrinterPos80.initializePrinter())
//                list.add(
//                    DataForSendToPrinterPos80.printRasterBmp(
//                        0,
//                        printBmp,
//                        BitmapToByteData.BmpType.Threshold,
//                        BitmapToByteData.AlignType.Center,
//                        600
//                    )
//                )
//                list.add(DataForSendToPrinterPos80.printAndFeedForward(2))
//                list.add(DataForSendToPrinterPos80.selectCutPagerModerAndCutPager(66, 1))
//                list
//            })
//        }
//
//
//
//    }
//
//
//         fun resizeImage(bitmap: Bitmap, w: Int, ischecked: Boolean): Bitmap? {
//        var resizedBitmap: Bitmap? = null
//        val width = bitmap.width
//        val height = bitmap.height
//        if (width == w) {
//            return bitmap
//        }
//
//        //resize image with width
//        val newHeight = height * w / width
//        val scaleWidth = w.toFloat() / width
//        val scaleHeight = newHeight.toFloat() / height
//        val matrix = Matrix()
//        matrix.postScale(scaleWidth, scaleHeight)
//        // if you want to rotate the Bitmap
//        // matrix.postRotate(45);
//        resizedBitmap =
//            Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
//        return resizedBitmap
//    }
//

    fun compressBitmap(bitmap: Bitmap, format: Bitmap.CompressFormat, quality: Int): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(format, quality, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getimagebytes(): ByteArray {
        val newbitmaplist: ArrayList<ByteArray?> = arrayListOf()
        val bitmaplist: Bitmap =  getBitmapFromView(orderrootget())

//       for (bitmap in bitmaplist){
//
//       }
        var b2 = resizeImage(bitmaplist, 530, true)
        val originalBitmap: Bitmap? = b2
        val compressFormat = Bitmap.CompressFormat.JPEG
        val compressionQuality = 10 // Adjust the quality as needed

//           val compressedData =
//               originalBitmap?.let { compressBitmap(it, compressFormat, compressionQuality) }


//        newbitmaplist.add(bitmapToByteArray(b2!!))
        return bitmapToByteArray(b2!!);
    }




         fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
             val stream = ByteArrayOutputStream()
             bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
             return stream.toByteArray()
         }

}