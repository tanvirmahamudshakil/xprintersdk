package com.example.xprintersdk.PrinterService

import android.annotation.SuppressLint
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
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.example.xprintersdk.Barcode.barcodeSetting
import com.example.xprintersdk.Bitmap.bitmapSetting
import com.example.xprintersdk.LabelPrinter.LabelPrinter
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.Nyxprinter.NyxprinterHelp
import com.example.xprintersdk.Printer80.printer80
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.databinding.ButcherOrderPrintBinding
import com.example.xprintersdk.databinding.ButcherOrderStyle2Binding
import com.example.xprintersdk.databinding.GroceryinvoiceBinding
import com.example.xprintersdk.databinding.GroupPrintViewBinding
import com.example.xprintersdk.databinding.ModelPrint2Binding
import com.example.xprintersdk.databinding.OnlinePrint2Binding
import com.example.xprintersdk.databinding.StickerprinterBinding
import com.example.xprintersdk.xprinter.PrinterIdentifierResolver
import com.example.xprintersdk.xprinter.xprinterService
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import io.flutter.plugin.common.MethodChannel
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.floor
import kotlin.math.roundToInt


class orderPrinterService(
    mcontext: Context,
    morderModel: OrderData,
    businessdata: BusinessSetting,
    mserviceBinding: xprinterService,
    mresult: MethodChannel.Result,
    sunmiHelper : SunmiHelp,
    saveImage: Boolean,
    nyxp : NyxprinterHelp,
    labelPrinter : LabelPrinter,
    printer80D : printer80,
    var barcodePrint: Boolean?,
    var kitchenPrint : Boolean,
    ) : AsyncTask<String, Int, Bitmap>()
     {
         private var context: Context
         private  var orderModel: OrderData
         private  var businessname: String
         private  var businessaddress: String
         private  var businessphone: String
         private var fontsize: Int = 30
         private var labelPrinter: LabelPrinter
         private var printer80: printer80
         private var noofprint: Int =1
         private var businessdatadata: BusinessSetting
         private var serviceBinding: xprinterService
         private var result: MethodChannel.Result
         private var sunmiPrinter : SunmiHelp
         private var bitmapSave: Boolean
         private var nyxprinter : NyxprinterHelp
         private var header1 : Int = 22
         private var header2 : Int = 22
         private var header3 : Int = 22
         private var header4 : Int = 22

         private var footervatFontSize : Int = 15
         lateinit var barcode: String
         private val size_width = 660
         private val size_height = 264

    init {
        context = mcontext;
        orderModel = morderModel;
        printer80 = printer80D
        serviceBinding = mserviceBinding;
        this.businessname = businessdata.businessname ?: "";
        this.businessaddress =  businessdata.businessaddress ?: "";
        this.businessphone =  businessdata.businessphone ?: "";
        this.fontsize =  businessdata.fontSize ?: 30;
        noofprint = businessdata.printOnCollection ?: 1;
        businessdatadata = businessdata
        result = mresult
        sunmiPrinter = sunmiHelper;
        this.labelPrinter = labelPrinter
        bitmapSave = saveImage;
        header1 = businessdata.header1Size ?: 22;
        header2 = businessdata.header2Size ?: 22;
        header3 = businessdata.header3Size ?: 22;
        header4 = businessdata.header4Size ?: 22;
        footervatFontSize = businessdata.footervatFontSize ?: 12
        this.nyxprinter = nyxp

    }

         var x_for_poundOfferApplyList  = mutableListOf<Int>();
         var banquetOfferApplyCartList = mutableListOf<MutableMap<String, Any?>>()

         fun rotateBitmap180(bitmap: Bitmap): Bitmap {
             val matrix = Matrix().apply {
                 postRotate(180f)
             }
             return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
         }


         fun capitalize(str: String): String? {

             return str.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

         }

         private fun getBitmapFromView(view: View): Bitmap {

             if(businessdatadata.selectPrinter!!.lowercase() == "label_printer") {
                 val dpi = businessdatadata.dpi ?: 203
                 val widthMm = businessdatadata.label_width ?: 76
                 val heightMm = businessdatadata.label_hight ?: 76
//                 val widthPx = (widthMm * dpi / 25.4f).toInt()
//                 val heightPx = (heightMm * dpi / 25.4f).toInt()
//                 val specWidth = View.MeasureSpec.makeMeasureSpec(widthPx, View.MeasureSpec.EXACTLY)
//                 val specHeight = View.MeasureSpec.makeMeasureSpec(heightPx, View.MeasureSpec.EXACTLY)
//                 view.measure(specWidth, specHeight)
//                 view.layout(0, 0, widthPx, heightPx)
//                 val bitmap = Bitmap.createBitmap(widthPx, heightPx, Bitmap.Config.ARGB_8888)
//                 val canvas = Canvas(bitmap)
//                 val offsetX = (widthPx - view.measuredWidth) / 2
//                 val offsetY = (heightPx - view.measuredHeight) / 2
//                 canvas.translate(offsetX.toFloat(), offsetY.toFloat())
//                 view.background?.draw(canvas) ?: canvas.drawColor(Color.WHITE)
//                 view.draw(canvas)
                 var labelBitmap = bitmapSetting().labelprinterBitmap(view, dpi, widthMm, heightMm)
                 return rotateBitmap180(labelBitmap)

             }else{
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
                 val canvas = Canvas(returnedBitmap)
                 val bgDrawable = view.background
                 if (bgDrawable != null) {
                     bgDrawable.draw(canvas)
                 } else {
                     canvas.drawColor(Color.WHITE)
                 }
                 view.draw(canvas)


                 var bitmap: Bitmap = if (businessdatadata.paperSize == 80) {
                     val maxImageSize = 570f
                     val ratio = maxImageSize / returnedBitmap.width
                     val width = (ratio * returnedBitmap.width).roundToInt()
                     val height = (ratio * returnedBitmap.height).roundToInt()
                     Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
                 } else {
                     val maxImageSize = 390f
                     val ratio = maxImageSize / (returnedBitmap.width)
                     val width = (ratio * returnedBitmap.width).roundToInt()
                     val height = (ratio * returnedBitmap.height).roundToInt()
                     Bitmap.createScaledBitmap(returnedBitmap, width, height, true)
                 }
                 return bitmap;
             }

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
//                 if (i.product.property != null){
//                     if( i.product.property.itemtype != null) {
//                         !(((i.product.property.itemtype?.lowercase() == "topping") || (i.product.property.itemtype?.lowercase() == "addon") || (i.product.property.itemtype?.lowercase() == "dressing")))
//                     }else{
//                         true;
//                     }
//                 }else{
//                     true;
//                 }
                 true;
             }else{
                 false;
             }
         }


         fun getView(listorderProducts: List<OrderData.OrderProduct?>?, item: OrderData.OrderProduct?, iteamLength : Int, position: Int): View? {
             val binding: ModelPrint2Binding = ModelPrint2Binding.inflate(LayoutInflater.from(context))
             var  component: List<OrderData.OrderProduct.Component?>?
             var  extraIteam: List<OrderData.OrderProduct.Component?>? = ArrayList()
             if(businessdatadata.printerStyle == "5") {
                 binding.unitValue.visibility = View.GONE
                 binding.itemPrice.visibility = View.GONE
             }


             if(orderModel.orderChannel?.uppercase() == "ONLINE") {
                 component = item?.components;
             } else {
                 component = item?.components?.filter {i-> componentFilter(i)}
                 extraIteam = item?.components?.filter { i-> i?.product?.type == "EXTRA-COMPONENT"}
             }
             val str3 = StringBuilder()
//             var price = 0.0
             var tareWeight : Double = if(item?.product?.property?.tare_weight?.isEmpty() == true) {
                 0.0;
             }else{
                 item?.product?.property?.tare_weight?.toDouble() ?: 0.0
             }
             var unitAmount = if(item?.product?.property?.unit_amount?.isEmpty() == true) {
                 0.0
             } else{
                 item?.product?.property?.unit_amount?.toDouble() ?: 0.0
             }
             if(businessdatadata.invoice_type == "GROCERY") {
                 binding.unitValue.visibility = View.VISIBLE
                 binding.unitValue.text = "${item?.unit ?: 1}"
                 binding.unitValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, (header3.toFloat() - 5))
             }else{
                 if(unitAmount == 0.0) {
                     binding.unitValue.visibility = View.GONE
                 }else{
                     if(businessdatadata.weightShow) {
                         binding.unitValue.visibility = View.VISIBLE
                         binding.unitValue.text = "${unitAmount} ${unitGet(item)}"
                         binding.unitValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, (header3.toFloat() - 5))
                     } else {
                         binding.unitValue.visibility = View.GONE
                     }
                 }
             }

//             if(item?.product?.property?.unit_product_type?.uppercase() == "WEIGHT") {
//                 if(businessdatadata.weightMultiplyingPrice) {
//                     price = (item.netAmount ?: 0.0) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight)
//                 }else{
//                     price = (item.netAmount ?: 0.0)
//                 }
//             }else{
//                 price = (item?.netAmount ?: 0.0)
//             }
             var discount = item?.discountableAmount ?: 0.0;
//             if (position < iteamLength - 1) {
//                 if ((listorderProducts!![position]?.product?.property?.printorder?.toInt()
//                         ?: 0) < (listorderProducts[position + 1]?.product?.property?.printorder?.toInt()
//                         ?: 0)) {
//                     binding.underLine.visibility = View.VISIBLE
//                 }
//             }

             var regularNormalLargeSecoundItem = isRegularNormalLargeSecoundItem(component)

             if (!component.isNullOrEmpty() && !regularNormalLargeSecoundItem) {
                 if (businessdatadata.show_category_name && item?.categoryName != null){
                     if(businessdatadata.invoice_type == "GROCERY") {
                         str3.append("${item.product?.shortName},${item.categoryName}")
                     }else{
                         str3.append(item.unit).append("x ").append("${item.product?.shortName},${item.categoryName}")
                     }

                 }else{
                     if(businessdatadata.invoice_type == "GROCERY"){
                         str3.append(item?.product?.shortName)
                     }else{
                         str3.append(item?.unit).append("x ").append(item?.product?.shortName)
                     }
                 }

                 if(item?.product?.property?.discriptionShowInPrinter == "TRUE" && item.product.property.shortDescription != null) str3.append("\n").append(item?.product?.property?.shortDescription ?: "")

                 for (section in component) {
                     var _comName = ""
                     var reqularLargeNormal = isRegularNormalLarge(section)
                     if (section?.product?.shortName?.uppercase() != "NONE") {
                         var name = section?.product?.shortName ?: "";
                         var unit = section?.unit ?: 1

                         _comName = ((if (unit == 1) "" else "${unit}x ") + name)
                         if(reqularLargeNormal) _comName += " (${section?.components?.first()?.product?.shortName})"
                         if(section?.product?.description != null) {
                             _comName += "\n" + "${section?.product?.description ?: ""}"
                         }
                     }
                     if ((section?.components != null) && section.components.isNotEmpty() && !reqularLargeNormal) {
                         for (section2 in section.components) {
                             if (section2?.product?.shortName?.uppercase() != "NONE") {
                                 var name = section2?.product?.shortName ?: "";
                                 var unit = section2?.unit ?: 1
                                 if(businessdatadata.printerStyle == "2") {
                                     _comName += "\n      -> " + ((if (unit == 1) "" else "${unit}x ") + name);
                                 }else{
                                     _comName += "\n      " + ((if (unit == 1) "" else "${unit}x ") + name);
                                 }
                                // price += ((section2?.netAmount ?: 0.0) * (section2?.unit ?: 1));
                             }
                         }
                     }
                     if (_comName != "") {
                         if(businessdatadata.printerStyle == "2") {
                             str3.append("\n -> ").append(_comName)
                         }else{
                             str3.append("\n   ").append(_comName)
                         }
                     }
                    // price += section?.netAmount ?: 0.0;
                 }
             } else {
                 if (item?.product?.type == "ITEM" || item?.product?.type == "DYNAMIC"){
                     if (businessdatadata.show_category_name && item.categoryName != null){
                         if(businessdatadata.invoice_type == "GROCERY"){
                             str3.append("${item.product.shortName},${item.categoryName}")
                         }else{
                             str3.append(item.unit).append("x ").append("${item.product.shortName},${item.categoryName}")
                         }

                     }else{
                         if(businessdatadata.invoice_type == "GROCERY"){
                             str3.append(item.product.shortName)
                         }else{
                             var name = item.product.shortName;
                             if(regularNormalLargeSecoundItem) {
                                 name += " (${component?.first()?.product?.shortName})"
                             }
                             str3.append(item.unit).append("x ").append(name)
                         }

                     }

                     if(businessdatadata.printerStyle == "2"){
                         if(item.product.property?.printorder == "2"){
                             str3.append("(Str)")
                         }
                     }
                 }

                 if(item?.product?.property?.discriptionShowInPrinter == "TRUE" && item.product.property.shortDescription != null) str3.append("\n").append(item?.product?.property?.shortDescription ?: "")
             }

             if (extraIteam != null) {
                 val topping = StringBuilder()
                 if (extraIteam.isNotEmpty()) {
                     if(businessdatadata.printerStyle == "1") {
                         topping.append("\n")
                     }else{
                         topping.append("\n-> ")
                     }
//                val topping = java.lang.StringBuilder("\n")
                     for (extraItem in extraIteam) {
                         topping.append("*").append("${extraItem?.product?.shortName}  ")
                        // price += extraItem?.netAmount!!;
                     }
                     str3.append(topping.toString())
                 }
             }
             var price : Double = 0.0;
             if(orderModel.orderChannel?.uppercase() == "ONLINE") {
                 price = calculatePriceForOnlineOrder(item)
             }else  {
                 price = calculatePriceForLocalOrder(listorderProducts, item)
             }
