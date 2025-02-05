import 'package:flutter/material.dart';

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
  final _xprintersdkPlugin = Xprintersdk();

  List<String> usbList = [];
  String selectUsb = "";

  labelPrinterInit() {
    _xprintersdkPlugin.bitmapSave(printermodel, butcherOrder);
  }

  getUsbList() async {
    usbList = await _xprintersdkPlugin.getLabelPrinterUsbList();
    setState(() {});
  }

  @override
  void initState() {
    getUsbList();
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
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text("Select Usb: ${selectUsb}"),
            ),
            SizedBox(height: 50),
            MaterialButton(
              onPressed: () {
                labelPrinterInit();
              },
              child: Text("Label Printer Init"),
            ),
            MaterialButton(
              onPressed: () {
                getUsbList();
              },
              child: Text("Get All Usb List"),
            ),

            DropdownButtonFormField(
              items: List.generate(usbList.length, (index) {
                var d = usbList[index];
                return DropdownMenuItem(value: d, child: Text(d));
              }),
              onChanged: (value) {
                selectUsb = value!;
                setState(() {});
              },
            ),
            MaterialButton(
              onPressed: () {
                _xprintersdkPlugin.labelPrinterUSbConnect(path_name: selectUsb);
              },
              child: Text("Connect Label Printer"),
            ),

            MaterialButton(
              onPressed: () {
                // _xprintersdkPlugin.labelPrinterPrintBarCode();
              },
              child: Text("Label Printer Bar Code"),
            ),

            MaterialButton(
              onPressed: () {
                var d = _xprintersdkPlugin.labelPrinterConnect();
                print("flutter lavel Printer ${d}");
              },
              child: Text("Label Printer Connect Check"),
            ),
            // MaterialButton(
            //   onPressed: () async {
            //     _xprintersdkPlugin.nyxPrinterInit();
            //   },
            //   child: const Text("NyxPrinter init"),
            // ),

            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.checkNyxPrinter();
            //     print(data.toString());
            //   },
            //   child: const Text("NyxPrinter Check"),
            // ),

            // MaterialButton(
            //   onPressed: () async {
            //     _xprintersdkPlugin.nyxPrinterPrintBitmap(printermodel, order2);
            //   },
            //   child: const Text("NyxPrinter print order"),
            // ),

            // MaterialButton(
            //     onPressed: () {
            //       _xprintersdkPlugin.XprinterInitialization();
            //     },
            //     child: const Text("Xprinter Initialization")),
            // MaterialButton(
            //     onPressed: () async {
            //       var data = await _xprintersdkPlugin.XPrinterConnectionCheck();
            //       print(data);
            //     },
            //     child: const Text("Xprinter connection check")),
            // MaterialButton(
            //   onPressed: () async {
            //     var data =
            //         await _xprintersdkPlugin.XPrinterConnect(printermodel);
            //     print(data);
            //   },
            //   child: const Text("Xprinter connect"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.XPrinterPrintOnLineData(
            //         printermodel, orderjson5);
            //     print(data);
            //   },
            //   child: const Text("Xprinter print"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.XPrinterPrintLocalData(
            //         printermodel, localorder);
            //   },
            //   child: const Text("local print"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.sunmiPrinterServiceInitialization();
            //   },
            //   child: const Text("Sunmi Printer Service Init"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.sunmiPrinterInitialization();
            //   },
            //   child: const Text("Sunmi Printer Init"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.sunmiPrintBitmap(
            //         printermodel, orderjson4);
            //     print("${data}");
            //     Future.delayed(const Duration(seconds: 2), () {
            //       showDialog(
            //           context: context,
            //           builder: (_) {
            //             return AlertDialog(
            //               title: Text("sunmi print $data"),
            //             );
            //           });
            //     });
            //   },
            //   child: const Text("Sunmi Print Bitmap"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.bitmapSave(printermodel, order2);
            //   },
            //   child: const Text("Bitmap Save"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.checkSunmiPrinter();
            //     print(data.toString());
            //   },
            //   child: const Text("Sunmi Printer Check"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.bookingRequestPrint(
            //         printermodel, bookingRequest);
            //     print(data.toString());
            //   },
            //   child: const Text("Bookign Request print"),
            // ),
            // MaterialButton(
            //   onPressed: () async {
            //     var data = await _xprintersdkPlugin.dailyReportPrint(
            //         printermodel, dailyReport);
            //     print(data.toString());
            //   },
            //   child: const Text("Daily Report print"),
            // )
          ],
        ),
      ),
    );
  }

  PrinterBusinessModel printermodel = PrinterBusinessModel.fromJson({
    "branchNameShow": false,
    "singleProductBarcodeHight": 73,
    "butcherStickerFont": 23,
    "expireDateShow": false,
    "maxItemPrint": 0,
    "butcherPrintStyle": "1",
    "singleProductBarCodeWidth": 400,
    "price_per_weight_font": 20,
    "singleProductExpireFont": 15,
    "singleProductPriceFont": 6,
    "singleProductNameFont": 20,
    "singleProductPrintHight": 500,
    "business_name_font": 6,
    "expire_date_font": 20,
    "item_name_font": 20,
    "item_price_font": 20,
    "item_weight_font": 20,
    "barcode_hight": 100,
    "barcode_x": 10,
    "barcode_y": 10,
    "barcode_width": 500,
    "label_hight": 63,
    "label_width": 57,
    "orderChannel": "BUTCHER",
    "weightShow": true,
    "asapFontSize": 22,
    "highlighttextsize": 20,
    "weightMultiplyingPrice": true,
    "font_size": 22,
    "print_on_delivery": 1,
    "print_on_collection": 1,
    "print_on_table_order": 1,
    "print_on_tackway_order": 1,
    "auto_print": true,
    "show_order_no_invoice": true,
    "select_printer": "label_printer",
    "printer_connection": "usbconnection",
    "ip": "192.65.525",
    "dpi": 203,
    "bluetooth_name": "",
    "bluetooth_address": "",
    "businessname": "TTTTT",
    "businessphone": "01932331718",
    "businessaddress": "",
    "highlight": 40,
    "papersize": 80,
    "dynamicCollection": "Collection 1",
    "dynamicDelivery": "Delivery",
    "dynamicEatIn": "Eat In",
    "dynamicTakeaway": "Takeaway",
    "vat_number": null,
    "vat_company_name": null,
    "vat_note": null,
    "printer_style": "1",
    "header1Size": 22,
    "header2Size": 22,
    "header3Size": 20,
    "header4Size": 22,
    "footervatFontSize": 15,
    "serviceCharge": true,
    "xprinter_path": null,
    "propertyshop": false
  });
}
