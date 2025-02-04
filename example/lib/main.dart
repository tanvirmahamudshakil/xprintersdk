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
    "barcode": "EPOS_1738670106176025",
    "local_id": 1,
    "tableUniqID": 1738670106176025,
    "selectDiscount": 0.0,
    "table_id": null,
    "order_type": "COLLECTION",
    "changeAmount": 0.0,
    "order_channel": "EPOS",
    "order_date": "2025-02-04 18:48:44",
    "requester_type": "PROVIDER",
    "requester_id": 0,
    "requester_uuid": 0,
    "shipping_address_id": 0,
    "requested_delivery_timestamp": "2025-02-04 18:48:44",
    "status": "PROCESSING",
    "net_amount": 2.0,
    "discounted_amount": 0.0,
    "delivery_charge": 0.0,
    "payable_amount": 2.0,
    "payment_type": "NOTPAY",
    "payment_id": 0,
    "prescriber_id": 0,
    "branch_id": 0,
    "comment": "",
    "created_at": "2025-02-04T18:48:44.368",
    "updated_at": "2025-02-04T18:48:44.385",
    "order_products": [
      {
        "id": 87512,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 3,
        "net_amount": 1.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 87512,
          "sort_order": 0,
          "uuid": "pran-frooto-mango-1l-IN7",
          "barcode": null,
          "type": "ITEM",
          "short_name": "PRAN FROOTO MANGO 1L",
          "description": "PRAN FROOTO MANGO 1L",
          "status": 1,
          "discountable": 1.0,
          "creator_id": 1,
          "creator_uuid": "9c870ba8-9807-4ece-8f91-55f6754396e5",
          "property": {
            "expire_date": null,
            "product_group": "",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "drink",
            "is_coupon": null,
            "category": "drink",
            "featured": null,
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": null,
            "tare_weight": "0",
            "total_unit": null,
            "total_unit_price": null,
            "cost_price": null,
            "sale_price_without_tax": null,
            "sale_price_with_tax": null,
            "margin": null,
            "supplier": null,
            "orderCode": null,
            "sell_on_till": null,
            "unit_product_type": "",
            "unit_of_purchase": null,
            "available_to_butcher": "1"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "",
        "selectMainCategoryKey": "",
        "offer": {
          "id": 1,
          "offer_id": 1,
          "product_id": 87512,
          "offer": {"id": 1, "title": "3 for 2", "type": "X_FOR_Y", "buy": 3, "for": 2.0, "start_from": null, "end_at": null, "status": 1}
        },
        "bands": []
      }
    ],
    "requester": null,
    "requester_guest": null,
    "shipping_address": {
      "id": null,
      "name": null,
      "type": null,
      "creator_type": null,
      "creator_id": null,
      "status": null,
      "created_at": null,
      "updated_at": null,
      "prop": null
    },
    "waiterInfo": {
      "firstName": "Admin",
      "id": 0,
      "role": "ADMIN",
      "lastName": "",
      "phone": "admin",
      "password": "0000",
      "orderList": [],
      "loginTime": "2025-02-04 17:55:35",
      "logoutTime": "2025-02-04 17:55:35"
    },
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
    "waiterToEposSend": 1,
    "requestTime": 0,
    "entermanuallyRequestTime": false
  };

  List<String> usbList = [];
  String selectUsb = "";

  labelPrinterInit() {
    _xprintersdkPlugin.bitmapSave(printermodel, ordermodeldata);
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

  PrinterBusinessModel printermodel = PrinterBusinessModel(
    branchNameShow: true,
    businessNameFont: 12,
    butcherPrintStyle: "1",
    butcherStickerFont: 12,
    expireDateFont: 12,
    expireDateShow: true,
    itemNameFont: 12,
    itemPricefont: 20,
    itemWeightfont: 12,
    labelPrinterHight: 12,
    orderChannel: "EPOS",
    weightMultiplyingPrice: false,
    highlighttextsize: 20,
    dynamicCollection: "Collection",
    dynamicDelivery: "Delivery",
    dynamicEatIn: "Eat In",
    dynamicTakeaway: "Customer Waiting",
    autoPrint: true,
    highlight: 30,
    fontSize: 22,
    printOnCollection: 1,
    papersSize: 80,
    printOnDelivery: 1,
    printOnTableOrder: 1,
    printOnTackwayOrder: 1,
    printerConnection: "USBConnection",
    selectPrinter: "xprinter",
    showOrderNoInvoice: true,
    bluetoothAddress: "DC:0D:30:EE:30:2A",
    bluetoothName: "dsvsdvsd",
    businessname: "sdvsdvsdv",
    businessphone: "01932336565",
    ip: "192.168.0.104",
    businessaddress: "sdkjbvjsdhbvjhsbdv",
    maxItemPrint: 0,
    pricePerWeightFont: 12,
    singleProductBarCodeWidth: 10,
    singleProductBarcodeHight: 10,
    singleProductExpireFont: 10,
    singleProductNameFont: 10,
    singleProductPriceFont: 10,
    singleProductPrintHight: 10,
    asapFontSize: 10,
    barcode_hight: 10,
    barcode_width: 10,
    barcode_x: 10,
    barcode_y: 10,
    dpi: 10,
    footervatFontSize: 10,
    header1Size: 10,
  );
}
