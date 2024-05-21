// To parse this JSON data, do
//
//     final printerBusinessModel = printerBusinessModelFromJson(jsonString);

import 'dart:convert';

PrinterBusinessModel printerBusinessModelFromJson(String str) =>
    PrinterBusinessModel.fromJson(json.decode(str));

String printerBusinessModelToJson(PrinterBusinessModel data) =>
    json.encode(data.toJson());

class PrinterBusinessModel {
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

  PrinterBusinessModel(
      {this.fontSize,
      this.header1Size,
      this.header2Size,
      this.header3Size,
      this.header4Size,
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
      this.asapFontSize});

  factory PrinterBusinessModel.fromJson(Map<String, dynamic> json) =>
      PrinterBusinessModel(
          highlighttextsize: json["highlighttextsize"],
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
          asapFontSize: json["asapFontSize"]);

  Map<String, dynamic> toJson() => {
        "asapFontSize": asapFontSize,
        "highlighttextsize": highlighttextsize,
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
        "footervatFontSize" : footervatFontSize

      };
}
