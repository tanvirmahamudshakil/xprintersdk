package com.example.xprintersdk

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.LocalOrderData.LocalOrderData
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.PrinterService.local_printer
import com.example.xprintersdk.PrinterService.printerservice
import com.example.xprintersdk.Sunmi.SunmiHelp
import com.example.xprintersdk.xprinter.Xprinter
import com.google.gson.Gson
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class XprintersdkPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var context : Context
  lateinit var xprinter: Xprinter
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

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "xprintersdk")
    context = flutterPluginBinding.applicationContext
    xprinter = Xprinter(context)
    sunmiHelper= SunmiHelp()
    channel.setMethodCallHandler(this)
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == xPrinterIntitalization) {
      xPrinterInitialization()
    }else if(call.method == sunmiPrinterService){
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
    }else if(call.method == xPrinterPrintLocalData){
      xPrinterLocalData(call, result)
    } else if(call.method ==  bitmapImageSave) {
      bitmapImageDataSave(call, result)
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
      result.notImplemented()
    }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  private fun xprinterOnlineDataPrint(call: MethodCall, result : Result){
    var orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var orderjson = Gson().toJson(orderiteamdata)
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    Log.d("json data", "xprinterprint: ${orderiteamdata}")
    var modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)
    Log.d("order product length", "xprinterOnlineDataPrint: ${modeldata.orderProducts!!.size}")
    if (businessdata.printerConnection!!.lowercase() == "ipconnection"){
      printerservice(context,modeldata,businessdata, xprinter, result,sunmiHelper, false).execute()
    }else if(businessdata.printerConnection!!.lowercase() == "usbconnection"){
      printerservice(context,modeldata, businessdata,xprinter, result, sunmiHelper, false).execute()
    }else{

    }
  }



  private fun xPrinterLocalData(call: MethodCall, result : Result){
    var orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var orderjson = Gson().toJson(orderiteamdata)
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    Log.d("json data", "xprinterprint: ${orderiteamdata}")
    var modeldata = Gson().fromJson<LocalOrderData>(orderjson, LocalOrderData::class.java)
    Log.d("order product length", "xprinterOnlineDataPrint: ${modeldata.items!!.size}")
    if (businessdata.printerConnection == "IP Connection"){
      local_printer(context,modeldata,businessdata, xprinter, result).execute("1")

    }else if(businessdata.printerConnection == "USB Connection"){
      local_printer(context,modeldata, businessdata,xprinter, result).execute("2")
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
    Log.d("json data", "xprinterprint: ${orderiteamdata}")
    var modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)
    Log.d("order product length", "xprinterOnlineDataPrint: ${modeldata.orderProducts!!.size}")
    printerservice(context,modeldata,businessdata, xprinter, result, sunmiHelper, false).execute()
  }


  private fun bitmapImageDataSave(call: MethodCall, result : Result) {
    var orderiteamdata = call.argument<Map<String, Any>>("orderiteam")
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var orderjson = Gson().toJson(orderiteamdata)
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    Log.d("json data", "xprinterprint: ${orderiteamdata}")
    var modeldata = Gson().fromJson<OrderData>(orderjson, OrderData::class.java)
    Log.d("order product length", "xprinterOnlineDataPrint: ${modeldata.orderProducts!!.size}")
    printerservice(context,modeldata,businessdata, xprinter, result, sunmiHelper, true).execute()
  }


  private fun sunmiPrinterCheck(call: MethodCall, result : Result) {
      result.success(sunmiHelper.sunmiPrinter)
  }

}