//             val totaldiscount = (price * (discount / 100))
//             price -= totaldiscount;


             if(orderModel.orderChannel == "ONLINE") {
                 if(item?.promo_discount != null && item.promo_discount != "0.00") {
                     str3.append("\npromo : £ ").append(item.promo_discount)
                 }

             }else {
                 if(item?.promoAmountList != null && item.promoAmountList != 0.0) {
                     str3.append("\npromo : £ ").append(String.format("%.2f", item.promoAmountList))
                 }
             }
             if(item?.comment != null && (item.product?.type == "ITEM" || item.product?.type == "DYNAMIC")) str3.append("\nNote : ").append(item.comment)
             binding.itemText.text = str3.toString()
             binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
             if (businessdatadata.invoiceItemPriceShow == false) {
                 binding.itemPrice.visibility = View.GONE
             } else if(item?.product?.type == "ITEM" || item?.product?.type == "DYNAMIC"){
                 binding.itemPrice.text = "£ ${String.format("%.2f", price)}"
                 binding.itemPrice.visibility = View.VISIBLE
             } else{
                 binding.itemPrice.visibility = View.GONE
             }
             binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
             binding.root.buildDrawingCache(true)
             return binding.root
    }



         fun isRegularNormalLarge(d: OrderData.OrderProduct.Component?): Boolean {
             return if (d?.components?.size == 1) {
                 val itemName = d.components.first()?.product?.shortName?.lowercase()
                 itemName == "regular" || itemName == "normal" || itemName == "large"
             } else {
                 false
             }
         }

         fun isRegularNormalLargeSecoundItem(component: List<OrderData.OrderProduct.Component?>?): Boolean {
             return if (component?.size == 1) {
                 val itemName = component?.first()?.product?.shortName?.lowercase()
                 itemName == "regular" || itemName == "normal" || itemName == "large"
             } else {
                 false
             }
         }


         fun calculatePriceForLocalOrder(listorderProducts: List<OrderData.OrderProduct?>?,item: OrderData.OrderProduct?) : Double {
             val weightmultiplayprice : Boolean = businessdatadata.weightMultiplyingPrice
               return item?.netAmountWihPromoApply ?: 0.0;
//             var total: Double = 0.0;
//             val components = item?.components ?: emptyList();
//             for (element in components){
//                 total += ((element?.netAmount ?: 0.0) * (element?.paid_unit ?: 1))
//                 val subComponentes = element?.components ?: emptyList()
//                 for (element2 in subComponentes) {
//                     total += (((element2?.netAmount ?: 0.0) * (element2?.paid_unit ?: 1)) * (element?.paid_unit ?: 1))
//                 }
//             }
//             var tareWeight : Double = if(item?.product?.property?.tare_weight?.isEmpty() == true) {
//                 0.0;
//             }else{
//                 item?.product?.property?.tare_weight?.toDouble() ?: 0.0
//             }
//             var unitAmount = if(item?.product?.property?.unit_amount?.isEmpty() == true) {
//                 0.0
//             } else{
//                 item?.product?.property?.unit_amount?.toDouble() ?: 0.0
//             }
//
//             if(item?.product?.property?.unit_product_type?.uppercase() == "WEIGHT") {
////                 if (item?.offer?.offer?.type == "X_FOR_Y" && item?.offer?.offer?.status == 1) {
////                     if(weightmultiplayprice) {
//////                         val p = String.format("%.2f", getOrderOfferPrice(item).toDouble())
////                         total = ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1)) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight)
////                     }else{
//////                         val p = String.format("%.2f", getOrderOfferPrice(item).toDouble())
////                         total = ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1))
////                     }
////
////                 }else if (item?.offer?.offer?.type == "X_FOR_£" && item?.offer?.offer?.status == 1) {
////                     if(weightmultiplayprice) {
////                         var p = String.format("%.2f", xForPoundOfferLocalDetailOrder(item, listorderProducts))
////                         total =  p.toDouble() * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight)
////                     }else{
////                         var p = String.format("%.2f", xForPoundOfferLocalDetailOrder(item, listorderProducts))
////                         total =  p.toDouble()
////                     }
////
////                 } else if (item?.offer?.offer?.type == "%_DISCOUNT" && item?.offer?.offer?.status == 1){
////                     if(weightmultiplayprice) {
//////                         var p = String.format("%.2f", persentDisocunt(item))
////                         total =  ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1)) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight)
////                     }else{
//////                         var p = String.format("%.2f", persentDisocunt(item))
////                         total =  ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1))
////                     }
////                 }
////
//////                 else if (item?.offer?.offer?.type == "BUY_X_GET_%OFF_NTH" && item?.offer?.offer?.status == 1){
//////                     var p = buy_x_get_nth_local(item)
//////                     if(weightmultiplayprice) {
//////
//////                         total =  ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1)  * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight) - p);
//////                     }else{
//////
//////                         total = (((total + (item.netAmount ?: 0.0)))* (item.unit ?: 1) - 1)
//////                     }
//////                 }
////
////                 else{
////                     if(weightmultiplayprice) {
////                         total = (((total + (item.netAmount ?: 0.0)) * (item.unit?: 1).toDouble()) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight))
////                     }else{
////                         total = ((total + (item.netAmount ?: 0.0)) * (item.unit?: 1).toDouble())
////                     }
////                 }
//
//                 if(weightmultiplayprice) {
//                     total = (((total + (item.netAmount ?: 0.0)) * (item.unit?: 1).toDouble()) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight))
//                 }else{
//                     total = ((total + (item.netAmount ?: 0.0)) * (item.unit?: 1).toDouble())
//                 }
//
//             }else {
////                 if (item?.offer?.offer?.type == "X_FOR_Y" && item?.offer?.offer?.status == 1) {
////
//////                    val p = String.format("%.2f", getOrderOfferPrice(item).toDouble())
////
////
////                     total = ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1))
////
////                 }else if (item?.offer?.offer?.type == "X_FOR_£" && item?.offer?.offer?.status == 1) {
////                     var p = String.format("%.2f", xForPoundOfferLocalDetailOrder(item, listorderProducts))
////                     total =  p.toDouble()
////
////                 }  else if (item?.offer?.offer?.type == "%_DISCOUNT" && item?.offer?.offer?.status == 1) {
//////                     var p = String.format("%.2f", persentDisocunt(item))
////                     total =  ((total + (item.netAmount ?: 0.0)) * (item.unit ?: 1))
////
////                 }
////                 else if (item?.offer?.offer?.type == "BUY_X_GET_%OFF_NTH" && item?.offer?.offer?.status == 1) {
////                     var p = buy_x_get_nth_local(item)
////                     total = ((total + (item?.netAmount ?: 0.0)) * (item?.unit?: 1).toDouble() - p)
////
////                 }
////                 else{
////                     total = ((total + (item?.netAmount ?: 0.0)) * (item?.unit?: 1).toDouble())
////                 }
//                 total = ((total + (item?.netAmount ?: 0.0)) * (item?.unit?: 1).toDouble())
//             }
//
//             return total
         }

//         fun buy_x_get_nth_local(item: OrderData.OrderProduct?) : Double {
//             val findOffer = orderModel.orderProducts?.filter { element ->
//                 element?.offer?.offer?.type == "BUY_X_GET_%OFF_NTH" &&
//                         element?.offer?.offerId == item?.offer?.offerId
//             }
//
//             val totalQty = findOffer?.fold(0) { sum, item ->
//                 sum + (item?.unit ?: 1)
//             }
//
//             val buyQty = item?.offer?.offer?.buy ?: 0
//             val offerValue = item?.offer?.offer?.offerFor ?: 0.0
//
//             val sortedFindOffer = findOffer?.sortedBy { it?.netAmount ?: 0.0 }
//
//             return if (sortedFindOffer?.firstOrNull()?.id == item?.id) {
//                 val eligibleSets = if (buyQty != 0) (totalQty ?: 1) / buyQty else 0
//                 val remainingSets = eligibleSets
//                 val totalPrice = item?.netAmount ?: 0.0
//                 val discountAmount = (totalPrice * offerValue) / 100
//                 discountAmount * remainingSets
//             } else {
//                 0.0
//             }
//         }


//         fun getBanquetOfferForLocal(item: OrderData.OrderProduct?): Int {
//             var itemproduictWithOutSort = orderModel.orderProducts;
//             val isOfferItem = businessdatadata.items?.any { it?.offerProductID == item?.id }
//
//             if (itemproduictWithOutSort?.firstOrNull()?.id == item?.id) {
//                 banquetOfferApplyCartList.clear()
//
//             }
//
//             if (isOfferItem == true) {
//
//                 return item?.unit ?: 1
//             } else {
//                 val banquetOfferIndex = businessdatadata.items?.indexOfFirst {
//                     it?.categoryID?.contains(item?.categoryId.toString()) == true
//                 }
//
//
//
//
//                 if (banquetOfferIndex != -1) {
//
//                     val banquetOffer = businessdatadata.items?.get(banquetOfferIndex!!)
//
//                     val freeLimit = banquetOffer?.freeQuantity ?: 0
//
//                     val isAvailableInCart = itemproduictWithOutSort?.filter {
//
//                         it?.id == banquetOffer?.offerProductID
//                     }
//
//
//
//
//
//                     isAvailableInCart?.size?.let {
//                         if (it > 0) {
//
//                             val offerProductQty = isAvailableInCart.first()?.unit ?: 1
//                             val totalFreeLimit = freeLimit * offerProductQty
//
//                             val categoryIds = banquetOffer?.categoryID
//                                 ?.split(",")
//                                 ?.mapNotNull { it.toIntOrNull() }
//                                 ?: emptyList()
//
//                             val offerItems1 = itemproduictWithOutSort?.filter {
//                                 categoryIds.contains(it?.categoryId)
//                             }
//
//                             var offerItems = offerItems1?.toMutableList()?.asReversed()
//
//                             var remainingFree = totalFreeLimit
//                             val currentItemId = item?.id ?: 0
//
//                             if (offerItems != null) {
//                                 for (item in offerItems) {
//                                     val itemQty = item?.unit ?: 1
//                                     var freeForThisItem = 0
//
//                                     if (remainingFree > 0) {
//                                         freeForThisItem = if (itemQty <= remainingFree) itemQty else remainingFree
//                                     }
//
//                                     remainingFree -= freeForThisItem
//
//                                     val alreadyExists = banquetOfferApplyCartList.any {
//                                         it["id"] == item?.id
//                                     }
//
//                                     if (!alreadyExists) {
//                                         banquetOfferApplyCartList.add(
//                                             mutableMapOf(
//                                                 "id" to item?.id,
//                                                 "freeQty" to freeForThisItem,
//                                                 "totalQty" to itemQty
//                                             )
//                                         )
//                                     }
//                                 }
//                             }
//
//                             val found = banquetOfferApplyCartList.filter {
//                                 it["id"] == currentItemId
//                             }
//
//
//                             if (found.isNotEmpty()) {
//                                 val freeQty = found.first()["freeQty"] as? Int ?: 0
//                                 val totalQty = found.first()["totalQty"] as? Int ?: 0
//                                 val paidQty = totalQty - freeQty
//
//                                 return paidQty
//                             } else {
//                                 return item?.unit ?: 1
//                             }
//                         } else {
//                             return item?.unit ?: 1
//                         }
//                     }
//                     return item?.unit ?: 1
//                 } else {
//                     return item?.unit ?: 1
//                 }
//             }
//         }


