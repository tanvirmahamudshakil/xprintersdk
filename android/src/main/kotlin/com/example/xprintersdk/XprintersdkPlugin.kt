package com.example.xprintersdk

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.xprintersdk.Model.BookingRequest.BookingRequest
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.DailyReport.Dailyreport
import com.example.xprintersdk.Model.LocalOrderData.LocalOrderData
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.NyxPrinter.NyxPrinterActivity
import com.example.xprintersdk.PrinterService.DailyReportPage
import com.example.xprintersdk.PrinterService.RequestBookingprint
import com.example.xprintersdk.PrinterService.printerservice
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.xprinter.Xprinter
import com.google.gson.Gson
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


class XprintersdkPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var context : Context
  lateinit var xprinter: Xprinter
  lateinit var nxyPrinter : NyxPrinterActivity;
  lateinit var sunmiHelper : SunmiHelp;
  private var xPrinterIntitalization : String = "xPrinterIntitalization";
  private var xPrinterConnectionCheck ="xPrinterConnectionCheck";
  private var xPrinterConnect = "xPrinterConnect";
  private var xPrinterPrintOnlineData = "xPrinterPrintOnlineData"
  private var xPrinterPrintLocalData = "xPrinterPrintLocalData"
  private var sunmiPrinterService = "sunmiPrinterService";
  private var sunmiPrinterInit = "sunmiPrinterInit";
  private var sunmiPrintBitmap = "sunmiPrintBitmap";
  private var bitmapImageSave = "bitmapImageSave";
  private var sunmiPrinterCheck = "sunmiPrinterCheck";
  private var xprinterbookingRequest = "XprinterbookingRequest";
  private var dailyreportPrint = "dailyreportPrint";
  private var nyxPrinterInit = "nyxPrinterInit";
  private var nyxPrinterPrint = "nyxPrinterPrint";

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "xprintersdk")
    context = flutterPluginBinding.applicationContext
    xprinter = Xprinter(context)
    nxyPrinter = NyxPrinterActivity(context)
    sunmiHelper= SunmiHelp()
    channel.setMethodCallHandler(this)
  }


  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == xPrinterIntitalization) {
      xPrinterInitialization()
    } else if (call.method == nyxPrinterInit) {
      nyxPrinterInit();
    }else if (call.method == nyxPrinterPrint) {
      nyxPrintData(call, result)
    } else if(call.method == sunmiPrinterService){
      sunmiPrinterService()
    }else if(call.method == sunmiPrinterInit) {
      sunmiPrinterInit()
    }else if(call.method == sunmiPrintBitmap) {
      sunmiPrintData(call, result)
    }else if(call.method == sunmiPrinterCheck) {
      sunmiPrinterCheck(call, result)
    }
    else if (call.method == xPrinterConnectionCheck) {
      xPrinterConnectionCheck(result)
    }else if (call.method == xPrinterConnect){
      xPrinterConnect(call, result)
    }else if(call.method == xPrinterPrintOnlineData) {
      xprinterOnlineDataPrint(call, result);
    } else if(call.method ==  bitmapImageSave) {
      bitmapImageDataSave(call, result)
    } else if(call.method == xprinterbookingRequest) {
      XprinterBookingRequestPrint(call, result)
    } else if (call.method == dailyreportPrint) {
      dailyReportPrint(call, result)
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun xPrinterInitialization() {
    xprinter.initBinding()
  }
  private fun sunmiPrinterService() {
    sunmiHelper.initSunmiPrinterService(context)
  }

  private fun xPrinterConnectionCheck(result: Result) {
    xprinter.checkConnection(result)
  }

  private fun xPrinterConnect(call: MethodCall, result : Result) {
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    if (businessdata.selectPrinter!!.lowercase() == "xprinter" && businessdata.printerConnection!!.lowercase() == "ipconnection"){
      xprinter.connectNet(businessdata.ip.toString(),result);
    }else if(businessdata.selectPrinter!!.lowercase() == "xprinter" && businessdata.printerConnection!!.lowercase() == "usbconnection"){
      xprinter.connetUSB(result);
    }else{
     result.success(false)
    }
  }

  private fun xprinterOnlineDataPrint(call: MethodCall, result : Result){
    val orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    val printerbusinessdata = call.argument<String>("printer_model_data")
    val orderjson = Gson().toJson(orderiteamdata)
    val businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)

    val modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)

    if (businessdata.printerConnection!!.lowercase() == "ipconnection"){
      printerservice(context,modeldata,businessdata, xprinter, result,sunmiHelper, false, nxyPrinter).execute()
    }else if(businessdata.printerConnection!!.lowercase() == "usbconnection"){
      printerservice(context,modeldata, businessdata,xprinter, result, sunmiHelper, false, nxyPrinter).execute()
    }else{

    }
  }





  private fun sunmiPrinterInit() {
    sunmiHelper.initPrinter();
  }

  private fun sunmiPrintData(call: MethodCall, result : Result) {
    var orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var orderjson = Gson().toJson(orderiteamdata)
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    var modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)
    Log.d("order product length", "xprinterOnlineDataPrint: ${modeldata.orderProducts!!.size}")
    printerservice(context,modeldata,businessdata, xprinter, result, sunmiHelper, false, nxyPrinter).execute()

  }


  private fun bitmapImageDataSave(call: MethodCall, result : Result) {
    var orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var orderjson = Gson().toJson(orderiteamdata)
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    Log.d("json data", "xprinterprint: ${orderiteamdata}")
    var modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)
    Log.d("order product length", "xprinterOnlineDataPrint: ${modeldata.orderProducts!!.size}")
    printerservice(context,modeldata,businessdata, xprinter, result, sunmiHelper, true, nxyPrinter).execute()
  }


  private fun sunmiPrinterCheck(call: MethodCall, result : Result) {
      result.success(sunmiHelper.sunmiPrinter)
  }

  private fun XprinterBookingRequestPrint(call: MethodCall, result : Result) {
    var orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var orderjson = Gson().toJson(orderiteamdata)
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    Log.d("Online Booking Request", "Online Booking Request: ${orderiteamdata}")
    var modeldata = Gson().fromJson<BookingRequest>(orderjson, BookingRequest::class.java)
    Log.d("Online Booking Request", "bookingRequestData: ${modeldata.name}")
    RequestBookingprint(context,modeldata, businessdata,xprinter, result, sunmiHelper, false, ).execute()
  }

  private fun dailyReportPrint(call: MethodCall, result : Result) {
    val orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    val printerbusinessdata = call.argument<String>("printer_model_data")
    val orderjson = Gson().toJson(orderiteamdata)
    val businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    Log.d("Online Booking Request", "Online Booking Request: ${orderiteamdata}")
    val modeldata = Gson().fromJson<Dailyreport>(orderjson, Dailyreport::class.java)
    Log.d("DailyReport", "DailyReport: ${modeldata.data!!.date}")
    DailyReportPage(context,modeldata, businessdata,xprinter, result, sunmiHelper, false, ).execute()
//    if (businessdata.printerConnection!!.lowercase() == "ipconnection"){
//      DailyReportPage(context,modeldata,businessdata, xprinter, result,sunmiHelper, false).execute()
//    }else if(businessdata.printerConnection!!.lowercase() == "usbconnection"){
//      DailyReportPage(context,modeldata, businessdata,xprinter, result, sunmiHelper, false, ).execute()
//    }else{
//      DailyReportPage(context,modeldata, businessdata,xprinter, result, sunmiHelper, false, ).execute()
//    }
  }



  // nyx printer

  private fun nyxPrinterInit() {

    nxyPrinter.bindServiceData()
  }


  private fun  nyxPrintData(call: MethodCall, result : Result) {
    val orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    val printerbusinessdata = call.argument<String>("printer_model_data")
    val orderjson = Gson().toJson(orderiteamdata)
    val businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    val modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)
    printerservice(context,modeldata,businessdata, xprinter, result, sunmiHelper, false, nxyPrinter).execute()
  }

}
