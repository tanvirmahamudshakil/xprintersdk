import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_navigation/get_navigation.dart';

import 'package:xprintersdk/Model/printerbusinessmodel.dart';
import 'package:xprintersdk/xprintersdk.dart';

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

  final List<PrinterFormData> _printers = [];
  int _printerCounter = 0;

  List<String> usbPathList = [];

  final Map<String, dynamic> _sampleOrder = {
    "id": null,
    "online_order_id": null,
    "service_charge": 0.0,
    "tips": 0.0,
    "order_id": null,
    "errorcode": 0,
    "barcode": "EPOS_1741239813063831",
    "local_id": 1,
    "tableUniqID": 1741239813063831,
    "selectDiscount": 0.0,
    "table_id": null,
    "order_type": "COLLECTION",
    "changeAmount": 0.0,
    "order_channel": "EPOS",
    "order_date": "2025-03-06 05:43:45",
    "requester_type": "PROVIDER",
    "requester_id": 0,
    "requester_uuid": 0,
    "shipping_address_id": 0,
    "requested_delivery_timestamp": "2025-03-06 05:43:45",
    "status": "PROCESSING",
    "net_amount": 5.75,
    "discounted_amount": 0.0,
    "delivery_charge": 0.0,
    "payable_amount": 5.75,
    "payment_type": "NOTPAY",
    "payment_id": 0,
    "prescriber_id": 0,
    "branch_id": 0,
    "comment": "",
    "created_at": "2025-03-06T05:43:45.740299",
    "updated_at": "2025-03-06T05:43:45.740632",
    "order_products": [
      {
        "id": 89365,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 1.5,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 89365,
          "sort_order": 0,
          "uuid": "banoful-butter-cookies-250g-hLG",
          "barcode": "8941114001519",
          "type": "ITEM",
          "short_name": "BANOFUL BUTTER COOKIES 250G",
          "description": "BANOFUL BUTTER COOKIES 250G",
          "status": 1,
          "discountable": 1.0,
          "creator_id": 1,
          "creator_uuid": "9c870ba8-9807-4ece-8f91-55f6754396e5",
          "property": {
            "expire_date": null,
            "product_group_sort": "1",
            "product_group": "",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "bakery",
            "is_coupon": null,
            "category": "bakery",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0",
          },
          "files": [],
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "bakery",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": [],
      },
      {
        "id": 88127,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 1.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 88127,
          "sort_order": 0,
          "uuid": "azka-salted-biscuit-180g-sea",
          "barcode": "8827194382642",
          "type": "ITEM",
          "short_name": "AZKA SALTED BISCUIT 180G",
          "description": "AZKA SALTED BISCUIT 180G",
          "status": 1,
          "discountable": 1.0,
          "creator_id": 1,
          "creator_uuid": "9c870ba8-9807-4ece-8f91-55f6754396e5",
          "property": {
            "expire_date": null,
            "product_group_sort": "1",
            "product_group": "",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "bakery",
            "is_coupon": null,
            "category": "bakery",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0",
          },
          "files": [],
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "bakery",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": [],
      },
      {
        "id": 90116,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 2.25,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 90116,
          "sort_order": 0,
          "uuid": "akij-rice-ball-350g-inx",
          "barcode": "8941174031174",
          "type": "ITEM",
          "short_name": "AKIJ RICE BALL 350G",
          "description": "AKIJ RICE BALL 350G",
          "status": 1,
          "discountable": 1.0,
          "creator_id": 1,
          "creator_uuid": "9c870ba8-9807-4ece-8f91-55f6754396e5",
          "property": {
            "expire_date": null,
            "product_group_sort": "1",
            "product_group": "",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "bakery",
            "is_coupon": null,
            "category": "bakery",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0",
          },
          "files": [],
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "bakery",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": [],
      },
      {
        "id": 89235,
        "master_id": 0,
        "parent_id": null,
        "order_id": null,
        "product_id": null,
        "parent_product_id": null,
        "unit": 1,
        "net_amount": 1.0,
        "discountable_amount": null,
        "comment": null,
        "product": {
          "id": 89235,
          "sort_order": 0,
          "uuid": "6-large-wholemeal-pitta-bread-XQH",
          "barcode": "5027255078702",
          "type": "ITEM",
          "short_name": "6 LARGE WHOLEMEAL PITTA BREAD ",
          "description": "6 LARGE WHOLEMEAL PITTA BREAD ",
          "status": 1,
          "discountable": 1.0,
          "creator_id": 1,
          "creator_uuid": "9c870ba8-9807-4ece-8f91-55f6754396e5",
          "property": {
            "expire_date": null,
            "product_group_sort": "1",
            "product_group": "",
            "platform": null,
            "unit_amount": "0",
            "master": "0",
            "epos_category": "bakery",
            "is_coupon": null,
            "category": "bakery",
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
            "unit_product_type": null,
            "unit_of_purchase": null,
            "available_to_butcher": "0",
          },
          "files": [],
        },
        "components": [],
        "priceEditManage": false,
        "priceEditComment": "",
        "property": null,
        "selectSubCategoryKey": "bakery",
        "selectMainCategoryKey": "",
        "offer": null,
        "bands": [],
      },
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
      "prop": null,
    },
    "waiterInfo": {
      "firstName": "Admin",
      "id": 0,
      "role": "ADMIN",
      "lastName": "",
      "phone": "admin",
      "password": "0000",
      "orderList": [],
      "loginTime": "2025-03-06 05:43:39",
      "logoutTime": "2025-03-06 05:43:39",
    },
    "order_files": [],
    "prescriber": null,
    "payment": null,
    "cash_entry": [],
    "branch": {"id": null, "name": "alislah", "value": null, "created_at": null, "updated_at": null, "property": null},
    "table": null,
    "property": {
      "comment": null,
      "requested_delivery_timestamp_type": "ASAP",
      "total_value_edit": null,
      "total_value_edit_note": null,
    },
    "vat": 0.0,
    "totalvalueEdit": false,
    "totalValueEditcomment": "manager has edited price",
    "isDiscountGivenByAmount": false,
    "isDiscountGivenByUser": false,
    "waiterToEposSend": 1,
    "requestTime": 0,
    "entermanuallyRequestTime": false,
  };

  @override
  void initState() {
    super.initState();
    _addPrinterEntry();
    _initXPrinterBinding();
  }

  @override
  void dispose() {
    for (final printer in _printers) {
      printer.dispose();
    }
    super.dispose();
  }

  Future refreshUsbPathList() async {
    usbPathList = await Xprintersdk().getXPrinterUsbList();
    setState(() {});
  }

  Future<void> _initXPrinterBinding() async {
    await _xprintersdkPlugin.XprinterInitialization();
    _showSnack("XPrinter service ready");
  }

  void _addPrinterEntry() {
    setState(() {
      _printerCounter += 1;
      _printers.add(PrinterFormData(id: _printerCounter));
    });
  }

  void _removePrinterEntry(PrinterFormData form) {
    if (_printers.length == 1) {
      _showSnack("At least one printer card is required.");
      return;
    }
    setState(() {
      _printers.remove(form);
    });
    form.dispose();
  }

  String? _validateForm(PrinterFormData form) {
    if (form.connectionType == 'ipconnection' && form.ipController.text.trim().isEmpty) {
      return "${form.displayName}: enter an IP address";
    }
    if (form.connectionType == 'usbconnection' && form.usbPathController.text.trim().isEmpty) {
      return "${form.displayName}: enter a USB path";
    }
    return null;
  }

  PrinterBusinessModel _buildPrinterModel(PrinterFormData form) {
    final printerJson = Map<String, dynamic>.from(_basePrinterJson);
    printerJson["printer_connection"] = form.connectionType;
    printerJson["ip"] = form.connectionType == 'ipconnection' ? form.ipController.text.trim() : "";
    printerJson["xprinter_path"] = form.connectionType == 'usbconnection' ? form.usbPathController.text.trim() : null;
    return PrinterBusinessModel.fromJson(printerJson);
  }

  Future<void> _connectPrinter(PrinterFormData form) async {
    final validation = _validateForm(form);

    if (validation != null) {
      _showSnack(validation);
      return;
    }
    setState(() {
      form.isBusy = true;
      form.status = "Connecting...";
    });
    try {
      final businessModel = _buildPrinterModel(form);
      print("jcbjashcas ${businessModel.printerConnection}");
      final connected = await _xprintersdkPlugin.XPrinterConnect(businessModel);
      _updateStatus(form, connected ? "Connected successfully" : "Connection failed");
      _showSnack(connected ? "${form.displayName} connected" : "${form.displayName} failed to connect");
    } catch (e) {
      _updateStatus(form, "Connection error");
      _showSnack("Connection error: $e");
    }
  }

  Future<void> _checkPrinter(PrinterFormData form) async {
    final validation = _validateForm(form);
    if (validation != null) {
      _showSnack(validation);
      return;
    }
    setState(() {
      form.isBusy = true;
      form.status = "Checking...";
    });
    try {
      final businessModel = _buildPrinterModel(form);
      final status = await _xprintersdkPlugin.XPrinterConnectionCheck(printermodel: businessModel);
      _updateStatus(form, status ? "Printer is online" : "Printer is offline");
    } catch (e) {
      _updateStatus(form, "Status check failed");
      _showSnack("Status check failed: $e");
    }
  }

  Future<void> _sendSamplePrint(PrinterFormData form) async {
    // final validation = _validateForm(form);
    // if (validation != null) {
    //   _showSnack(validation);
    //   return;
    // }
    // setState(() {
    //   form.isBusy = true;
    //   form.status = "Sending sample order...";
    // });
    try {
      final businessModel = _buildPrinterModel(form);
      var data = await _xprintersdkPlugin.XPrinterPrintOnLineData(
        businessModel,
        Map<String, Object?>.from(_sampleOrder),
      );
      print("sdvjbsjvhsd ${data}");
      _updateStatus(form, "Sample sent to printer");
      _showSnack("${form.displayName}: sample order sent ${data}");
    } catch (e) {
      _updateStatus(form, "Sample print failed");
      _showSnack("Sample print failed: $e");
    }
  }

  void _updateStatus(PrinterFormData form, String status) {
    setState(() {
      form.status = status;
      form.isBusy = false;
    });
  }

  void _showSnack(String message) {
    Get.showSnackbar(GetSnackBar(title: "XPrinter", message: message, duration: const Duration(seconds: 2)));
  }

  Widget _buildPrinterCard(PrinterFormData form) {
    final isIpConnection = form.connectionType == 'ipconnection';
    final theme = Get.context?.theme;
    return Card(
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                Text(form.displayName, style: theme?.textTheme.titleMedium),
                const Spacer(),
                if (_printers.length > 1)
                  IconButton(
                    icon: const Icon(Icons.close),
                    tooltip: "Remove printer",
                    onPressed: () => _removePrinterEntry(form),
                  ),
              ],
            ),
            const SizedBox(height: 12),
            DropdownButtonFormField<String>(
              value: form.connectionType,
              decoration: const InputDecoration(labelText: "Connection type"),
              items: const [
                DropdownMenuItem(value: 'ipconnection', child: Text('LAN (IP)')),
                DropdownMenuItem(value: 'usbconnection', child: Text('USB')),
              ],
              onChanged: (value) {
                if (value == null) return;
                setState(() {
                  form.connectionType = value;
                });
              },
            ),
            const SizedBox(height: 12),
            if (isIpConnection)
              TextField(
                controller: form.ipController,
                decoration: const InputDecoration(labelText: 'Printer IP address', hintText: 'e.g. 192.168.0.100'),
                keyboardType: TextInputType.text,
              )
            else
              DropdownButtonFormField(
                // controller: form.usbPathController,
                items: List.generate(usbPathList.length, (index) {
                  var d = usbPathList[index];
                  return DropdownMenuItem(child: Text(d.toString()), value: d);
                }),
                decoration: InputDecoration(
                  labelText: 'USB path',
                  hintText: '/dev/bus/usb/001/002',
                  prefixIcon: IconButton(
                    onPressed: () {
                      refreshUsbPathList();
                    },
                    icon: Icon(Icons.refresh),
                  ),
                ),
                onChanged: (value) {
                  setState(() {
                    form.usbPathController.text = value!;
                  });
                },
              ),
            const SizedBox(height: 16),
            if (form.isBusy) ...[const LinearProgressIndicator(), const SizedBox(height: 12)],
            Wrap(
              spacing: 12,
              runSpacing: 12,
              children: [
                ElevatedButton.icon(
                  onPressed: () => _connectPrinter(form),
                  icon: const Icon(Icons.link),
                  label: const Text('Connect'),
                ),
                OutlinedButton.icon(
                  onPressed: form.isBusy ? null : () => _checkPrinter(form),
                  icon: const Icon(Icons.check_circle),
                  label: const Text('Check'),
                ),
                TextButton.icon(
                  onPressed: () => _sendSamplePrint(form),
                  icon: const Icon(Icons.print),
                  label: const Text('Send sample'),
                ),
              ],
            ),
            const SizedBox(height: 12),
            Text(
              form.status,
              style: TextStyle(color: form.status.toLowerCase().contains("fail") ? Colors.red : Colors.green),
            ),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(title: const Text('XPrinter multi-connector')),
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('Manage Multiple XPrinters', style: Theme.of(context).textTheme.titleLarge),
                  const SizedBox(height: 8),
                  const Text('Add each printer with its IP or USB path, then connect and check them individually.'),
                  const SizedBox(height: 12),
                  Row(
                    children: [
                      ElevatedButton.icon(
                        onPressed: _initXPrinterBinding,
                        icon: const Icon(Icons.power_settings_new),
                        label: const Text('Re-initialize service'),
                      ),
                      const SizedBox(width: 12),
                      Text('Initialize once after the device boots.', style: Theme.of(context).textTheme.bodySmall),
                    ],
                  ),
                ],
              ),
            ),
            const Divider(height: 1),
            Expanded(
              child:
                  _printers.isEmpty
                      ? const Center(child: Text('Tap + to add a printer.'))
                      : ListView.builder(
                        itemCount: _printers.length,
                        itemBuilder: (context, index) => _buildPrinterCard(_printers[index]),
                      ),
            ),
          ],
        ),
        floatingActionButton: FloatingActionButton(onPressed: _addPrinterEntry, child: const Icon(Icons.add)),
      ),
    );
  }

  final Map<String, dynamic> _basePrinterJson = {
    "starter_group": true,
    "grocery_barcode_hight": 5,
    "grocery_barcode_width": 30,
    "grocery_barcode_text_show": true,
    "grocery_barcode_text_size": 10,
    "yuma_productid_length": 8,
    "business_email": "yuma@tech.com",
    "invoice_type": "GROCERY",
    "yuma_netamount_length": 5,
    "yuma_weight_length": 2,
    "yuma_totalprice_length": 5,
    "branchNameShow": true,
    "barcode_text_show": true,
    "barcode_text_size": 14,
    "order_group": false,
    "butcher_order_barcode_hight": 200,
    "butcher_order_barcode_width": 700,
    "barcode_dpi": 420,
    "singleProductBarcodeHight": 2,
    "butcherStickerFont": 1,
    "expireDateShow": true,
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
    "orderChannel": "EPOS",
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
    "select_printer": "xprinter",
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
    "header2Size": 15,
    "header3Size": 17,
    "header4Size": 15,
    "footervatFontSize": 15,
    "serviceCharge": false,
    "xprinter_path": null,
    "propertyshop": false,
    "show_category_name": false,
  };
}

class PrinterFormData {
  PrinterFormData({required this.id});

  final int id;
  String connectionType = 'ipconnection';
  bool isBusy = false;
  String status = 'Waiting for action';
  final TextEditingController ipController = TextEditingController();
  final TextEditingController usbPathController = TextEditingController();

  String get displayName => 'Printer $id';

  void dispose() {
    ipController.dispose();
    usbPathController.dispose();
  }
}