//         fun getBanquetOfferForLocal(items: OrderData.OrderProduct?): Int {
//             var itemproduictWithOutSort = orderModel.orderProducts;
//             val isOfferItem = businessdatadata.items?.any { it?.offerProductID == items?.id }
//             if (itemproduictWithOutSort?.firstOrNull()?.id == items?.id) {
//                 banquetOfferApplyCartList.clear()
//             }
//             if (isOfferItem == true) {
//                 return items?.unit ?: 1
//             } else {
//                 val banquetOfferIndex = businessdatadata.items?.indexOfFirst {
//                     it?.categoryID?.contains(items?.categoryId.toString()) == true
//                 }
//
//                 if (banquetOfferIndex != -1) {
//
//                     val banquetOffer = businessdatadata.items?.get(banquetOfferIndex!!)
//
//                     val freeLimit = banquetOffer?.freeQuantity ?: 0
//
//                     val isAvailableInCart = itemproduictWithOutSort?.filter {
//
//                         it?.id == banquetOffer?.offerProductID
//                     }
//
//
//
//
//
//                     isAvailableInCart?.size?.let {
//                         if (it > 0) {
//
//                             val offerProductQty = isAvailableInCart.first()?.unit ?: 1
//                             val totalFreeLimit = freeLimit * offerProductQty
//
//                             val categoryIds = banquetOffer?.categoryID
//                                 ?.split(",")
//                                 ?.mapNotNull { it.toIntOrNull() }
//                                 ?: emptyList()
//
//                             val offerItems1 = itemproduictWithOutSort?.filter {
//                                 categoryIds.contains(it?.categoryId) && it?.product?.property?.epos_gourmet_offer_price_apply == "1"
//                             }
//
//                            var offerItems = offerItems1?.toMutableList()?.asReversed()
//
//                             var remainingFree = totalFreeLimit
//                             val currentItemId = items?.id ?: 0
//
//                             if (offerItems != null) {
//                                 for (item in offerItems) {
//                                     val itemQty = item?.unit ?: 1
//                                     var freeForThisItem = 0
//
//                                     if (remainingFree > 0) {
//                                         freeForThisItem = if (itemQty <= remainingFree) itemQty else remainingFree
//                                     }
//
//                                     remainingFree -= freeForThisItem
//
//                                     val alreadyExists = banquetOfferApplyCartList.any {
//                                         it["id"] == item?.id
//                                     }
//
//                                     banquetOfferApplyCartList.add(
//                                         mutableMapOf(
//                                             "id" to item?.id,
//                                             "sub_id" to item?.components?.map { it?.id.toString() }
//                                                 ?.toMutableList()?.joinToString(","),
//                                             "sub_sub_id" to item?.components?.map { it?.components?.map { it?.id.toString() }?.toMutableList()?.joinToString(",") }
//                                                 ?.toMutableList()?.joinToString(","),
//                                             "freeQty" to freeForThisItem,
//                                             "totalQty" to itemQty,
//                                         )
//                                     )
//
//                                 }
//                             }
//
//                              var sub_id = items?.components?.map { it?.id.toString() }
//                                 ?.toMutableList()?.joinToString(",");
//
//                             var sub_sub_id = items?.components?.map { it?.components?.map { it?.id.toString() }?.toMutableList()?.joinToString(",") }
//                                 ?.toMutableList()?.joinToString(",");
//
//                             val found = banquetOfferApplyCartList.filter {
//                                 it["id"] == currentItemId &&
//                                         it["sub_id"] == sub_id &&
//                                         it["sub_sub_id"] == sub_sub_id
//                             }
//
//
//                             if (found.isNotEmpty()) {
//                                 val freeQty = found.first()["freeQty"] as? Int ?: 0
//                                 val totalQty = found.first()["totalQty"] as? Int ?: 0
//                                 val paidQty = totalQty - freeQty
//
//                                 return paidQty
//                             } else {
//                                 return items?.unit ?: 1
//                             }
//                         } else {
//                             return items?.unit ?: 1
//                         }
//                     }
//                     return items?.unit ?: 1
//                 } else {
//                     return items?.unit ?: 1
//                 }
//             }
//         }
//

         fun calculatePriceForOnlineOrder(item: OrderData.OrderProduct?) : Double {
             var weightmultiplayprice : Boolean = businessdatadata.weightMultiplyingPrice
             var total: Double = 0.0;
             var promo_discount = item?.promo_discount?.toDoubleOrNull() ?: 0.0;
             val components = item?.components ?: emptyList();

             for (element in components){
                 total += (element?.netAmount ?: 0.0)
                 val subComponentes = element?.components ?: emptyList()
                 for (element2 in subComponentes) {
                     total += ((element2?.netAmount ?: 0.0))
                 }
             }

             var tareWeight : Double = if(item?.product?.property?.tare_weight?.isEmpty() == true) {
                 0.0;
             }else{
                 item?.product?.property?.tare_weight?.toDouble() ?: 0.0
             }
             var unitAmount = if(item?.product?.property?.unit_amount?.isEmpty() == true) {
                 0.0
             } else{
                 item?.product?.property?.unit_amount?.toDouble() ?: 0.0
             }

             if(item?.product?.property?.unit_product_type?.uppercase() == "WEIGHT") {
                 if(weightmultiplayprice) {
                     total = (((total + (item.netAmount ?: 0.0))) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight))
                 }else{
                     total = ((total + (item.netAmount ?: 0.0)))
                 }

             } else{
                 total = ((total + (item?.netAmount ?: 0.0)))
             }
             return (total - promo_discount)
         }

         fun unitGet(data: OrderData.OrderProduct?): String {
             val unitOfSale = data?.product?.property?.unit_of_sale
             return when (unitOfSale) {
                 "POUND" -> "P"
                 "KG" -> "KG"
                 "PCS" -> "PCS"
                 "TON" -> "TON"
                 "LB" -> "LB"
                 else -> "G"
             }
         }

         fun getOrderOfferPrice(data: OrderData.OrderProduct): Int {
             val totalItems = data.unit ?: 1
             if (data.offer?.offer?.type == "X_FOR_Y") {
                 val buyX : Int = data.offer.offer.buy ?: 0
                 val payY : Double = data.offer.offer.offerFor ?: 0.0
                 val groupSize = buyX
                 val fullPriceItems = totalItems % groupSize // Items not fitting into the promotion
                 val discountedGroups = totalItems / groupSize // Number of groups that fit into the promotion
                 // Total paid items for groups that fit the promotion plus any remaining full price items
                 val paidItems = discountedGroups * payY + fullPriceItems
                 return paidItems.toInt()
             } else {
                 return totalItems
             }
         }

         fun truncateToTwoDecimalPlaces(value: Double): Double {
             val factor = 100.0
             return floor(value * factor) / factor
         }

         fun persentDisocunt(data: OrderData.OrderProduct) : Double {
             val buy : Int = data.offer?.offer?.buy ?: 0
             val forPound : Double = data.offer?.offer?.offerFor ?: 0.0
             val unit: Int = data.unit ?: 1;
             return  calculateDiscountTotalPrice(
                 quantity = unit, unitPrice = data.netAmount ?: 0.0,
                 discountPercent = forPound,
                 discountPerQuantity = buy
             )

         }

         fun calculateDiscountTotalPrice(quantity: Int, unitPrice: Double, discountPercent: Double, discountPerQuantity: Int): Double {
             val discountedSets = quantity / discountPerQuantity // how many full groups
             val nonDiscountedItems = quantity % discountPerQuantity
             var totalAmount = discountedSets * discountPerQuantity * unitPrice
             val discountedTotal =totalAmount * (discountPercent / 100)
             val nonDiscountedTotal = nonDiscountedItems * unitPrice
             return (totalAmount - discountedTotal) + nonDiscountedTotal
         }

         fun xForPoundOfferLocalDetailOrder(data: OrderData.OrderProduct, listorderProducts: List<OrderData.OrderProduct?>?): Double {
             var totalBuy = 0
             val findOffer = listorderProducts!!.filter { it?.offer?.offer?.type == "X_FOR_£" && it?.offer?.offer?.offerFor == data.offer?.offer?.offerFor }

             if (findOffer.isNotEmpty()) {
                 findOffer.forEach { e ->
                     totalBuy += e?.unit ?: 1
                 }

                 val buy : Int = data.offer?.offer?.buy ?: 0
                 val forPound : Double = data.offer?.offer?.offerFor ?: 0.0

                 if (totalBuy % buy == 0) {
                     val offerPrice = (forPound / buy) * (data.unit ?: 1)
                     x_for_poundOfferApplyList.add(data.unit ?: 1)
                     var of = String.format("%.2f", offerPrice.toFloat())
                     Log.e("offer 1", "getView: ${of}----${offerPrice.toFloat()}")
                     return truncateToTwoDecimalPlaces(offerPrice)
                 } else if (totalBuy > buy) {
                     val itemQuantity = data.unit ?: 1
                     if (itemQuantity == buy) {
                         val offerPrice = (forPound / buy) * itemQuantity
                         x_for_poundOfferApplyList.add(itemQuantity)
                         var of = String.format("%.2f", offerPrice)
                         Log.e("offer 2", "getView: ${of.toDouble()}----")
                         return truncateToTwoDecimalPlaces(offerPrice)
                     } else if (itemQuantity > buy) {
                         val otherQuantity = itemQuantity - buy
                         val offerQuantity = (data.unit ?: 1) - otherQuantity
                         val offerPrice = (forPound / buy) * offerQuantity
                         val fullPrice = (data.netAmount ?: 0.0) * otherQuantity
                         x_for_poundOfferApplyList.add(itemQuantity)
                         var of = String.format("%.2f", offerPrice)
                         Log.e("offer 3", "getView: ${of.toDouble()}----${fullPrice}")
                         return fullPrice + (truncateToTwoDecimalPlaces(offerPrice))
                     } else {
                         var totalQuantityApply = 0
                         if (findOffer.first()?.id == data.id) {
                             x_for_poundOfferApplyList.clear()
                         }
                         x_for_poundOfferApplyList.forEach { e ->
                             totalQuantityApply += e
                         }
                         println("sdbjshdbv $totalQuantityApply")
                         if (totalQuantityApply < buy) {
                             val available = buy - totalQuantityApply
                             if (available < itemQuantity) {
                                 val otherQuantity = itemQuantity - available
                                 val offerQuantity = available
                                 val offerPrice = (forPound / buy) * offerQuantity
                                 val fullPrice = (data.netAmount ?: 0.0) * otherQuantity
                                 x_for_poundOfferApplyList.add(itemQuantity)
                                 var of = String.format("%.2f", offerPrice)
                                 Log.e("offer 4", "getView: ${of.toDouble()}----${fullPrice}")
                                 return fullPrice + truncateToTwoDecimalPlaces(offerPrice)
                             } else {
                                 val offerPrice = (forPound / buy) * itemQuantity
                                 x_for_poundOfferApplyList.add(itemQuantity)
                                 var of = String.format("%.2f", offerPrice)
                                 Log.e("offer 5", "getView: ${of.toDouble()}----")
                                 return truncateToTwoDecimalPlaces(offerPrice)
                             }
                         } else if (totalQuantityApply == buy) {
                             val fullPrice = (data.netAmount ?: 0.0) * itemQuantity
                             Log.e("offer 6", "getView: ${fullPrice}----")
                             return fullPrice;
                         } else {
                             x_for_poundOfferApplyList.remove(itemQuantity)
                             return (data.netAmount ?: 0.0) * (data.unit ?: 1)
                         }
                     }
                 } else {
                     x_for_poundOfferApplyList.remove(data.unit ?: 1)
                     return (data.netAmount ?: 0.0) * (data.unit ?: 1)
                 }
             }
             x_for_poundOfferApplyList.remove(data.unit ?: 1)
             return (data.netAmount ?: 0.0) * (data.unit ?: 1)
         }


         fun printBitmap(bitmap: Bitmap?)  {
        try {
            if(bitmapSave) {
                saveBitmapToGallery(context, bitmap!!, "bitmapImage", "scascas");
            }else if (businessdatadata.selectPrinter!!.lowercase() == "xprinter"){
                val printerKey = PrinterIdentifierResolver.resolve(businessdatadata) ?: serviceBinding.getDefaultPrinterKey()
                serviceBinding.printBitmap(printerKey, bitmap,result);
            } else if (businessdatadata.selectPrinter!!.lowercase() == "nyxprinter") {
                nyxprinter.printBitmap(bitmap!!, result)
            } else if (businessdatadata.selectPrinter!!.lowercase() == "label_printer") {
                val dpi = businessdatadata.dpi ?: 203
                val widthMm = businessdatadata.label_width ?: 76

                val widthPx = (widthMm * dpi / 25.4f).toInt()
                 labelPrinter.printPicCode(
                     bitmap!!,
                     result,
                     (businessdatadata.label_width?.toDouble() ?: 76.0),
                     (businessdatadata.label_hight?.toDouble() ?: 30.0),
                     widthPx
                 )
            } else if (businessdatadata.selectPrinter!!.lowercase() == "printer80") {
                if (bitmap != null) {
                    printer80.printBitmap(bitmap)
                    result.success(true)
                };
            }
            else {
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


         private fun genBarcode(barcode : String) : Bitmap? {
             // Geting input value from the EditText
             val inputValue = barcode.trim()
             var width = businessdatadata.butcher_order_barcode_width;
             var height = businessdatadata.butcher_order_barcode_hight;
             if (inputValue.isNotEmpty()) {
                 // Initializing a MultiFormatWriter to encode the input value
                 val mwriter = MultiFormatWriter()
                 try {
                     // Generating a barcode matrix
                     val matrix = mwriter.encode(inputValue, BarcodeFormat.CODE_128, width, height)
                     // Creating a bitmap to represent the barcode
                     val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                     // Iterating through the matrix and set pixels in the bitmap
                     for (i in 0 until width) {
                         for (j in 0 until height) {
                             bitmap.setPixel(i, j, if (matrix[i, j]) Color.BLACK else Color.WHITE)
                         }
                     }
                     // Seting the bitmap as the image resource of the ImageView
                     return bitmap
                 } catch (e: Exception) {
                     return  null;
                 }
             } else {
                 // Showing an error message if the EditText is empty
                 return  null;
             }
         }
//
//
//         private fun genBarcode2(barcode : String) : Bitmap? {
//             var widthd = businessdatadata.barcode_width ?: 250;
//             var heightd = businessdatadata.barcode_hight ?: 100;
//
//             var bitMatrix: BitMatrix? = null
//             bitMatrix = MultiFormatWriter().encode(barcode, BarcodeFormat.CODE_128, widthd, heightd)
//             val width = bitMatrix.width
//             val height = bitMatrix.getHeight()
//             val pixels = IntArray(width * height)
//             for (i in 0 until height) {
//                 for (j in 0 until width) {
//                     if (bitMatrix[j, i]) {
//                         pixels[i * width + j] = -0x1000000
//                     } else {
//                         pixels[i * width + j] = -0x1
//                     }
//                 }
//             }
//             val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//             bitmap.setPixels(pixels, 0, width, 0, 0, width, height)
//             return bitmap
////
////             val inputValue = barcode.trim()
////             var width = businessdatadata.barcode_width ?: 250;
////             var height = businessdatadata.barcode_hight ?: 100;
////             if (inputValue.isNotEmpty()) {
////                 val hints = mapOf(
////                     EncodeHintType.MARGIN to 0  // Optional: set margin to 0 for a tighter fit
////                 )
////                 // Initializing a MultiFormatWriter to encode the input value
////       //          val mwriter = MultiFormatWriter()
////                 try {
////                     val bitMatrix: BitMatrix = MultiFormatWriter().encode(
////                         inputValue,
////                         BarcodeFormat.CODE_128,  // Use CODE_128 or other formats as needed
////                         width,
////                         height,
////                         hints
////                     )
////
////                     val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
////                     for (x in 0 until width) {
////                         for (y in 0 until height) {
////                             bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
////                         }
////                     }
////                     return bitmap
////                 } catch (e: Exception) {
////                    return  null;
////                 }
////             } else {
////                 // Showing an error message if the EditText is empty
////                return  null;
////             }
//         }
//

         @SuppressLint("DefaultLocale", "SetTextI18n")
         fun eposWaiterorderPrint() : Bitmap {
             noofprint = if (orderModel.orderType == "DELIVERY"){
                 businessdatadata.printOnDelivery!!
             }else{
                 businessdatadata.printOnCollection!!
             }

               val printSize: Int = fontsize
               val bind: OnlinePrint2Binding = OnlinePrint2Binding.inflate(LayoutInflater.from(context))
               bind.businessName.text = businessname
               bind.businessName.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
               if (businessdatadata.invoicebusinessName == false) {
                   bind.businessName.visibility = View.GONE
               }
               if(businessdatadata.printerStyle == "5") {
                   bind.containerSummary.visibility = View.GONE
               }
               if (businessdatadata.invoicecontainerSummary == false) {
                   bind.containerSummary.visibility = View.GONE
               }

               if(businessdatadata.invoicebusinessName == false &&
                   businessdatadata.invoicebusinessLocation == false &&
                   businessdatadata.invoicebusinessPhoneLayout == false &&
                   businessdatadata.invoicebranchName == false) {
                   bind.header1dotted.visibility = View.GONE

               }

             if(businessdatadata.invoiceorderType == false &&
                 businessdatadata.invoicecontainerTableNo == false &&
                 businessdatadata.invoiceorderTime == false &&
                 businessdatadata.invoicenumber_of_guest_box == false &&
                 businessdatadata.invoiceCollectionContainer == false &&
                 businessdatadata.invoicecontainerOrderNo == false
                 ) {
                 bind.dottedBeforeItems.visibility = View.GONE

             }


             applyOrderSummaryVisibility(bind)
               bind.dottedBeforeSummary.visibility =
                   if (bind.containerSummary.visibility == View.VISIBLE) View.VISIBLE else View.GONE

             val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
             val formatter = SimpleDateFormat("dd-MMM hh:mm a")
             val requestformatter = SimpleDateFormat("HH:mm")
             val formatter2 = SimpleDateFormat(" dd/MM hh:mm a")
             var totalRefund : Double = 0.0;
             var totalCardPaid : Double = 0.0;
             var totalCashPaid: Double = 0.0;
             var totalReceivePound : Double = 0.0
             var totalDue : Double = 0.0
             var totalBank : Double = 0.0
              // var totalDue : Double = 0.0;
             val refundList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "REFUND"}
             var cardPaidList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "EPOS_CARD" || it?.type?.uppercase() == "CARD"}
             var cashPaidList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "EPOS_CASH" || it?.type?.uppercase() == "CASH"}
             var dueList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "CREDIT"}
             var bankList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "BANK"}

             val receivePoundList = orderModel.cashEntry?.filter { it?.type?.uppercase() != "REFUND"}
             val changeAmount : Double = orderModel.changeAmount ?: 0.0;
             refundList?.forEach {
                 totalRefund += (it?.amount ?: 0.0)
             }
             dueList?.forEach {
                 totalDue += (it?.amount ?: 0.0)
             }
             bankList?.forEach {
                 totalBank += (it?.amount ?: 0.0)
             }
             cardPaidList?.forEach {
                 totalCardPaid += (it?.amount?: 0.0)
             }
             cashPaidList?.forEach {
                 totalCashPaid += (it?.amount?: 0.0)
             }
             receivePoundList?.forEach {
                 totalReceivePound += (it?.amount ?: 0.0)
             }

             if(businessdatadata.invoiceCollectionContainer == false) {
                 bind.CollectionContainer.visibility = View.GONE
             }else{
                 bind.CollectionContainer.visibility = View.VISIBLE
             }


             // totalDue = (orderModel.payableAmount ?: 0.0) - (totalReceivePound - (orderModel.changeAmount ?: 0.0))
               var addedDeliveryCharge = 0.0
               if(businessdatadata.printerStyle == "4") {
                   bind.headerOneLayout.visibility = View.GONE
                   bind.Layout9.visibility = if (businessdatadata.invoiceLayout9 == false) View.GONE else View.VISIBLE
                   bind.Layout10.visibility = if (businessdatadata.invoiceLayout10 == false) View.GONE else View.VISIBLE
                   bind.Layout11.visibility = if (businessdatadata.invoiceLayout11 == true) View.VISIBLE else View.GONE
                   bind.businessLocation2.text = businessaddress
                   bind.businessLocation2.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
                   bind.businessPhone2.text = businessphone

                   bind.businessPhone2.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
                   if (businessdatadata.invoicebusinessLocation == false) {
                       bind.businessLocation2.visibility = View.GONE
                   }
                   if (businessdatadata.invoicebusinessPhoneLayout == false) {
                       bind.businessPhone2.visibility = View.GONE
                   }
               }else{
                   bind.businessLocation.text = businessaddress
                   bind.businessLocation.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
                   if (businessdatadata.invoicebusinessLocation == false) {
                       bind.businessLocation.visibility = View.GONE
                   }
                   bind.businessPhone.text = businessphone
                   bind.businessPhone.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
                   if (businessdatadata.invoicebusinessPhoneLayout == false) {
                       bind.businessPhoneLayout.visibility = View.GONE
                   }
                   if(businessdatadata.branchNameShow && businessdatadata.invoicebranchName != false) {
                       bind.branchName.text = orderModel.branch?.name?.uppercase()
                       bind.branchName.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
                   }else{
                       bind.branchName.visibility = View.GONE
                   }
                   if (bind.businessName.visibility == View.GONE &&
                       bind.businessLocation.visibility == View.GONE &&
                       bind.businessPhoneLayout.visibility == View.GONE &&
                       bind.branchName.visibility == View.GONE) {
                       bind.headerOneLayout.visibility = View.GONE
                   }
               }


               if(orderModel.orderType == "TABLE_BOOKING") {
                   bind.orderType.text = "TABLE BOOKING #${orderModel.table_name}"
                   bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                   if(orderModel.number_of_guest.isNullOrEmpty() || orderModel.number_of_guest == "0" || businessdatadata.invoicenumber_of_guest_box == false){
                       bind.numberOfGuestBox.visibility = View.GONE
                   }else{
                       bind.numberOfGuestBox.visibility = View.VISIBLE
                       bind.numberOfGuest.text = "${businessdatadata.numberOfGuestName} ${orderModel.number_of_guest}"
                       val guestFontSize = (businessdatadata.numberOfGuestFontSize ?: businessdatadata.fontSize ?: 18).toFloat()
                       bind.numberOfGuest.setTextSize(TypedValue.COMPLEX_UNIT_SP, guestFontSize)
                   }

               }else{
//                 bind.orderType.text =  getOrderType()
//                 bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                 if(orderModel.property?.requestedDeliveryTimestampType != null) {
                     if(businessdatadata.printerStyle == "3") {
                         bind.orderType.text =  "${getOrderType()} ${orderModel.property?.requestedDeliveryTimestampType}"
                         bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                     }else{
                         bind.orderType.text =  getOrderType()
                         bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                     }

                   }else{
                       if(businessdatadata.printerStyle == "3") {
                           bind.orderType.text =  "${getOrderType()}"
                           bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                       }else{
                           bind.orderType.text =  getOrderType()
                           bind.orderType.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                       }

                   }
               }
               if (businessdatadata.invoiceorderType == false) {
                   bind.orderType.visibility = View.GONE
                   bind.containerTableNo.visibility = View.GONE
               } else if (businessdatadata.invoicecontainerTableNo == false) {
                   bind.containerTableNo.visibility = View.GONE
               }
               bind.orderTime.text = "Order at : ${parser.parse(orderModel.orderDate)
                   ?.let { formatter.format(it) }}"
               bind.orderTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
               if (businessdatadata.invoiceorderTime == false) {
                   bind.orderTime.visibility = View.GONE
               }
             if(orderModel.orderType == "TABLE_BOOKING") {
                 bind.collectionAt.text = "TABLE BOOKING at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                 bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
             }else{
                 if(orderModel.property?.requestedDeliveryTimestampType != null) {
                     var asapdata = orderModel.property?.requestedDeliveryTimestampType;
                     if(businessdatadata.printerStyle == "3") {
                         bind.collectionAt.setTypeface(null, Typeface.BOLD)
                         bind.collectionAt.text = "REQUESTED at : ${requestformatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))} ${asapdata}"
                         bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                     }else{
                         bind.collectionAt.text = asapdata
                         bind.collectionAt.setTypeface(null, Typeface.BOLD)
                         bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.asapFontSize!!.toFloat())
                     }
//                     var asapdata = orderModel.property?.requestedDeliveryTimestampType;
//                     bind.collectionAt.text = asapdata
//                     bind.collectionAt.setTypeface(null, Typeface.BOLD)
//                     bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.asapFontSize?.toFloat() ?: 16f)

                 }else{
//                     bind.collectionAt.text = "REQUESTED at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
//                     bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())

                     if(businessdatadata.printerStyle == "3") {
                         bind.collectionAt.setTypeface(null, Typeface.BOLD)
                         bind.collectionAt.text = "REQUESTED at : ${requestformatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                         bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                     }else{
                         bind.collectionAt.text = "REQUESTED at : ${formatter.format(parser.parse(orderModel.requestedDeliveryTimestamp))}"
                         bind.collectionAt.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())
                     }
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
                   bind.orderNo.text = "${orderModel.online_order_id}";
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
               if (businessdatadata.invoicecontainerOrderNo == false) {
                   bind.containerOrderNo.visibility = View.GONE
               }
               bind.items.removeAllViews()
               var allitemsheight = 0
               if (businessdatadata.invoiceitems == false) {
                   bind.items.visibility = View.GONE
                   bind.dottedBeforeItems.visibility = View.GONE
               } else {
                   bind.items.visibility = View.VISIBLE
                   var hasItems = false
                   if(businessdatadata.order_group) {
                       val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                       val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.product_group_sort?.toInt() ?: 0 })
                       val groupProduct = sortIteam?.groupBy { it?.product?.property?.product_group }
                       if (!groupProduct.isNullOrEmpty()) {
                           for ((index, entry) in groupProduct.entries.withIndex()) {
                               val key = entry.key
                               val productList = entry.value
                               val childView = groupOrderPrintView(productList, index)
                               bind.items.addView(childView)
                               allitemsheight += childView.measuredHeight
                               hasItems = true
                           }
                       }else{
                           val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                           val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                           if(!sortIteam.isNullOrEmpty()){
                               for (j in sortIteam.indices) {
                                   val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                                   bind.items.addView(childView)
                                   allitemsheight += childView!!.measuredHeight
                                   hasItems = true
                               }
                           }
                       }

                   } else if (businessdatadata.starter_group) {
                       val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                       val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                       val groupProduct = sortIteam?.groupBy { it?.product?.property?.printorder }
                       if (!groupProduct.isNullOrEmpty()) {
                           for ((index, entry) in groupProduct.entries.withIndex()) {
                               val key = entry.key
                               val productList = entry.value
                               val childView = starterGroupView(productList, index)
                               bind.items.addView(childView)
                               allitemsheight += childView.measuredHeight
                               hasItems = true
                           }
                       }else{
                           val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                           val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                           if(!sortIteam.isNullOrEmpty()){
                               for (j in sortIteam.indices) {
                                   val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                                   bind.items.addView(childView)
                                   allitemsheight += childView!!.measuredHeight
                                   hasItems = true
                               }
                           }
                       }
                   }
                   else{
                       val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                       val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                       if(!sortIteam.isNullOrEmpty()){
                           for (j in sortIteam.indices) {
                               val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                               bind.items.addView(childView)
                               allitemsheight += childView!!.measuredHeight
                               hasItems = true
                           }
                       }
                   }

                   if (!hasItems) {
                       bind.items.visibility = View.GONE
                       bind.dottedBeforeItems.visibility = View.GONE
                   } else {
                       bind.dottedBeforeItems.visibility = View.VISIBLE
                   }
               }

             var paidOrNot = "";
             if (orderModel.orderChannel?.uppercase() == "ONLINE") {
                 if(totalRefund > 0.0) {
                     bind.RefundContainer.visibility = View.VISIBLE
                     bind.refund.text = "£ " + String.format("%.2f", totalRefund)
                 }else{
                     bind.RefundContainer.visibility = View.GONE
                 }

                 if(changeAmount > 0.0) {
                     bind.changeContainer.visibility = View.VISIBLE
                     bind.change.text = "£ " + String.format("%.2f", changeAmount)
                 }else{
                     bind.changeContainer.visibility = View.GONE

                 }
                 if(totalRefund > 0.0 || changeAmount > 0.0) {
                     bind.dottedBelowTotal.visibility = View.VISIBLE
                 }else{
                     bind.dottedBelowTotal.visibility = View.GONE
                 }

                 if(orderModel.status?.uppercase() == "REFUNDED") {
                     paidOrNot = "ORDER is REFUNDED"
                     bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                     bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                 }else{
                     if(orderModel.paymentType?.uppercase() == "CARD" || orderModel.paymentType?.uppercase() == "EPOS_CARD"){
                         paidOrNot ="ORDER IS PAID"
                     } else if (orderModel.paymentType?.uppercase() == "BANK") {
                         paidOrNot ="ORDER IS PAID"
                     } else if(orderModel.paymentType?.uppercase() == "CASH" || orderModel.paymentType?.uppercase() == "EPOS_CASH") {
                         var totalpay = (totalCardPaid + totalCashPaid + totalBank) - totalRefund
                         var due = (orderModel.payableAmount ?: 0.0) - totalpay

                         if(totalpay >= (orderModel.payableAmount ?: 0.0)) {
                             paidOrNot ="ORDER IS PAID"
                         }else{
                             paidOrNot = "ORDER NOT PAID"
                             bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                             if(due > 0) {
                                 bind.dueTotal.text = "£ " + String.format("%.2f", due)
                             }else{
                                 bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                             }

                         }
//                         if (orderModel.cashEntry == null || orderModel.cashEntry!!.isEmpty()){
//                             paidOrNot = "ORDER NOT PAID"
//                             bind.dueTotalContainer.visibility = View.VISIBLE
//                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
//                         }else{
//                             paidOrNot ="ORDER IS PAID"
//                         }
                     }else if (orderModel.paymentType?.uppercase() == "CREDIT") {
                         paidOrNot = "ORDER NOT PAID"
                         bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                         bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                     }
                 }
             } else if (orderModel.orderChannel?.uppercase() != "ONLINE") {
                 if(totalRefund > 0.0) {
                     bind.RefundContainer.visibility = if( businessdatadata.refundContainer == true)  View.VISIBLE else View.GONE
                     bind.refund.text = "£ " + String.format("%.2f", totalRefund)
                 }else{
                     bind.RefundContainer.visibility = View.GONE
                 }

                 if(changeAmount > 0.0) {
                     bind.changeContainer.visibility = if( businessdatadata.changeContainer == true)  View.VISIBLE else View.GONE
                     bind.change.text = "£ " + String.format("%.2f", changeAmount)
                 }else{
                     bind.changeContainer.visibility = View.GONE
                 }
                 if(totalRefund > 0.0 || changeAmount > 0.0) {
                     bind.dottedBelowTotal.visibility = View.VISIBLE
                 }else{
                     bind.dottedBelowTotal.visibility = View.GONE
                 }

                 if(orderModel.status?.uppercase() == "REFUNDED") {
                     paidOrNot = "ORDER is REFUND"
                     bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE

                     bind.dueTotal.text = "£ 0.0"

                 } else if(totalRefund > 0.0) {
                     paidOrNot = "ORDER is PARTIAL REFUND"
                     bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                     bind.dueTotal.text = "£ 0.0"
                 } else{
                     if(orderModel.cashEntry != null && orderModel.cashEntry!!.isNotEmpty()) {
//                         paidOrNot ="ORDER IS PAID"
//                         bind.dueTotal.text = "£ 0.0"
                         var totalpay = (totalCardPaid + totalCashPaid + totalBank) - totalRefund
                         var due = (orderModel.payableAmount ?: 0.0) - totalpay
                         if(totalpay >= (orderModel.payableAmount ?: 0.0)) {
                             paidOrNot ="ORDER IS PAID"
                         }else{
                             paidOrNot = "ORDER NOT PAID"
                             bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                             if(due > 0) {
                                 bind.dueTotal.text = "£ " + String.format("%.2f", due)
                             }else{
                                 bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                             }
                         }
                     }else{
                         if(orderModel.paymentType?.uppercase() == "UNPAID_CASH") {
                             paidOrNot ="ORDER IS UNPAID(CASH)"
                             bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                                 //"£ " + String.format("%.2f", orderModel.payableAmount)
                         }else if(orderModel.paymentType?.uppercase() == "UNPAID_CARD") {
                             paidOrNot ="ORDER IS UNPAID(CARD)"
                             bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                                 //"£ " + String.format("%.2f", orderModel.payableAmount)
                         }else{
                             paidOrNot = "ORDER NOT PAID"
                             bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                             bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                                 //"£ " + String.format("%.2f", orderModel.payableAmount)
                         }
                     }
                 }

             } else  {
                 paidOrNot = "ORDER NOT PAID"
                 bind.dueTotalContainer.visibility = if( businessdatadata.dueTotalContainer == true)  View.VISIBLE else View.GONE
                 bind.dueTotal.text = "£ " + String.format("%.2f", orderModel.payableAmount)
                     //"£ " + String.format("%.2f", orderModel.payableAmount)
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
               if (businessdatadata.invoiceorderPaidMessage == false) {
                   bind.orderPaidMessage.visibility = View.GONE
               } else {
                   bind.orderPaidMessage.text = paidOrNot
                   bind.orderPaidMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())
               }
