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
    "barcode": "EPOS_1739179859741582",
    "local_id": 1,
    "tableUniqID": 1739179859741582,
    "selectDiscount": 0.0,
    "table_id": null,
    "order_type": "COLLECTION",
    "changeAmount": 0.0,
    "order_channel": "EPOS",
    "order_date": "2025-02-10 15:31:27",
    "requester_type": "PROVIDER",
    "requester_id": 0,
    "requester_uuid": 0,
    "shipping_address_id": 0,
    "requested_delivery_timestamp": "2025-02-10 15:31:27",
    "status": "PROCESSING",
    "net_amount": 83.0,
    "discounted_amount": 8.3,
    "delivery_charge": 0.0,
    "payable_amount": 74.7,
    "payment_type": "NOTPAY",
    "payment_id": 0,
    "prescriber_id": 0,
    "branch_id": 0,
    "comment": "",
    "created_at": "2025-02-10T15:31:27.358",
    "updated_at": "2025-02-10T15:31:27.359",
    "order_products": [
      {
        "id": 2169,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 23.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 2169,
          "sort_order": 6,
          "uuid": "item-6",
          "barcode": null,
          "type": "ITEM",
          "short_name": "item 6",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 2,
          "creator_uuid": "9db2c1e2-8421-4150-bf1c-dfd673acf836",
          "property": {
            "expire_date": null,
            "product_group": "Indian",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "sub-1",
            "is_coupon": null,
            "category": "sub-1",
            "featured": "0",
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": "0",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "sub-1",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": []
      },
      {
        "id": 2167,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 14.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 2167,
          "sort_order": 5,
          "uuid": "item-5",
          "barcode": null,
          "type": "ITEM",
          "short_name": "item 5",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 2,
          "creator_uuid": "9db2c1e2-8421-4150-bf1c-dfd673acf836",
          "property": {
            "expire_date": null,
            "product_group": "Indian",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "sub-1",
            "is_coupon": null,
            "category": "sub-1",
            "featured": "0",
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": "0",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "sub-1",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": []
      },
      {
        "id": 2165,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 13.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 2165,
          "sort_order": 4,
          "uuid": "item-3-pqU",
          "barcode": null,
          "type": "ITEM",
          "short_name": "item 3",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 2,
          "creator_uuid": "9db2c1e2-8421-4150-bf1c-dfd673acf836",
          "property": {
            "expire_date": null,
            "product_group": "Grill",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "sub-1",
            "is_coupon": null,
            "category": "sub-1",
            "featured": "0",
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": "0",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "sub-1",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": []
      },
      {
        "id": 2163,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 12.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 2163,
          "sort_order": 3,
          "uuid": "item-3",
          "barcode": null,
          "type": "ITEM",
          "short_name": "item 3",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 2,
          "creator_uuid": "9db2c1e2-8421-4150-bf1c-dfd673acf836",
          "property": {
            "expire_date": null,
            "product_group": "Grill",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "sub-1",
            "is_coupon": null,
            "category": "sub-1",
            "featured": "0",
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": "0",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "sub-1",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": []
      },
      {
        "id": 2161,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 11.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 2161,
          "sort_order": 2,
          "uuid": "item-2",
          "barcode": null,
          "type": "ITEM",
          "short_name": "item 2",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 2,
          "creator_uuid": "9db2c1e2-8421-4150-bf1c-dfd673acf836",
          "property": {
            "expire_date": null,
            "product_group": "Grill",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "sub-1",
            "is_coupon": null,
            "category": "sub-1",
            "featured": "0",
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": "0",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "sub-1",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": []
      },
      {
        "id": 2159,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 10.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 2159,
          "sort_order": 1,
          "uuid": "item-1",
          "barcode": null,
          "type": "ITEM",
          "short_name": "item 1",
          "description": null,
          "status": 1,
          "discountable": 1.0,
          "creator_id": 2,
          "creator_uuid": "9db2c1e2-8421-4150-bf1c-dfd673acf836",
          "property": {
            "expire_date": null,
            "product_group": "Indian",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "sub-1",
            "is_coupon": null,
            "category": "sub-1",
            "featured": "0",
            "unit_of_sale": "KG",
            "discount_type": null,
            "discount_value": null,
            "print_order": "10",
            "print_order_enable": "0",
            "purchase_price": "0",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0"
          },
          "files": []
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "sub-1",
        "selectMainCategoryKey": "",
        "offer": null,
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
      "loginTime": "2025-02-10 15:31:09",
      "logoutTime": "2025-02-10 15:31:09"
    },
    "order_files": [],
    "prescriber": null,
    "payment": null,
    "cash_entry": [],
    "branch": {"id": null, "name": "branch-1", "value": null, "created_at": null, "updated_at": null, "property": null},
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
    _xprintersdkPlugin.bitmapSave(printermodel, ordermodeldata);
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
    butcher_order_barcode_hight: 10,
    butcher_order_barcode_width: 30,
    order_group: true,
    barcode_dpi: 203,
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
