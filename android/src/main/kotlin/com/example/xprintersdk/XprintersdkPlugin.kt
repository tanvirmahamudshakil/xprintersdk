package com.example.xprintersdk

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.xprintersdk.Model.BusinessModel.BusinessSetting
import com.example.xprintersdk.Model.OrderData.OrderData
import com.example.xprintersdk.PrinterService.printerservice
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
  private var xPrinterIntitalization : String = "xPrinterIntitalization";
  private var xPrinterConnectionCheck ="xPrinterConnectionCheck";
  private var xPrinterConnect = "xPrinterConnect";
  private var xPrinterPrintOnlineData = "xPrinterPrintOnlineData"

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "xprintersdk")
    context = flutterPluginBinding.applicationContext
    xprinter = Xprinter(context)

    channel.setMethodCallHandler(this)
  }

  @RequiresApi(Build.VERSION_CODES.O)
  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == xPrinterIntitalization) {
      xPrinterInitialization()
    } else if (call.method == xPrinterConnectionCheck) {
      xPrinterConnectionCheck(result)
    }else if (call.method == xPrinterConnect){
      xPrinterConnect(call, result)
    }else if(call.method == xPrinterPrintOnlineData) {
      xprinterOnlineDataPrint(call, result);
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

  private fun xPrinterConnectionCheck(result: Result) {
    xprinter.checkConnection(result)
  }

  private fun xPrinterConnect(call: MethodCall, result : Result) {
    var printerbusinessdata = call.argument<String>("printer_model_data")
    var businessdata = Gson().fromJson<BusinessSetting>(printerbusinessdata, BusinessSetting::class.java)
    if (businessdata.printerConnection == "IP Connection"){
      xprinter.connectNet(businessdata.ip.toString(),result);
    }else if(businessdata.printerConnection == "USB Connection"){
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
    if (businessdata.printerConnection == "IP Connection"){
      printerservice(context,modeldata,businessdata).printxprinteripdata(xprinter, result)
    }else if(businessdata.printerConnection == "USB Connection"){
      printerservice(context,modeldata, businessdata).printxprinteripdata(xprinter, result)
    }else{

    }
  }

}