//             bind.refundContainer.visibility = View.GONE
             var subTotal: Double = 0.0
             if(orderModel.orderChannel == "ONLINE") {
                 subTotal = (orderModel.netAmount ?: 0.0) - (orderModel.promotion_discount_amount?.toDoubleOrNull() ?: 0.0) ;
             }else{
                 subTotal = (orderModel.netAmount ?: 0.0)
             }
             bind.subTotal.text = "£ " + String.format( "%.2f", subTotal)
             bind.subTotal2.text = "Sub Total: £ " + String.format( "%.2f", subTotal)
             bind.serviceChage.text = "£ " + String.format("%.2f", orderModel.serviceCharge ?: 0.0)
             bind.tips.text = "£ " + String.format("%.2f", orderModel.tips ?: 0.0)
             if(orderModel.orderType == "DELIVERY") {
                 bind.deliveryChargeContainer.visibility = if( businessdatadata.deliveryChargeContainer == true)  View.VISIBLE else View.GONE
                 bind.txtDeliveryCharge.text = "Delivery Charge";
                 bind.deliveryCharge.text = "£ " + orderModel.deliveryCharge!!.toFloat().toString()
             }else{
                 bind.deliveryChargeContainer.visibility = View.GONE
             }
             bind.change.text = "£ " +  String.format( "%.2f",  orderModel.changeAmount)
             if(totalCardPaid > 0) {
                 bind.cardPayContainer.visibility =  if( businessdatadata.cardPayContainer == true)  View.VISIBLE else View.GONE
                 bind.cardPay.text = "£ " + String.format( "%.2f",  totalCardPaid)
             } else{
                 bind.cardPayContainer.visibility = View.GONE
             }
             if(totalCashPaid > 0) {
                 bind.cashPayContainer.visibility =  if( businessdatadata.cashPayContainer == true)  View.VISIBLE else View.GONE
                 bind.cashPay.text = "£ " + String.format( "%.2f",  totalCashPaid)
             }else{
                 bind.cashPayContainer.visibility = View.GONE
             }

             if(totalBank > 0) {
                 bind.bankPayContainer.visibility =  if( businessdatadata.bankPayContainer == true)  View.VISIBLE else View.GONE
                 bind.bankPay.text = "£ " + String.format( "%.2f",  totalBank)
             }else{
                 bind.bankPayContainer.visibility = View.GONE
             }
             bind.dottedBetweenPayments.visibility =
                 if (bind.cardPayContainer.visibility == View.VISIBLE ||
                     bind.cashPayContainer.visibility == View.VISIBLE ||
                     bind.dueTotalContainer.visibility == View.VISIBLE
                 ) {
                     View.VISIBLE
                 } else {
                     View.GONE
                 }


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
//             bind.promodiscount.text =
//                 "£ " + String.format( "%.2f", orderModel.promotion_discount_amount?.toFloatOrNull() ?: 0.0)

            if(orderModel.vat_amount == 0.0) {
                bind.vatContainer.visibility = View.GONE
            }else{
                 bind.vatContainer.visibility =  if( businessdatadata.vatContainer == true)  View.VISIBLE else View.GONE
                 bind.vatAmount.text = "£ " + String.format( "%.2f", orderModel.vat_amount)
             }
             bind.total.text =
                 "£ " +String.format( "%.2f",(orderModel.payableAmount!!))
             bind.grandTotal.text =
                 "Grand Total £ " +String.format( "%.2f",(orderModel.payableAmount!!))

             var dlAddress = "Service charge is not included\n\n"
             if(businessdatadata.serviceCharge) {
                 dlAddress = "${businessdatadata.serviceChargeMessage ?: ""}"
             }else{
                 dlAddress = ""
             }
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


               if (businessdatadata.invoicecomments == false || comment.isBlank()) {
                   bind.comments.visibility = View.GONE
                   bind.dottedBeforeComments.visibility = View.GONE
               } else {
                   bind.comments.visibility = View.VISIBLE
                   bind.dottedBeforeComments.visibility = View.VISIBLE
                   bind.comments.text = comment
                   bind.comments.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())
               }
               if (businessdatadata.invoiceaddress == false || dlAddress.isBlank()) {
                   bind.address.visibility = View.GONE
                   bind.dottedBeforeAddress.visibility = View.GONE
               } else {
                   bind.address.visibility = View.VISIBLE
                   bind.dottedBeforeAddress.visibility = View.VISIBLE
                   bind.address.text = dlAddress
                   bind.address.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())
               }



             bind.serviceChargeMessage.text = businessdatadata.serviceChargeMessage

             bind.ThankYouMessage.text = businessdatadata.thankyoumessage


             if(!businessdatadata.vatNumber.isNullOrEmpty() || !businessdatadata.vatCompanyName.isNullOrEmpty()) {
                 bind.vatNumberCompany.text = "${businessdatadata.vatNumber ?: ""}"+"${businessdatadata.vatCompanyName ?: ""}"
                 bind.vatno2.text = "${businessdatadata.vatNumber}"
                 bind.vatMessage.text = "${businessdatadata.vatCompanyName}"
                 bind.vatno2.setTextSize(TypedValue.COMPLEX_UNIT_SP, footervatFontSize.toFloat())
                 bind.vatMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, footervatFontSize.toFloat())
                 bind.vatNumberCompany.visibility = View.VISIBLE
                 bind.vatNumberCompany.setTextSize(TypedValue.COMPLEX_UNIT_SP, footervatFontSize.toFloat())
             }else{
                 bind.vatNumberCompany.visibility = View.GONE
                 bind.vatno2.visibility = View.GONE
             }
             if(!businessdatadata.vatNumber.isNullOrEmpty()) {
                 bind.vatno2.visibility = View.VISIBLE;
             }else{
                 bind.vatno2.visibility = View.GONE;
             }

             if(!businessdatadata.vatCompanyName.isNullOrEmpty()) {
                 bind.vatMessage.visibility = View.VISIBLE;
             }else{
                 bind.vatMessage.visibility = View.GONE;
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

         fun groceryInvoice() : Bitmap {
             noofprint = if (orderModel.orderType == "DELIVERY"){
                 businessdatadata.printOnDelivery!!
             }else{
                 businessdatadata.printOnCollection!!
             }

             val printSize: Int = fontsize
             val bind: GroceryinvoiceBinding = GroceryinvoiceBinding.inflate(LayoutInflater.from(context))
             bind.businessName.text = businessname
             bind.businessName.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
             bind.emailaddress.text = businessdatadata.business_email
             bind.emailaddress.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())

             val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
             val formatter = SimpleDateFormat("dd/mm/yyyy HH:mm:ss")

             var totalRefund : Double = 0.0;
             var totalCardPaid : Double = 0.0;
             var totalCashPaid: Double = 0.0;
             var totalReceivePound : Double = 0.0
             // var totalDue : Double = 0.0;
             val refundList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "REFUND"}
             var cardPaidList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "EPOS_CARD" || it?.type?.uppercase() == "CARD"}
             var cashPaidList = orderModel.cashEntry?.filter { it?.type?.uppercase() == "EPOS_CASH" || it?.type?.uppercase() == "CASH"}

             val receivePoundList = orderModel.cashEntry?.filter { it?.type?.uppercase() != "REFUND"}
             val changeAmount : Double = orderModel.changeAmount ?: 0.0;
             refundList?.forEach {
                 totalRefund += (it?.amount ?: 0.0)
             }
             cardPaidList?.forEach {
                 totalCardPaid += (it?.amount?: 0.0)
             }
             cashPaidList?.forEach {
                 totalCashPaid += (it?.amount?: 0.0)
             }
             receivePoundList?.forEach {
                 totalReceivePound += (it?.amount ?: 0.0)
             }
             // totalDue = (orderModel.payableAmount ?: 0.0) - (totalReceivePound - (orderModel.changeAmount ?: 0.0))
             var addedDeliveryCharge = 0.0
             bind.businessLocation.text = businessaddress
             bind.businessLocation.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
             bind.businessPhone.text = businessphone
             bind.businessPhone.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
             if(businessdatadata.branchNameShow) {
                 bind.branchName.text = orderModel.branch?.name?.uppercase()
                 bind.branchName.setTextSize(TypedValue.COMPLEX_UNIT_SP, header1.toFloat())
             }else{
                 bind.branchName.visibility = View.GONE
             }


             bind.orderTime.text = "${parser.parse(orderModel.orderDate)
                 ?.let { formatter.format(it) }}"
             bind.orderTime.setTextSize(TypedValue.COMPLEX_UNIT_SP, header2.toFloat())



             bind.items.removeAllViews()
             var allitemsheight = 0
             if(businessdatadata.order_group) {
                 val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                 val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.product_group_sort?.toInt() ?: 1 })
                 val groupProduct = sortIteam?.groupBy { it?.product?.property?.product_group }
                 if (groupProduct != null) {
                     for ((index, entry) in groupProduct.entries.withIndex()) {
                         val key = entry.key
                         val productList = entry.value
                         val childView = groupOrderPrintView(productList, index)
                         bind.items.addView(childView)
                         allitemsheight += childView.measuredHeight
                     }
                 }else{
                     val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                     val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                     if(!sortIteam.isNullOrEmpty()){
                         for (j in sortIteam.indices) {
                             val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                             bind.items.addView(childView)
                             allitemsheight += childView!!.measuredHeight
                         }
                     }
                 }

             }else{
                 val itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                 val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                 if(!sortIteam.isNullOrEmpty()){
                     for (j in sortIteam.indices) {
                         val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                         bind.items.addView(childView)
                         allitemsheight += childView!!.measuredHeight
                     }
                 }
             }



             val subTotal: Double = orderModel.netAmount ?: 0.0
             bind.subTotal.text = "£ " + String.format( "%.2f", subTotal)

             if(totalCardPaid > 0) {
                 bind.cardPayContainer.visibility = View.VISIBLE
                 bind.cardPay.text = "£ " + String.format( "%.2f",  totalCardPaid)
             } else{
                 bind.cardPayContainer.visibility = View.GONE
             }
             if(totalCashPaid > 0) {
                 bind.cashPayContainer.visibility = View.VISIBLE
                 bind.cashPay.text = "£ " + String.format( "%.2f",  totalCashPaid)
             }else{
                 bind.cashPayContainer.visibility = View.GONE
             }


             bind.total.text =
                 "£ " +String.format( "%.2f",(orderModel.payableAmount!!))


             applyGrocerySummaryVisibility(bind)
             if(!businessdatadata.vatNumber.isNullOrEmpty() || !businessdatadata.vatCompanyName.isNullOrEmpty()) {
                 bind.vatNumberCompany.text = "VAT Number: ${businessdatadata.vatNumber}"

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


             var widthd = businessdatadata.grocery_barcode_width;
             var heightd = businessdatadata.grocery_barcode_hight;
             var barcodeBitmap = barcodeSetting().generateBarcode("A${orderModel.orderUniqID}",businessdatadata.barcode_dpi,widthd, heightd)    // genBarcode2(barcode)
             val imageView = ImageView(context).apply {
                 setImageBitmap(barcodeBitmap)

                 // Set layout parameters if needed (e.g., dynamic width and height)
                 layoutParams = ViewGroup.LayoutParams(
                     ViewGroup.LayoutParams.WRAP_CONTENT,
                     ViewGroup.LayoutParams.WRAP_CONTENT
                 )
             }
//                var layoutParams = ViewGroup.LayoutParams(
//                         businessdatadata.barcode_width?: 400,
//                    businessdatadata.barcode_hight?: 100,
//                     )
             bind.barcodeItem.removeAllViews()
             bind.barcodeItem.addView(imageView)

             if(businessdatadata.barcode_text_show) {
                 bind.barcodeValue.visibility = View.VISIBLE
                 bind.barcodeValue.text = "A${orderModel.orderUniqID}"
                 bind.barcodeValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.barcode_text_size.toFloat())
             }else{
                 bind.barcodeValue.visibility = View.GONE
             }

             val bitmaplist: Bitmap =  getBitmapFromView(bind.root)

             return  bitmaplist;
         }


         fun groupOrderPrintView(itemproduict: List<OrderData.OrderProduct?>?,  index : Int) : View {
             val bind: GroupPrintViewBinding = GroupPrintViewBinding.inflate(LayoutInflater.from(context))
             if(businessdatadata.groupHeaderShow) {
                 bind.groupHeader.visibility = View.VISIBLE
                 bind.groupHeader.text = "${itemproduict?.first()?.product?.property?.product_group?.uppercase()}"
                 bind.groupHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
             }else{
                 bind.groupHeader.visibility = View.GONE
                 if(index == 0) {
                     bind.dotted.visibility = View.GONE
                 }
             }

             val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
             bind.groupItem.removeAllViews()
             var allitemsheight = 0
             if(!sortIteam.isNullOrEmpty()){
                 for (j in sortIteam.indices) {
                     val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j,)
                     bind.groupItem.addView(childView)
                     allitemsheight += childView!!.measuredHeight
                 }
             }
             return  bind.root
         }


         fun getStarterName(printerorder : String) : String {
             if(printerorder == "1") {
                 return "Set Meal";
             } else if(printerorder == "2") {
                 return "Starter";
             } else if (printerorder == "3") {
                 return "Main Dish";
             } else if (printerorder == "4") {
                 return  "Side Dish";
             } else if (printerorder == "5") {
                 return  "Sundries";
             } else{
                 return  "Others";
             }
         }


         fun starterGroupView(itemproduict: List<OrderData.OrderProduct?>?, index : Int) : View {
             val bind: GroupPrintViewBinding = GroupPrintViewBinding.inflate(LayoutInflater.from(context))
             if(businessdatadata.groupHeaderShow) {
                 bind.groupHeader.visibility = View.VISIBLE
                 bind.groupHeader.text = getStarterName(itemproduict?.first()?.product?.property?.printorder ?: "0")
                 bind.groupHeader.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
             }else{
                 bind.groupHeader.visibility = View.GONE
                 if(index == 0) {
                     bind.dotted.visibility = View.GONE
                 }
             }

             val sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
             bind.groupItem.removeAllViews()
             var allitemsheight = 0
             if(!sortIteam.isNullOrEmpty()){
                 for (j in sortIteam.indices) {
                     val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                     bind.groupItem.addView(childView)
                     allitemsheight += childView!!.measuredHeight
                 }
             }
             return  bind.root
         }


         @SuppressLint("DefaultLocale")
         fun ButcherOrderPrint() : Bitmap {
             if(businessdatadata.butcherPrintStyle == "1") {
               return  butcherOrderPrintStyle1()
             }else{
               return  butcherOrderPrintStyle2()
             }

         }

         fun butcherOrderPrintStyle1() : Bitmap {
             if(businessdatadata.selectPrinter!!.lowercase() == "label_printer" && orderModel.orderProducts != null && orderModel.orderProducts!!.isNotEmpty()) {
                 val bind: StickerprinterBinding = StickerprinterBinding.inflate(LayoutInflater.from(context))

//                 val dpi = businessdatadata.dpi ?: 203
//                 val widthMm = businessdatadata.label_width ?: 76
//                 val heightMm = businessdatadata.label_hight ?: 76
//                 // Convert mm to pixels
//                 val widthPx = (widthMm * dpi / 25.4f).toInt()
//                 val heightPx = (heightMm * dpi / 25.4f).toInt()
//                 val layoutParamsd = ViewGroup.LayoutParams(
//                     ViewGroup.LayoutParams.WRAP_CONTENT,
//                     ViewGroup.LayoutParams.WRAP_CONTENT
//                 )
//                 layoutParamsd.width = widthPx
//                 layoutParamsd.height = heightPx
//                 bind.root.layoutParams = layoutParamsd


                 val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                 val formatter1 = SimpleDateFormat("yyyy.MM.dd")
                 val formatter2 = SimpleDateFormat("HH:mm a")
                 var unitName = orderModel.orderProducts?.first()?.product?.property?.unit_of_sale
                 var item = orderModel.orderProducts?.first();
                 var  component: List<OrderData.OrderProduct.Component?>?
                 var  extraIteam: List<OrderData.OrderProduct.Component?>? = ArrayList()
                 if(orderModel.orderChannel?.uppercase() == "ONLINE") {
                     component = item?.components;
                 } else {
                     component = item?.components?.filter {i-> componentFilter(i)}
                     extraIteam = item?.components?.filter { i-> i?.product?.type == "EXTRA-COMPONENT"}
                 }


                 bind.itemName.text = orderModel.orderProducts?.first()?.product?.shortName
                 bind.itemName.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.item_name_font?.toFloat() ?: 22f)


                 bind.priceValue.text = "Price Per ${unitGet(item)} - £${orderModel.orderProducts?.first()?.netAmount.toString()}"
                 bind.priceValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.price_per_weight_font?.toFloat() ?: 22f)


                 bind.netwtvalue.text = "Weight - ${orderModel.orderProducts?.first()?.product?.property?.unit_amount} ${unitGet(item)}"
                 bind.netwtvalue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.item_weight_font?.toFloat() ?: 22f)

                 bind.businessName.text = businessname
                 bind.businessName.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.business_name_font?.toFloat() ?: 22f)

                 var price = 0.0
                 var tareWeight : Double = if(item?.product?.property?.tare_weight?.isEmpty() == true) {
                     0.0;
                 }else{
                     item?.product?.property?.tare_weight?.toDouble() ?: 0.0
                 }
                 var unitAmount = if(item?.product?.property?.unit_amount?.isEmpty() == true) {
                     0.0
                 } else{
                     item?.product?.property?.unit_amount?.toDouble() ?: 0.0
                 }

                 price = if(item?.product?.property?.unit_product_type?.uppercase() == "WEIGHT") {
                     if(businessdatadata.weightMultiplyingPrice) {
                         (item.netAmount ?: 0.0) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight)
                     }else{
                         (item.netAmount ?: 0.0)
                     }

                 }else{
                     (item?.netAmount ?: 0.0)
                 }

                 var discount = item?.discountableAmount ?: 0.0;

                 if (!component.isNullOrEmpty()) {
                     for (section in component) {
                         if ((section?.components != null) && section.components.isNotEmpty()) {
                             for (section2 in section.components) {
                                 if (section2?.product?.shortName?.uppercase() != "NONE") {
                                     price += ((section2?.netAmount ?: 0.0) * (section2?.unit ?: 1));
                                 }
                             }

                         }

                         price += section?.netAmount ?: 0.0;
                     }
                 }

                 if (extraIteam != null) {
                     if (extraIteam.isNotEmpty()) {
                         for (extraItem in extraIteam) {
                             price += extraItem?.netAmount!!;
                         }
                     }
                 }


                 if(orderModel.orderChannel?.uppercase() != "ONLINE"){
                     if (item?.offer?.offer?.type == "X_FOR_Y" && item?.offer?.offer?.status == 1) {
                         var p = String.format("%.2f", getOrderOfferPrice(item).toDouble())
                         price *=  p.toDouble()
                     }else if (item?.offer?.offer?.type == "X_FOR_£" && item?.offer?.offer?.status == 1) {
                         var p = String.format("%.2f", xForPoundOfferLocalDetailOrder(item, orderModel.orderProducts))
                         Log.e("price get", "getView: ${p}----")
                         price =  p.toDouble()
                     }else{
                         price *= (item?.unit ?: 1)
                     }

                     var totaldiscount = (price * (discount / 100))
                     price -= totaldiscount;
                 }

                 bind.totalvalue.text = "Total Price £${String.format("%.2f", price)}"
                 bind.totalvalue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.item_price_font?.toFloat() ?: 22f)


                 if(!orderModel.bestbeforeDate.isNullOrEmpty() ) {
                     bind.expire.text = "${businessdatadata.expire_name} ${orderModel.bestbeforeDate}"
                     bind.expire.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.expire_date_font?.toFloat() ?: 22f)

                 }else{
                     bind.expirecontainer.visibility = View.GONE
                 }
                 val productId = orderModel.orderProducts?.first()?.id.toString().padStart(businessdatadata.yuma_productid_length, '0')
                 val netAmount = "${String.format("%.2f", orderModel.orderProducts?.first()?.netAmount ?: 0.0)}".replace(".", "").padStart(businessdatadata.yuma_netamount_length, '0')  // "0200"
                 val weight = "${orderModel.orderProducts?.first()?.product?.property?.unit_amount ?: 0}".replace(".", "").padStart(businessdatadata.yuma_weight_length, '0')  // "05"
                 val totalPrice = "${String.format("%.2f", price)}".replace(".", "").padStart(businessdatadata.yuma_totalprice_length, '0')

                 //barcode = "${orderModel.orderProducts?.first()?.id}-${netamount}-${orderModel.orderProducts?.first()?.product?.property?.unit_amount ?: 0}-${totalPrice}";

                 barcode = "${productId}${netAmount}${weight}${totalPrice}";


                 if(businessdatadata.barcode_text_show) {
                     bind.barcodeValue.visibility = View.VISIBLE
                     bind.barcodeValue.text = barcode
                     bind.barcodeValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.barcode_text_size.toFloat() ?: 15f)
                 }else{
                     bind.barcodeValue.visibility = View.GONE
                 }


                 var widthd = businessdatadata.barcode_width ?: 250;
                 var heightd = businessdatadata.barcode_hight ?: 100;
                 var barcodeBitmap = barcodeSetting().generateBarcode(barcode,businessdatadata.barcode_dpi,widthd, heightd)    // genBarcode2(barcode)
                 val imageView = ImageView(context).apply {
                     setImageBitmap(barcodeBitmap)

                     // Set layout parameters if needed (e.g., dynamic width and height)
                     layoutParams = ViewGroup.LayoutParams(
                         ViewGroup.LayoutParams.WRAP_CONTENT,
                         ViewGroup.LayoutParams.WRAP_CONTENT
                     )
                 }
