import 'dart:convert';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:xprintersdk/Model/printerbusinessmodel.dart';
import 'package:xprintersdk/xprintersdk.dart';

import 'orderjson.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _xprintersdkPlugin = Xprintersdk();

  @override
  void initState() {
    super.initState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Column(
          children: [
            MaterialButton(
                onPressed: () {
                  _xprintersdkPlugin.XprinterInitialization();
                },
                child: Text("Xprinter Initialization")),
            MaterialButton(
                onPressed: () async {
                  var data = await _xprintersdkPlugin.XPrinterConnectionCheck();
                  print(data);
                },
                child: Text("Xprinter connection check")),
            MaterialButton(
              onPressed: () async {
                var data =
                    await _xprintersdkPlugin.XPrinterConnect(printermodel);
                print(data);
              },
              child: Text("Xprinter connect"),
            ),
            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.XPrinterPrintOnLineData(
                    printermodel, orderjson3);
                print(data);
              },
              child: Text("Xprinter print"),
            ),
            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.XPrinterPrintLocalData(
                    printermodel, localorder);
              },
              child: Text("local print"),
            ),

            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.sunmiPrinterServiceInitialization();
              },
              child: Text("Sunmi Printer Service Init"),
            ),
            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.sunmiPrinterInitialization();
              },
              child: Text("Sunmi Printer Init"),
            ),

            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.sunmiPrintBitmap(printermodel, orderjson3);
              },
              child: Text("Sunmi Print Bitmap"),
            ),

            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.bitmapSave(printermodel, orderjson3);
              },
              child: Text("Bitmap Save"),
            ),

            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.checkSunmiPrinter();
                print(data.toString());
              },
              child: Text("Sunmi Printer Check"),
            ),
            MaterialButton(
              onPressed: () async {
                var data = await _xprintersdkPlugin.bookingRequestPrint(printermodel, bookingRequest);
                print(data.toString());
              },
              child: Text("Bookign Request print"),
            )

          ],
        ),
      ),
    );
  }

  PrinterBusinessModel printermodel = PrinterBusinessModel(
      autoPrint: true,
      fontSize: 16,
      printOnCollection: 1,
      printOnDelivery: 1,
      printOnTableOrder: 1,
      printOnTackwayOrder: 1,
      printerConnection: "USBConnection",
      selectPrinter: "sunmi",
      showOrderNoInvoice: true,
      bluetoothAddress: "DC:0D:30:EE:30:2A",
      bluetoothName: "dsvsdvsd",
      businessname: "sdvsdvsdv",
      businessphone: "01932336565",
      ip: "192.168.0.104",
      businessaddress: "sdkjbvjsdhbvjhsbdv");
}
