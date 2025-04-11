// To parse this JSON data, do
//
//     final printerBusinessModel = printerBusinessModelFromJson(jsonString);

import 'dart:convert';

PrinterBusinessModel printerBusinessModelFromJson(String str) => PrinterBusinessModel.fromJson(json.decode(str));

String printerBusinessModelToJson(PrinterBusinessModel data) => json.encode(data.toJson());

class PrinterBusinessModel {
  bool? weightShow;
  String orderChannel;
  int? header1Size;
  int? header2Size;
  int? header3Size;
  int? header4Size;
  int? fontSize;
  int printOnDelivery;
  int printOnCollection;
  int printOnTableOrder;
  int printOnTackwayOrder;
  bool? autoPrint;
  bool? showOrderNoInvoice;
  bool? serviceCharge;
  String selectPrinter;
  String printerConnection;
  String? ip;
  String? bluetoothName;
  String? bluetoothAddress;
  String businessname;
  String businessphone;
  String businessaddress;
  int? highlight;
  int highlighttextsize;
  int? papersSize;
  String dynamicCollection;
  String dynamicDelivery;
  String dynamicEatIn;
  String dynamicTakeaway;
  String? vatNumber;
  String? vatCompanyName;
  String? vatNote;
  String? printerStyle;
  int? asapFontSize;
  int? footervatFontSize;
  bool weightMultiplyingPrice;
  String? xprinterPath;
  bool? propertyshop;
  int? labelPrinterHight;
  int? labelPrinterWidth;
  int? dpi;
  int? barcode_hight;
  int? barcode_width;
  int? barcode_x;
  int? barcode_y;
  int itemNameFont;
  int itemPricefont;
  int itemWeightfont;
  int expireDateFont;
  int businessNameFont;
  int pricePerWeightFont;
  int singleProductNameFont;
  int singleProductPriceFont;
  int singleProductExpireFont;
  int singleProductPrintHight;
  int singleProductBarcodeHight;
  int singleProductBarCodeWidth;
  int maxItemPrint;
  String butcherPrintStyle;
  int butcherStickerFont;
  bool expireDateShow;
  bool branchNameShow;
  int barcode_dpi;
  int butcher_order_barcode_width;
  int butcher_order_barcode_hight;
  bool order_group;
  bool show_category_name;
  bool barcode_text_show;
  int barcode_text_size;

  int yuma_productid_length;
  int yuma_netamount_length;
  int yuma_weight_length;
  int yuma_totalprice_length;
  String invoice_type;
  String business_email;
  int grocery_barcode_hight;

  int grocery_barcode_width;
  bool grocery_barcode_text_show;

  int grocery_barcode_text_size;
  String expire_name;
  bool starter_group;

  PrinterBusinessModel({
    this.fontSize,
    required this.expire_name,
    required this.grocery_barcode_hight,
    required this.grocery_barcode_width,
    required this.grocery_barcode_text_show,
    required this.grocery_barcode_text_size,
    required this.business_email,
    required this.yuma_productid_length,
    required this.yuma_netamount_length,
    required this.yuma_weight_length,
    required this.yuma_totalprice_length,
    required this.order_group,
    required this.barcode_dpi,
    required this.butcher_order_barcode_width,
    required this.butcher_order_barcode_hight,
    required this.expireDateShow,
    required this.branchNameShow,
    required this.butcherPrintStyle,
    required this.labelPrinterHight,
    this.labelPrinterWidth,
    required this.butcherStickerFont,
    this.barcode_hight,
    this.barcode_width,
    required this.maxItemPrint,
    this.weightShow = false,
    required this.orderChannel,
    this.header1Size,
    required this.weightMultiplyingPrice,
    this.header2Size,
    this.serviceCharge = false,
    this.header3Size,
    this.header4Size,
    this.dpi,
    required this.highlighttextsize,
    required this.printOnDelivery,
    required this.printOnCollection,
    required this.printOnTableOrder,
    required this.printOnTackwayOrder,
    this.autoPrint,
    this.showOrderNoInvoice,
    required this.selectPrinter,
    required this.printerConnection,
    this.footervatFontSize,
    this.ip,
    this.bluetoothName,
    this.bluetoothAddress,
    required this.businessname,
    required this.businessphone,
    required this.businessaddress,
    this.highlight,
    this.papersSize,
    required this.dynamicCollection,
    required this.dynamicDelivery,
    required this.dynamicEatIn,
    required this.dynamicTakeaway,
    this.vatNumber,
    this.vatCompanyName,
    this.vatNote,
    this.printerStyle,
    this.asapFontSize,
    this.barcode_x,
    this.barcode_y,
    this.xprinterPath,
    this.propertyshop,
    required this.itemNameFont,
    required this.itemPricefont,
    required this.itemWeightfont,
    required this.expireDateFont,
    required this.businessNameFont,
    required this.pricePerWeightFont,
    required this.singleProductNameFont,
    required this.singleProductExpireFont,
    required this.singleProductPriceFont,
    required this.singleProductPrintHight,
    required this.singleProductBarcodeHight,
    required this.singleProductBarCodeWidth,
    required this.show_category_name,
    required this.barcode_text_show,
    required this.barcode_text_size,
    required this.invoice_type,
    required this.starter_group,
  });