//                var layoutParams = ViewGroup.LayoutParams(
//                         businessdatadata.barcode_width?: 400,
//                    businessdatadata.barcode_hight?: 100,
//                     )
                 bind.items.removeAllViews()
                 bind.items.addView(imageView)
//                 bind.barcode.setImageBitmap(barcodeBitmap)

//                 var p = "Price/${unitGet(item)} ${orderModel.orderProducts?.first()?.netAmount.toString()}";
//                 var net = "Net: ${orderModel.orderProducts?.first()?.product?.property?.unit_amount ?: 0} ${unitGet(item)}"
//                 var t = "Total: ${String.format("%.2f", price)}"
//                 var ex= if(item?.product?.property?.expire_date == null) "" else "Exp: ${item?.product?.property?.expire_date}";
//
                 val bitmaplist: Bitmap =  getBitmapFromView(bind.root)
                 return  bitmaplist


             } else{
                 val printSize: Int = fontsize
                 val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                 val formatter = SimpleDateFormat("dd-MMM hh:mm a")
                 val bind: ButcherOrderPrintBinding = ButcherOrderPrintBinding.inflate(LayoutInflater.from(context))
                 bind.orderNo.text = "${orderModel.localId}"
                 bind.totalValue.text = "£ " +String.format( "%.2f",(orderModel.payableAmount!!))
                 bind.Date.text = "Date : ${parser.parse(orderModel.orderDate)
                     ?.let { formatter.format(it) }}"
                 bind.businessName.text = businessdatadata.businessname
                 var allitemsheight = 0
                 bind.items.removeAllViews()
                 var itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
                 var sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
                 if(!sortIteam.isNullOrEmpty()){
                     for (j in sortIteam.indices) {
                         val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j,)
                         bind.items.addView(childView)
                         allitemsheight += childView!!.measuredHeight
                     }
                 }
                 var dlAddress = ""

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

                 bind.address.text = dlAddress
                 bind.address.setTextSize(TypedValue.COMPLEX_UNIT_SP, header4.toFloat())

                 if(orderModel.barcode != null) {
                     var barcodeBitmap = genBarcode(orderModel.barcode!!)
                     bind.barcode.setImageBitmap(barcodeBitmap)
                     if(businessdatadata.barcode_text_show) {
                         bind.barcodeValue.visibility = View.VISIBLE
                         bind.barcodeValue.text = orderModel.barcode
                         bind.barcodeValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.barcode_text_size.toFloat() ?: 15f)
                     }else{
                         bind.barcodeValue.visibility = View.GONE
                     }

                 }
                 val bitmaplist: Bitmap =  getBitmapFromView(bind.root)
                 return  bitmaplist
             }
         }


