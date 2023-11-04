import 'dart:convert';

import 'package:flutter/services.dart';

import 'Model/printerbusinessmodel.dart';

class Xprintersdk {
  final methodChannel = const MethodChannel('xprintersdk');
  String xPrinterIntitalization = "xPrinterIntitalization";
  String xPrinterConnectionCheck = "xPrinterConnectionCheck";
  String xPrinterConnect = "xPrinterConnect";
  String xPrinterPrintOnlineData = "xPrinterPrintOnlineData";
  String xPrinterPrintLocalData = "xPrinterPrintLocalData";

  Future<String?> getPlatformVersion() async {
    final version =
        await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  Future XprinterInitialization() async {
    await methodChannel.invokeMethod(xPrinterIntitalization);
  }

  Future<bool> XPrinterConnectionCheck() async {
    return await methodChannel.invokeMethod(xPrinterConnectionCheck);
  }

  Future<bool> XPrinterConnect(PrinterBusinessModel printermodel) async {
    Map<String, dynamic> quary = {
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await methodChannel.invokeMethod(xPrinterConnect, quary);
  }

  Future<bool> XPrinterPrintOnLineData(
    PrinterBusinessModel printermodel,
    Map<String, Object?> orderiteam,
  ) async {
    Map<String, dynamic> quary = {
      "orderiteam": orderiteam,
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await methodChannel.invokeMethod(xPrinterPrintOnlineData, quary);
  }

  Future<bool> XPrinterPrintLocalData(
    PrinterBusinessModel printermodel,
    Map<String, Object?> orderiteam,
  ) async {
    Map<String, dynamic> quary = {
      "orderiteam": jsonDecode(jsonEncode(orderiteam)),
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await methodChannel.invokeMethod(xPrinterPrintLocalData, quary);
  }
}