  factory PrinterBusinessModel.fromJson(Map<String, dynamic> json) => PrinterBusinessModel(
        expire_name: json["expire_name"],
        starter_group: json["starter_group"],
        grocery_barcode_hight: json["grocery_barcode_hight"],
        grocery_barcode_width: json["grocery_barcode_width"],
        grocery_barcode_text_show: json["grocery_barcode_text_show"],
        grocery_barcode_text_size: json["grocery_barcode_text_size"],
        yuma_productid_length: json["yuma_productid_length"],
        business_email: json["business_email"],
        invoice_type: json["invoice_type"],
        yuma_netamount_length: json["yuma_netamount_length"],
        yuma_weight_length: json["yuma_weight_length"],
        yuma_totalprice_length: json["yuma_totalprice_length"],
        order_group: json["order_group"],
        barcode_text_show: json["barcode_text_show"],
        barcode_text_size: json["barcode_text_size"],
        pricePerWeightFont: json["price_per_weight_font"],
        butcher_order_barcode_hight: json["butcher_order_barcode_hight"],
        butcher_order_barcode_width: json["butcher_order_barcode_width"],
        barcode_dpi: json["barcode_dpi"],
        branchNameShow: json["branchNameShow"],
        expireDateShow: json["expireDateShow"],
        butcherStickerFont: json["butcherStickerFont"],
        maxItemPrint: json["maxItemPrint"],
        butcherPrintStyle: json["butcherPrintStyle"],
        singleProductBarcodeHight: json["singleProductBarcodeHight"],
        singleProductBarCodeWidth: json["singleProductBarCodeWidth"],
        singleProductExpireFont: json["singleProductExpireFont"],
        singleProductPriceFont: json["singleProductPriceFont"],
        singleProductNameFont: json["singleProductNameFont"],
        singleProductPrintHight: json["singleProductPrintHight"],
        businessNameFont: json["business_name_font"],
        expireDateFont: json["expire_date_font"],
        itemNameFont: json["item_name_font"],
        itemPricefont: json["item_price_font"],
        itemWeightfont: json["item_weight_font"],
        barcode_hight: json["barcode_hight"],
        barcode_width: json["barcode_width"],
        barcode_x: json["barcode_x"],
        barcode_y: json["barcode_y"],
        weightShow: json["weightShow"],
        dpi: json["dpi"],
        labelPrinterHight: json["label_hight"],
        labelPrinterWidth: json["label_width"],
        orderChannel: json["orderChannel"],
        highlighttextsize: json["highlighttextsize"],
        weightMultiplyingPrice: json["weightMultiplyingPrice"],
        header1Size: json["header1Size"],
        header2Size: json["header2Size"],
        header3Size: json["header3Size"],
        header4Size: json["header4Size"],
        fontSize: json["font_size"],
        printOnDelivery: json["print_on_delivery"],
        printOnCollection: json["print_on_collection"],
        printOnTableOrder: json["print_on_table_order"],
        printOnTackwayOrder: json["print_on_tackway_order"],
        autoPrint: json["auto_print"],
        serviceCharge: json["serviceCharge"],
        showOrderNoInvoice: json["show_order_no_invoice"],
        selectPrinter: json["select_printer"],
        printerConnection: json["printer_connection"],
        ip: json["ip"],
        bluetoothName: json["bluetooth_name"],
        bluetoothAddress: json["bluetooth_address"],
        businessname: json["businessname"],
        businessphone: json["businessphone"],
        businessaddress: json["businessaddress"],
        highlight: json["highlight"],
        papersSize: json["papersize"],
        dynamicCollection: json['dynamicCollection'],
        dynamicDelivery: json['dynamicDelivery'],
        dynamicEatIn: json['dynamicEatIn'],
        dynamicTakeaway: json['dynamicTakeaway'],
        vatNumber: json["vat_number"],
        vatCompanyName: json["vat_company_name"],
        vatNote: json["vat_note"],
        printerStyle: json["printer_style"],
        footervatFontSize: json["footervatFontSize"],
        asapFontSize: json["asapFontSize"],
        xprinterPath: json["xprinter_path"],
        propertyshop: json["propertyshop"],
        show_category_name: json["show_category_name"],
      );

