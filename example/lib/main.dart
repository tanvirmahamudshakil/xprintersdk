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
  String _platformVersion = 'Unknown';
  final _xprintersdkPlugin = Xprintersdk();

  var ordermodeldata = {
    "id": null,
    "online_order_id": null,
    "service_charge": 0.0,
    "tips": 0.0,
    "order_id": null,
    "errorcode": 0,
    "barcode": "BUTCHER_1741199281257061",
    "local_id": 0,
    "tableUniqID": 1741199281257061,
    "selectDiscount": 0.0,
    "table_id": null,
    "order_type": "COLLECTION",
    "changeAmount": 0.0,
    "order_channel": "BUTCHER",
    "order_date": "2025-03-05 18:31:35",
    "requester_type": "PROVIDER",
    "requester_id": 0,
    "requester_uuid": 0,
    "shipping_address_id": 0,
    "requested_delivery_timestamp": "2025-03-05 18:31:35",
    "status": "PROCESSING",
    "net_amount": 20.52,
    "discounted_amount": 0.0,
    "delivery_charge": 0.0,
    "payable_amount": 20.52,
    "payment_type": "NOTPAY",
    "payment_id": 0,
    "prescriber_id": 0,
    "branch_id": 0,
    "comment": "",
    "created_at": "2025-03-05T18:31:35.196516",
    "updated_at": "2025-03-05T18:31:35.196586",
    "order_products": [
      {
        "id": 87483,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 10.26,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 87483,
          "sort_order": 0,
          "uuid": "ayre-3-kg-up-S24",
          "barcode": null,
          "type": "ITEM",
          "short_name": "AYRE 3 KG UP",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 1,
          "creator_uuid": "9c870ba8-9807-4ece-8f91-55f6754396e5",
          "property": {"expire_date": "25-03-26", "product_group_sort": "1", "product_group": "", "platform": null, "unit_amount": "2", "master": "0", "epos_category": "fish-whole", "is_coupon": null, "category": "fish-whole", "featured": null, "unit_of_sale": "KG", "discount_type": null, "discount_value": null, "print_order": "10", "print_order_enable": "0", "purchase_price": null, "tare_weight": "0", "total_unit": null, "total_unit_price": null, "cost_price": null, "sale_price_without_tax": null, "sale_price_with_tax": null, "margin": "9.747", "supplier": null, "orderCode": null, "sell_on_till": "0", "unit_product_type": "WEIGHT", "unit_of_purchase": "KG", "available_to_butcher": "1"},
          "files": [],
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "fish-whole",
        "selectMainCategoryKey": "category",
        "offer": null,
        "bands": [],
      },
    ],
    "requester": null,
    "requester_guest": null,
    "shipping_address": {"id": null, "name": null, "type": null, "creator_type": null, "creator_id": null, "status": null, "created_at": null, "updated_at": null, "prop": null},
    "waiterInfo": {"firstName": "Admin", "id": 0, "role": "ADMIN", "lastName": "", "phone": "admin", "password": "0000", "orderList": [], "loginTime": "2025-03-05 18:28:15", "logoutTime": "2025-03-05 18:28:15"},
    "order_files": [],
    "prescriber": null,
    "payment": null,
    "cash_entry": [],
    "branch": {"id": null, "name": "alislah", "value": null, "created_at": null, "updated_at": null, "property": null},
    "table": null,
    "property": {"comment": null, "requested_delivery_timestamp_type": "ASAP", "total_value_edit": null, "total_value_edit_note": null},
    "vat": 0.0,
    "totalvalueEdit": false,
    "totalValueEditcomment": "manager has edited price",
    "isDiscountGivenByAmount": false,
    "isDiscountGivenByUser": false,
    "waiterToEposSend": 0,
    "requestTime": 0,
    "entermanuallyRequestTime": false,
  };

  List<String> usbList = [];
  String selectUsb = "";

  labelPrinterInit() async {
    _xprintersdkPlugin.XprinterInitialization();
    // _xprintersdkPlugin.bitmapSave(printermodel, ordermodeldata);
  }

  getUsbList() async {
    // usbList = await _xprintersdkPlugin.getLabelPrinterUsbList();
    // setState(() {});
    // var connect = await _xprintersdkPlugin.XPrinterConnect(printermodel);
    // if (connect) {
    //   _xprintersdkPlugin.XPrinterPrintOnLineData(printermodel, ordermodeldata);
    // }
    //_xprintersdkPlugin.bitmapSave(printermodel, ordermodeldata);
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
        appBar: AppBar(title: const Text('Plugin example app')),
        body: Column(
          children: [
            MaterialButton(
              onPressed: () async {
                await _xprintersdkPlugin.bitmapSave(printermodel, ordermodeldata);
              },
              child: Text("Print image"),
            ),
            // Padding(padding: const EdgeInsets.all(8.0), child: Text("Select Usb: ${selectUsb}")),
            // SizedBox(height: 50),
            // MaterialButton(
            //   onPressed: () {
            //     labelPrinterInit();
            //   },
            //   child: Text("Label Printer Init"),
            // ),
            // MaterialButton(
            //   onPressed: () {
            //     getUsbList();
            //   },
            //   child: Text("Get All Usb List"),
            // ),

            // DropdownButtonFormField(
            //   items: List.generate(usbList.length, (index) {
            //     var d = usbList[index];
            //     return DropdownMenuItem(value: d, child: Text(d));
            //   }),
            //   onChanged: (value) {
            //     selectUsb = value!;
            //     setState(() {});
            //   },
            // ),
            // MaterialButton(
            //   onPressed: () {
            //     _xprintersdkPlugin.labelPrinterUSbConnect(path_name: selectUsb);
            //   },
            //   child: Text("Connect Label Printer"),
            // ),

            // MaterialButton(
            //   onPressed: () {
            //     // _xprintersdkPlugin.labelPrinterPrintBarCode();
            //   },
            //   child: Text("Label Printer Bar Code"),
            // ),

            // MaterialButton(
            //   onPressed: () {
            //     var d = _xprintersdkPlugin.labelPrinterConnect();
            //     print("flutter lavel Printer ${d}");
            //   },
            //   child: Text("Label Printer Connect Check"),
            // ),

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
    "branchNameShow": true,
    "barcode_text_show": true,
    "barcode_text_size": 14,
    "order_group": false,
    "butcher_order_barcode_hight": 200,
    "butcher_order_barcode_width": 700,
    "barcode_dpi": 420,
    "singleProductBarcodeHight": 2,
    "butcherStickerFont": 1,
    "expireDateShow": false,
    "maxItemPrint": 1,
    "butcherPrintStyle": "1",
    "singleProductBarCodeWidth": 7,
    "price_per_weight_font": 22,
    "singleProductExpireFont": 15,
    "singleProductPriceFont": 13,
    "singleProductNameFont": 15,
    "singleProductPrintHight": 239,
    "business_name_font": 22,
    "expire_date_font": 20,
    "item_name_font": 30,
    "item_price_font": 24,
    "item_weight_font": 22,
    "barcode_hight": 7,
    "barcode_x": 10,
    "barcode_y": 10,
    "barcode_width": 24,
    "label_hight": 65,
    "label_width": 65,
    "orderChannel": "BUTCHER",
    "weightShow": false,
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
    "ip": "",
    "dpi": 203,
    "bluetooth_name": "",
    "bluetooth_address": "",
    "businessname": "AL - ISLAH CASH & CARRY",
    "businessphone": "01998821888",
    "businessaddress": "Argyll Avenue",
    "highlight": 40,
    "papersize": 80,
    "dynamicCollection": "Collection",
    "dynamicDelivery": "Delivery",
    "dynamicEatIn": "Eat In",
    "dynamicTakeaway": "Takeaway",
    "vat_number": null,
    "vat_company_name": null,
    "vat_note": null,
    "printer_style": "1",
    "header1Size": 22,
    "header2Size": 22,
    "header3Size": 22,
    "header4Size": 22,
    "footervatFontSize": 15,
    "serviceCharge": false,
    "xprinter_path": null,
    "propertyshop": false,
    "show_category_name": false,
  });
}
