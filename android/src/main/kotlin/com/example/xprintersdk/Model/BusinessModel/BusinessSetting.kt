package com.example.xprintersdk.Model.BusinessModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class BusinessSetting(
    @SerializedName("weightShow")
    var weightShow : Boolean,
    @SerializedName("orderChannel")
    var orderChannel: String?,
    @SerializedName("auto_print")
    val autoPrint: Boolean?,
    @SerializedName("weightMultiplyingPrice")
    val weightMultiplyingPrice : Boolean,
    @SerializedName("serviceCharge")
    val serviceCharge : Boolean,
    @SerializedName("bluetooth_address")
    val bluetoothAddress: String?,
    @SerializedName("bluetooth_name")
    val bluetoothName: String?,
    @SerializedName("businessaddress")
    val businessaddress: String?,
    @SerializedName("businessname")
    val businessname: String?,
    @SerializedName("businessphone")
    val businessphone: String?,
    @SerializedName("font_size")
    val fontSize: Int?,
    @SerializedName("ip")
    val ip: String?,
    @SerializedName("print_on_collection")
    val printOnCollection: Int?,
    @SerializedName("print_on_delivery")
    val printOnDelivery: Int?,
    @SerializedName("print_on_table_order")
    val printOnTableOrder: Int?,
    @SerializedName("print_on_tackway_order")
    val printOnTackwayOrder: Int?,
    @SerializedName("printer_connection")
    val printerConnection: String?,
    @SerializedName("select_printer")
    val selectPrinter: String?,
    @SerializedName("show_order_no_invoice")
    val showOrderNoInvoice: Boolean?,
    @SerializedName("highlight")
    val highlight : Int?,
    @SerializedName("papersize")
    val paperSize : Int?,
    @SerializedName("dynamicCollection")
    val dynamicCollection: String?,
    @SerializedName("dynamicDelivery")
    val dynamicDelivery: String?,
    @SerializedName("dynamicEatIn")
    val dynamicEatIn: String?,
    @SerializedName("dynamicTakeaway")
    val dynamicTakeaway: String?,
    @SerializedName("highlighttextsize")
    val highlighttextsize: Int?,
    @SerializedName("vat_number")
    val vatNumber: String?,
    @SerializedName("vat_company_name")
    val vatCompanyName: String?,
    @SerializedName("vat_note")
    val vatNote: String?,
    @SerializedName("printer_style")
    val printerStyle: String?,
    @SerializedName("asapFontSize")
    val asapFontSize: Int?,
    @SerializedName("header1Size")
    var header1Size: Int?,
    @SerializedName("header2Size")
    var header2Size: Int?,
    @SerializedName("header3Size")
    var header3Size: Int?,
    @SerializedName("header4Size")
    var header4Size: Int?,
    @SerializedName("footervatFontSize")
    var footervatFontSize : Int?,
    @SerializedName("xprinter_path")
    var xprinterpath : String?,
    @SerializedName("propertyshop")
    var propertyShop : Boolean?,
    @SerializedName("label_hight")
    var label_hight: Int?,
    @SerializedName("label_width")
    var label_width: Int?,

    @SerializedName("dpi")
    val dpi: Int?,
    @SerializedName("barcode_hight")
    val barcode_hight: Int?,
    @SerializedName("barcode_width")
    val barcode_width: Int?,
    @SerializedName("barcode_x")
    var barcode_x : Int?,
    @SerializedName("barcode_y")
    var barcode_y : Int?,
    @SerializedName("business_name_font")
    val business_name_font: Int?,
    @SerializedName("expire_date_font")
    val expire_date_font: Int?,
    @SerializedName("item_name_font")
    val item_name_font: Int?,
    @SerializedName("item_price_font")
    val item_price_font: Int?,
    @SerializedName("item_weight_font")
    val item_weight_font: Int?,
    @SerializedName("price_per_weight_font")
    val price_per_weight_font: Int?,
    @SerializedName("singleProductNameFont")
    val singleProductNameFont: Int?,
    @SerializedName("singleProductPriceFont")
    val singleProductPriceFont: Int?,
    @SerializedName("singleProductExpireFont")
    val singleProductExpireFont: Int?,
    @SerializedName("singleProductPrintHight")
    val singleProductPrintHight: Int?,
    @SerializedName("singleProductBarcodeHight")
    val singleProductBarcodeHight: Int?,
    @SerializedName("singleProductBarCodeWidth")
    val singleProductBarCodeWidth: Int?,
    @SerializedName("maxItemPrint")
    val maxItemPrint: Int?,
    @SerializedName("butcherPrintStyle")
    val butcherPrintStyle: String,
    @SerializedName("butcherStickerFont")
    val butcherStickerFont: Int,
    @SerializedName("expireDateShow")
    val expireDateShow: Boolean,
    @SerializedName("branchNameShow")
    val branchNameShow : Boolean,
    @SerializedName("barcode_dpi")
    val barcode_dpi: Int,
    @SerializedName("butcher_order_barcode_hight")
    val butcher_order_barcode_hight: Int,
    @SerializedName("butcher_order_barcode_width")
    val butcher_order_barcode_width: Int,
    @SerializedName("order_group")
    var order_group: Boolean,
    @SerializedName("show_category_name")
    var show_category_name: Boolean,
    @SerializedName("barcode_text_show")
    var barcode_text_show: Boolean,
    @SerializedName("barcode_text_size")
    var barcode_text_size: Int,
    @SerializedName("yuma_productid_length")
    var yuma_productid_length : Int,
    @SerializedName("yuma_netamount_length")
    var yuma_netamount_length : Int,
    @SerializedName("yuma_weight_length")
    var yuma_weight_length : Int,
    @SerializedName("yuma_totalprice_length")
    var yuma_totalprice_length : Int,
    @SerializedName("invoice_type")
    var invoice_type: String,
    @SerializedName("business_email")
    var business_email: String,
    @SerializedName("grocery_barcode_hight")
    var grocery_barcode_hight: Int,
    @SerializedName("grocery_barcode_width")
    var grocery_barcode_width: Int,
    @SerializedName("grocery_barcode_text_show")
    var grocery_barcode_text_show: Boolean,
    @SerializedName("grocery_barcode_text_size")
    var grocery_barcode_text_size: Int,
    @SerializedName("expire_name")
    var expire_name : String,
    @SerializedName("starter_group")
    var starter_group : Boolean,

    @SerializedName("groupHeaderShow")
    var groupHeaderShow : Boolean,
    @SerializedName("banquetoffer")
    val items: List<BanquetOfferElement?>?,






){
    @Keep
    data class BanquetOfferElement (
        @SerializedName("id")
        val id: Long,
        @SerializedName("offer_product_id")
        val offerProductID: Long,
        @SerializedName("category_id")
        val categoryID: String,
        @SerializedName("free_quantity")
        val freeQuantity: Long,
        @SerializedName("created_at")
        val createdAt: Any? = null,
        @SerializedName("updated_at")
        val updatedAt: Any? = null
    )
}