// To parse this JSON data, do
//
//     final banquetOffer = banquetOfferFromJson(jsonString);

import 'dart:convert';

List<BanquetOffer> banquetOfferFromJson(String str) => List<BanquetOffer>.from(json.decode(str).map((x) => BanquetOffer.fromJson(x)));

String banquetOfferToJson(List<BanquetOffer> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class BanquetOffer {
  int? id;
  int? offerProductId;
  String? categoryId;
  int? freeQuantity;
  dynamic createdAt;
  dynamic updatedAt;

  BanquetOffer({this.id, this.offerProductId, this.categoryId, this.freeQuantity, this.createdAt, this.updatedAt});

  factory BanquetOffer.fromJson(Map<String, dynamic> json) => BanquetOffer(
    id: json["id"],
    offerProductId: json["offer_product_id"],
    categoryId: json["category_id"],
    freeQuantity: json["free_quantity"],
    createdAt: json["created_at"],
    updatedAt: json["updated_at"],
  );

  Map<String, dynamic> toJson() => {"id": id, "offer_product_id": offerProductId, "category_id": categoryId, "free_quantity": freeQuantity, "created_at": createdAt, "updated_at": updatedAt};
}
