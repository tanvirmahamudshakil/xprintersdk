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
            MaterialButton(onPressed: (){
              _xprintersdkPlugin.XprinterInitialization();
            },child: Text("Xprinter Initialization")),

            MaterialButton(onPressed: ()  async {
             var data = await _xprintersdkPlugin.XPrinterConnectionCheck();
             print(data);
            },child: Text("Xprinter connection check")),
            
            MaterialButton(onPressed: () async {
              var data = await _xprintersdkPlugin.XPrinterConnect(printermodel);
              print(data);
            },child: Text("Xprinter connect"),),

            MaterialButton(onPressed: () async {
              var data = await _xprintersdkPlugin.XPrinterPrintOnLineData(printermodel, orderjson2);
              print(data);
            },child: Text("Xprinter print"),)
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
      printerConnection: "USB Connection",
      selectPrinter: "X Printer",
      showOrderNoInvoice: true,
      bluetoothAddress: "DC:0D:30:EE:30:2A",
      bluetoothName: "dsvsdvsd",
      businessname: "sdvsdvsdv",
      businessphone: "01932336565",
      ip: "192.168.0.104",
      businessaddress: "sdkjbvjsdhbvjhsbdv");

}
