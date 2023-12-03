package com.example.xprintersdk.Model.BusinessModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class BusinessSetting(
    @SerializedName("auto_print")
    val autoPrint: Boolean?,
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
    val highlight : Int?
)