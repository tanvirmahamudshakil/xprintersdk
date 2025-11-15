// To parse this JSON data, do
//
//     final productPrint = productPrintFromJson(jsonString);

import 'dart:convert';

ProductPrint productPrintFromJson(String str) => ProductPrint.fromJson(json.decode(str));

String productPrintToJson(ProductPrint data) => json.encode(data.toJson());

class ProductPrint {
  String? name;
  String? price;
  String? barcode;
  String? expire;

  ProductPrint({this.name, this.price, this.barcode, this.expire});

  factory ProductPrint.fromJson(Map<String, dynamic> json) =>
      ProductPrint(name: json["name"], price: json["price"], barcode: json["barcode"], expire: json["expire"]);

  Map<String, dynamic> toJson() => {"name": name, "price": price, "barcode": barcode, "expire": expire};
}
