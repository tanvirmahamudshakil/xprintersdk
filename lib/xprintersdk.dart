import 'dart:convert';

import 'package:flutter/services.dart';

import 'Model/printerbusinessmodel.dart';

class Xprintersdk {
  final _methodChannel = const MethodChannel('xprintersdk');
  final String _xPrinterIntitalization = "xPrinterIntitalization";
  final String _xPrinterConnectionCheck = "xPrinterConnectionCheck";
  final String _xPrinterConnect = "xPrinterConnect";
  final String _xPrinterPrintOnlineData = "xPrinterPrintOnlineData";
  final String _xPrinterPrintLocalData = "xPrinterPrintLocalData";
  final String _sunmiPrinterService = "sunmiPrinterService";
  final String _sunmiPrinterInit = "sunmiPrinterInit";
  final String _sunmiPrint = "sunmiPrintBitmap";
  final String _bitmapImageSave = "bitmapImageSave";
  final String _sunmiPrinterCheck = "sunmiPrinterCheck";
  final String xPrinterbookingRequest = "XprinterbookingRequest";

  Future<String?> getPlatformVersion() async {
    final version =
        await _methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  Future XprinterInitialization() async {
    await _methodChannel.invokeMethod(_xPrinterIntitalization);
  }

  Future sunmiPrinterServiceInitialization() async {
    await _methodChannel.invokeMethod(_sunmiPrinterService);
  }

  Future sunmiPrinterInitialization() async {
    await _methodChannel.invokeMethod(_sunmiPrinterInit);
  }

  Future<bool> XPrinterConnectionCheck() async {
    return await _methodChannel.invokeMethod(_xPrinterConnectionCheck);
  }

  Future<bool> XPrinterConnect(PrinterBusinessModel printermodel) async {
    Map<String, dynamic> quary = {
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await _methodChannel.invokeMethod(_xPrinterConnect, quary);
  }

  Future<bool> XPrinterPrintOnLineData(
    PrinterBusinessModel printermodel,
    Map<String, Object?> orderiteam,
  ) async {
    Map<String, dynamic> quary = {
      "orderiteam": orderiteam,
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await _methodChannel.invokeMethod(_xPrinterPrintOnlineData, quary);
  }

  Future<bool> XPrinterPrintLocalData(
    PrinterBusinessModel printermodel,
    Map<String, Object?> orderiteam,
  ) async {
    Map<String, dynamic> quary = {
      "orderiteam": jsonDecode(jsonEncode(orderiteam)),
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await _methodChannel.invokeMethod(_xPrinterPrintLocalData, quary);
  }

  Future<bool> sunmiPrintBitmap(
    PrinterBusinessModel printermodel,
    Map<String, Object?> orderiteam,
  ) async {
    Map<String, dynamic> quary = {
      "orderiteam": orderiteam,
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await _methodChannel.invokeMethod(_sunmiPrint, quary);
  }

  Future<bool> bitmapSave(
    PrinterBusinessModel printermodel,
    Map<String, Object?> orderiteam,
  ) async {
    Map<String, dynamic> quary = {
      "orderiteam": orderiteam,
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await _methodChannel.invokeMethod(_bitmapImageSave, quary);
  }

  Future checkSunmiPrinter() async {
    return await _methodChannel.invokeMethod(_sunmiPrinterCheck);
  }

  Future<bool>bookingRequestPrint(
      PrinterBusinessModel printermodel,
      Map<String, Object?> bookingrequestIteam,
      ) async {
    Map<String, dynamic> quary = {
      "orderiteam": jsonDecode(jsonEncode(bookingrequestIteam)),
      "printer_model_data": jsonEncode(printermodel.toJson())
    };
    return await _methodChannel.invokeMethod(xPrinterbookingRequest, quary);
  }

}
