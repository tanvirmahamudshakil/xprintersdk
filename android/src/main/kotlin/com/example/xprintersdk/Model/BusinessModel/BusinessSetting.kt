package com.example.xprintersdk.Model.BusinessModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class BusinessSetting(
    @SerializedName("auto_print")
    val autoPrint: Boolean?,
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
    var footervatFontSize : Int?






)