  Map<String, dynamic> toJson() => {
        "invoice_type": invoice_type,
        "starter_group": starter_group,
        "expire_name": expire_name,
        "grocery_barcode_hight": grocery_barcode_hight,
        "grocery_barcode_width": grocery_barcode_width,
        "grocery_barcode_text_show": grocery_barcode_text_show,
        "grocery_barcode_text_size": grocery_barcode_text_size,
        "business_email": business_email,
        "yuma_productid_length": yuma_productid_length,
        "yuma_netamount_length": yuma_netamount_length,
        "yuma_weight_length": yuma_weight_length,
        "yuma_totalprice_length": yuma_totalprice_length,
        "branchNameShow": branchNameShow,
        "barcode_text_show": barcode_text_show,
        "barcode_text_size": barcode_text_size,
        "order_group": order_group,
        "butcher_order_barcode_hight": butcher_order_barcode_hight,
        "butcher_order_barcode_width": butcher_order_barcode_width,
        "barcode_dpi": barcode_dpi,
        "singleProductBarcodeHight": singleProductBarcodeHight,
        "butcherStickerFont": butcherStickerFont,
        "expireDateShow": expireDateShow,
        "maxItemPrint": maxItemPrint,
        "butcherPrintStyle": butcherPrintStyle,
        "singleProductBarCodeWidth": singleProductBarCodeWidth,
        "price_per_weight_font": pricePerWeightFont,
        "singleProductExpireFont": singleProductExpireFont,
        "singleProductPriceFont": singleProductPriceFont,
        "singleProductNameFont": singleProductNameFont,
        "singleProductPrintHight": singleProductPrintHight,
        "business_name_font": businessNameFont,
        "expire_date_font": expireDateFont,
        "item_name_font": itemNameFont,
        "item_price_font": itemPricefont,
        "item_weight_font": itemWeightfont,
        "barcode_hight": barcode_hight,
        "barcode_x": barcode_x,
        "barcode_y": barcode_y,
        "barcode_width": barcode_width,
        "label_hight": labelPrinterHight,
        "label_width": labelPrinterWidth,
        "orderChannel": orderChannel,
        "weightShow": weightShow ?? false,
        "asapFontSize": asapFontSize,
        "highlighttextsize": highlighttextsize,
        "weightMultiplyingPrice": weightMultiplyingPrice,
        "font_size": fontSize,
        "print_on_delivery": printOnDelivery,
        "print_on_collection": printOnCollection,
        "print_on_table_order": printOnTableOrder,
        "print_on_tackway_order": printOnTackwayOrder,
        "auto_print": autoPrint,
        "show_order_no_invoice": showOrderNoInvoice,
        "select_printer": selectPrinter,
        "printer_connection": printerConnection,
        "ip": ip,
        "dpi": dpi,
        "bluetooth_name": bluetoothName,
        "bluetooth_address": bluetoothAddress,
        "businessname": businessname,
        "businessphone": businessphone,
        "businessaddress": businessaddress,
        "highlight": highlight,
        "papersize": papersSize,
        "dynamicCollection": dynamicCollection,
        "dynamicDelivery": dynamicDelivery,
        "dynamicEatIn": dynamicEatIn,
        "dynamicTakeaway": dynamicTakeaway,
        "vat_number": vatNumber,
        "vat_company_name": vatCompanyName,
        "vat_note": vatNote,
        "printer_style": printerStyle,
        "header1Size": header1Size,
        "header2Size": header2Size,
        "header3Size": header3Size,
        "header4Size": header4Size,
        "footervatFontSize": footervatFontSize,
        "serviceCharge": serviceCharge,
        "xprinter_path": xprinterPath,
        "propertyshop": propertyshop,
        "show_category_name": show_category_name,
      };
}