//         fun butcherItemgetViewStyle2(listorderProducts: List<OrderData.OrderProduct?>?, item: OrderData.OrderProduct?): View? {
//             val binding: ModelPrint2Binding = ModelPrint2Binding.inflate(LayoutInflater.from(context))
//             var  component: List<OrderData.OrderProduct.Component?>?
//             var  extraIteam: List<OrderData.OrderProduct.Component?>? = ArrayList()
//             if(orderModel.orderChannel?.uppercase() == "ONLINE") {
//                 component = item?.components;
//             } else {
//                 component = item?.components?.filter {i-> componentFilter(i)}
//                 //   extraIteam = item?.components?.filter { i-> i?.product?.property?.itemtype != null && (i.product.property.itemtype?.lowercase() == "topping" || i.product.property.itemtype?.lowercase() == "addon" || i.product.property.itemtype?.lowercase() == "dressing")}
//                 extraIteam = item?.components?.filter { i-> i?.product?.type == "EXTRA-COMPONENT"}
//
//             }
//
//
//             val str3 = StringBuilder()
//             var price = 0.0
//             var tareWeight : Double = if(item?.product?.property?.tare_weight?.isEmpty() == true) {
//                 0.0;
//             }else{
//                 item?.product?.property?.tare_weight?.toDouble() ?: 0.0
//             }
//             var unitAmount = if(item?.product?.property?.unit_amount?.isEmpty() == true) {
//                 0.0
//             } else{
//                 item?.product?.property?.unit_amount?.toDouble() ?: 0.0
//             }
//
//
//             if(unitAmount == 0.0) {
//                 binding.unitValue.visibility = View.GONE
//             }else{
//                 if(businessdatadata.weightShow) {
//                     binding.unitValue.visibility = View.VISIBLE
//                     binding.unitValue.text = "${unitAmount} ${unitGet(item)}"
//                     binding.unitValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, (header3.toFloat() - 5))
//                 } else {
//                     binding.unitValue.visibility = View.GONE
//                 }
//
//             }
//             if(item?.product?.property?.unit_product_type?.uppercase() == "WEIGHT") {
//                 if(businessdatadata.weightMultiplyingPrice) {
//                     price = (item.netAmount ?: 0.0) * ((if (unitAmount == 0.0) 1.0 else unitAmount) - tareWeight)
//                 }else{
//                     price = (item.netAmount ?: 0.0)
//                 }
//
//             }else{
//                 price = (item?.netAmount ?: 0.0)
//             }
//
//             var discount = item?.discountableAmount ?: 0.0;
//
//             if (!component.isNullOrEmpty()) {
//                 str3.append(item?.unit).append("x ").append(item?.product?.shortName)
//                 for (section in component) {
//                     var _comName = ""
//                     if (section?.product?.shortName?.uppercase() != "NONE") {
//                         _comName = section?.product?.shortName ?: ""
//                     }
//                     if ((section?.components != null) && section.components.isNotEmpty()) {
//                         for (section2 in section.components) {
//                             if (section2?.product?.shortName?.uppercase() != "NONE") {
//                                 _comName += " -> " + "${section2?.unit ?: 1}x " + section2?.product?.shortName;
//                                 price += ((section2?.netAmount ?: 0.0) * (section2?.unit ?: 1));
//                             }
//                         }
//
//                     }
//                     if (_comName != "") {
//                         if(businessdatadata.printerStyle == "1") {
//                             str3.append("\n").append(_comName)
//                         }else{
//                             str3.append(" -> ").append(_comName)
//                         }
//
//                     }
//                     price += section?.netAmount ?: 0.0;
//                 }
//             } else {
//                 if (item?.product?.type == "ITEM" || item?.product?.type == "DYNAMIC"){
//                     str3.append(item.unit).append("x ").append(item.product.shortName)
//                     if(businessdatadata.printerStyle == "2"){
//                         if(item.product.property?.printorder == "2"){
//                             str3.append("(Str)")
//                         }
//                     }
//                 }
//
//             }
//
//             if (extraIteam != null) {
//                 val topping = StringBuilder()
//                 if (extraIteam.isNotEmpty()) {
//                     if(businessdatadata.printerStyle == "1") {
//                         topping.append("\n")
//                     }else{
//                         topping.append(" -> ")
//                     }
////                val topping = java.lang.StringBuilder("\n")
//                     for (extraItem in extraIteam) {
//                         topping.append("  *").append(extraItem?.product?.shortName)
//                         price += extraItem?.netAmount!!;
//                     }
//                     str3.append(topping.toString())
//                 }
//             }
//
//
//             if(orderModel.orderChannel?.uppercase() != "ONLINE"){
////            price *= (item?.unit ?: 1)
//                 if (item?.offer?.offer?.type == "X_FOR_Y" && item?.offer?.offer?.status == 1) {
//                     var p = String.format("%.2f", getOrderOfferPrice(item))
//                     price *=  p.toDouble()
//                 }else if (item?.offer?.offer?.type == "X_FOR_£" && item?.offer?.offer?.status == 1) {
//                     var p = String.format("%.2f", xForPoundOfferLocalDetailOrder(item, listorderProducts))
//                     Log.e("price get", "getView: ${p}----")
//                     price =  p.toDouble()
//                 }else{
//                     price *= (item?.unit ?: 1)
//                 }
//
//                 var totaldiscount = (price * (discount / 100))
//                 price -= totaldiscount;
//             }
//             Log.e("price get", "getView: ${price}----")
//             if(item?.comment != null && (item.product?.type == "ITEM" || item.product?.type == "DYNAMIC")) str3.append("\nNote : ").append(item.comment)
//             binding.itemText.text = str3.toString()
//             binding.itemText.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
//             if(item?.product?.type == "ITEM" || item?.product?.type == "DYNAMIC"){
//                 binding.itemPrice.text = "£ ${String.format("%.2f", price)}"
//             } else{
//                 binding.itemPrice.visibility = View.GONE
//             }
//             binding.itemPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, header3.toFloat())
//             binding.root.buildDrawingCache(true)
//
//             return binding.root
//         }
//


         @SuppressLint("DefaultLocale")
         fun butcherOrderPrintStyle2(): Bitmap {
             val bind: ButcherOrderStyle2Binding = ButcherOrderStyle2Binding.inflate(LayoutInflater.from(context))
             val printSize: Int = fontsize

             var allitemsheight = 0
             bind.items.removeAllViews()
             var itemproduict = orderModel.orderProducts?.filter { i-> i?.product?.type == "ITEM" || i?.product?.type == "DYNAMIC" }
             var sortIteam = itemproduict?.sortedWith(compareBy {it?.product?.property?.printorder?.toInt() ?: 0 })
             if(!sortIteam.isNullOrEmpty()){
                 for (j in sortIteam.indices) {
//                     val childView = butcherItemgetViewStyle2(sortIteam,sortIteam[j])
                     val childView = getView(sortIteam, sortIteam[j],sortIteam.size, j)
                     bind.items.addView(childView)
                     allitemsheight += childView!!.measuredHeight
                 }
             }

             if(barcodePrint == true) {
                 bind.totalPrice.text = "Total Price £${String.format("%.2f", orderModel.payableAmount)}"
                 bind.totalPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.butcherStickerFont.toFloat() ?: 22f)
             }else{
                 bind.totalPrice.visibility = View.GONE
             }
             bind.businessName.text = businessname
             bind.businessName.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.business_name_font?.toFloat() ?: 22f)




             if(barcodePrint == true && orderModel.barcode != null) {
                 var widthd = businessdatadata.barcode_width ?: 250;
                 var heightd = businessdatadata.barcode_hight ?: 100;
                 var barcodeBitmap =  barcodeSetting().generateBarcode(orderModel.barcode!!, businessdatadata.barcode_dpi, widthd, heightd)   //genBarcode2(orderModel.barcode!!)
                 val imageView = ImageView(context).apply {
                     setImageBitmap(barcodeBitmap)

                     layoutParams = ViewGroup.LayoutParams(
                         ViewGroup.LayoutParams.WRAP_CONTENT,
                         ViewGroup.LayoutParams.WRAP_CONTENT
                     )
                 }
                 bind.barcode.removeAllViews()
                 bind.barcode.addView(imageView)
                 if(businessdatadata.barcode_text_show) {
                     bind.barcodeValue.visibility = View.VISIBLE
                     bind.barcodeValue.text = orderModel.barcode
                     bind.barcodeValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, businessdatadata.barcode_text_size.toFloat() ?: 15f)
                 }else{
                     bind.barcodeValue.visibility = View.GONE
                 }


                 }else{
                 bind.barcode.removeAllViews()
             }

             val bitmaplist: Bitmap =  getBitmapFromView(bind.root)
             return  bitmaplist
         }

         private fun View.setDimensionsInMillimeters() {
             val dpi = businessdatadata.dpi ?: 203
             val widthMm = businessdatadata.label_width ?: 76
             val heightMm = businessdatadata.label_hight ?: 76
             // Convert mm to pixels
             val widthPx = (widthMm * dpi / 25.4f).toInt()
             val heightPx = (heightMm * dpi / 25.4f).toInt()

             val layoutParams = this.layoutParams
             layoutParams.width = widthPx
             layoutParams.height = heightPx
             this.layoutParams = layoutParams
         }
         override fun doInBackground(vararg params: String?): Bitmap {
             if(businessdatadata.orderChannel?.uppercase() == "BUTCHER") {
                 val ButcherorderBitmap = ButcherOrderPrint()
                 return ButcherorderBitmap;
             }else{
                 if(businessdatadata.invoice_type == "GROCERY") {
                     val orderBitmap =  groceryInvoice()
                     return orderBitmap
                 }else{
                     val orderBitmap = eposWaiterorderPrint()
                     return orderBitmap
                 }

             }
         }


         private fun applyOrderSummaryVisibility(bind: OnlinePrint2Binding) {
             bind.subTotalContainer.hideIfDisabled(businessdatadata.subTotalContainer)
             bind.deliveryChargeContainer.hideIfDisabled(businessdatadata.deliveryChargeContainer)
             bind.discountContainer.hideIfDisabled(businessdatadata.discountContainer)
             bind.plasticBagContainer.hideIfDisabled(businessdatadata.plasticBagContainer)
             bind.containerBagContainer.hideIfDisabled(businessdatadata.containerBagContainer)
             bind.adjustmentContainer.hideIfDisabled(businessdatadata.adjustmentContainer)
             bind.vatContainer.hideIfDisabled(businessdatadata.vatContainer)
             bind.serviceChargeContainer.hideIfDisabled(businessdatadata.serviceChargeContainer)
             bind.tipsContainer.hideIfDisabled(businessdatadata.tipsContainer)
             bind.totalContainer.hideIfDisabled(businessdatadata.totalContainer)
             bind.RefundContainer.hideIfDisabled(businessdatadata.refundContainer)
             bind.changeContainer.hideIfDisabled(businessdatadata.changeContainer)
             bind.cardPayContainer.hideIfDisabled(businessdatadata.cardPayContainer)
             bind.cashPayContainer.hideIfDisabled(businessdatadata.cashPayContainer)
             bind.bankPayContainer.hideIfDisabled(businessdatadata.bankPayContainer)
             bind.dueTotalContainer.hideIfDisabled(businessdatadata.dueTotalContainer)

             bind.dottedBetweenPayments.visibility =
                 if (bind.cardPayContainer.visibility == View.VISIBLE ||
                     bind.cashPayContainer.visibility == View.VISIBLE ||
                     bind.dueTotalContainer.visibility == View.VISIBLE
                 ) {
                     View.VISIBLE
                 } else {
                     View.GONE
                 }
         }

         private fun applyGrocerySummaryVisibility(bind: GroceryinvoiceBinding) {
             bind.subTotalContainer.hideIfDisabled(businessdatadata.subTotalContainer)
             bind.totalContainer.hideIfDisabled(businessdatadata.totalContainer)
             bind.cardPayContainer.hideIfDisabled(businessdatadata.cardPayContainer)
             bind.cashPayContainer.hideIfDisabled(businessdatadata.cashPayContainer)
         }

         private fun View.hideIfDisabled(setting: Boolean?) {
             if (setting == false) {
                 visibility = View.GONE
             }else{
                 visibility = View.VISIBLE
             }
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
