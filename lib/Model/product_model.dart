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
  String? unitOfSale;
  String? weight;

  ProductPrint({this.name, this.price, this.barcode, this.expire, this.unitOfSale, this.weight});

  factory ProductPrint.fromJson(Map<String, dynamic> json) => ProductPrint(
    name: json["name"],
    price: json["price"],
    barcode: json["barcode"],
    expire: json["expire"],
    unitOfSale: json["unitOfSale"],
    weight: json["weight"],
  );

  Map<String, dynamic> toJson() => {
    "name": name,
    "price": price,
    "barcode": barcode,
    "expire": expire,
    "unitOfSale": unitOfSale,
    "weight": weight,
  };
}
