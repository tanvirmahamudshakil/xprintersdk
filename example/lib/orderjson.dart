var orderjson2 = {
  "id": 10601,
  "local_id": null,
  "order_type": "COLLECTION",
  "order_channel": "ONLINE",
  "order_date": "2023-10-30 16:41:51",
  "requester_type": "GUEST",
  "requester_id": 7537,
  "requester_uuid": "9a7e924c-4700-42fe-8e7e-89332b8d320a",
  "shipping_address_id": null,
  "requested_delivery_timestamp": "2023-10-30 17:01:49",
  "status": "CLOSED",
  "net_amount": 12.5,
  "discounted_amount": 1.25,
  "delivery_charge": 0,
  "payable_amount": 11.25,
  "payment_type": "CARD",
  "payment_id": 75003,
  "prescriber_id": null,
  "branch_id": 1002,
  "comment": null,
  "created_at": "2023-10-30T16:41:52.000000Z",
  "updated_at": "2023-10-30T16:48:19.000000Z",
  "order_products": [
    {
      "id": 23666,
      "parent_id": null,
      "order_id": 10601,
      "product_id": 2069,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 4.5,
      "discountable_amount": 0,
      "comment": null,
      "product": {
        "id": 2069,
        "sort_order": 1,
        "uuid": "chicken-hallumi-wrap",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken Hallumi Wrap",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Chicken Hallumi Wrap,chickenhallumiwrap,category,category,wraps,wraps,Lettuce & mayonnaise",
        "property": {
          "short_description": "Lettuce & mayonnaise",
          "platform": "BOTH",
          "epos_category": "wraps",
          "is_coupon": "FALSE",
          "category": "wraps"
        },
        "files": []
      },
      "components": [
        {
          "id": 23667,
          "parent_id": 23666,
          "order_id": 10601,
          "product_id": 2010,
          "parent_product_id": 2069,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": "BOTH", "is_coupon": "FALSE"}
          }
        }
      ]
    },
    {
      "id": 23668,
      "parent_id": null,
      "order_id": 10601,
      "product_id": 2053,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 5.5,
      "discountable_amount": 0,
      "comment": null,
      "product": {
        "id": 2053,
        "sort_order": 11,
        "uuid": "chicken-doner",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken Doner",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags": "Chicken Doner,chickendoner,category,category,doner,doner,",
        "property": {
          "platform": "BOTH",
          "epos_category": "doner",
          "is_coupon": "FALSE",
          "category": "doner"
        },
        "files": []
      },
      "components": [
        {
          "id": 23669,
          "parent_id": 23668,
          "order_id": 10601,
          "product_id": 2012,
          "parent_product_id": 2053,
          "unit": 1,
          "net_amount": 2.5,
          "discountable_amount": 0,
          "comment": null,
          "components": [
            {
              "id": 23670,
              "parent_id": 23669,
              "order_id": 10601,
              "product_id": 2182,
              "parent_product_id": 2012,
              "unit": 1,
              "net_amount": 0,
              "discountable_amount": 0,
              "comment": null,
              "components": [],
              "product": {
                "id": 2182,
                "sort_order": 29,
                "uuid": "9a738e85-b646-44e4-8d57-aa18c6d40c5e",
                "barcode": null,
                "type": "COMPONENT",
                "short_name": "Dr Pepper (Can)",
                "description": null,
                "status": 1,
                "discountable": 1,
                "creator_id": 2,
                "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
                "tags": "addon",
                "property": {
                  "platform": "BOTH",
                  "item_type": "addon",
                  "is_coupon": "FALSE"
                }
              }
            }
          ],
          "product": {
            "id": 2012,
            "sort_order": 2,
            "uuid": "meal-deal-chips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Meal Deal (Chips)",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": "BOTH", "is_coupon": "FALSE"}
          }
        },
        {
          "id": 23671,
          "parent_id": 23668,
          "order_id": 10601,
          "product_id": 2010,
          "parent_product_id": 2053,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": "BOTH", "is_coupon": "FALSE"}
          }
        }
      ]
    },
    {
      "id": 23672,
      "parent_id": null,
      "order_id": 10601,
      "product_id": 2019,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 1.25,
      "discountable_amount": 0,
      "comment": "discounted",
      "product": {
        "id": 2019,
        "sort_order": 2,
        "uuid": "onallorder",
        "barcode": null,
        "type": "DISCOUNT",
        "short_name": "ONALLORDER",
        "description": "10% OFF on all order",
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags": null,
        "property": {
          "platform": "WEB",
          "discount_type": "PERCENTAGE",
          "discount_value": "10",
          "is_coupon": "FALSE"
        },
        "files": []
      },
      "components": []
    }
  ],
  "requester": null,
  "requester_guest": {
    "id": 7537,
    "uuid": "9a7e924c-4700-42fe-8e7e-89332b8d320a",
    "first_name": "William",
    "last_name": "Shepherd",
    "email": "willshepherd696@icloud.com",
    "phone": "07488301680",
    "created_at": "2023-10-30T16:41:50.000000Z",
    "updated_at": "2023-10-30T16:41:50.000000Z"
  },
  "shipping_address": null,
  "order_files": [],
  "prescriber": null,
  "payment": {
    "id": 75003,
    "requester_type": "GUEST",
    "requester_id": 7537,
    "reference": "23103016ZT42VM37NK14CWH",
    "payment_date": "2023-10-30 16:42:44",
    "amount": 1125,
    "source": "STREAM",
    "card_type": "VD",
    "status": "APPROVED",
    "comment": null,
    "created_at": "2023-10-30T16:42:44.000000Z",
    "updated_at": "2023-10-30T16:42:44.000000Z"
  },
  "cash_entry": [],
  "branch": {
    "id": 1002,
    "name": "Sundon Park",
    "value": "sundon-park",
    "created_at": null,
    "updated_at": null,
    "property": {
      "phone": "01582354091",
      "email": "contact@theflava.co.uk",
      "address": "4, Ashwell Parade, Luton LU3 3AZ",
      "postcode": "LU3 3AZ"
    }
  }
};

var localorder = {
  "comment": "",
  "createdAt": "2023-11-04T09:31:39.283829",
  "deliveryCharge": 3,
  "discountedAmount": 0,
  "id": 0,
  "localId": 5,
  "netAmount": 28.18,
  "orderChannel": "EPOS",
  "orderDate": "2023-11-04T09:31:39.283868",
  "orderType": "DELIVERY",
  "payableAmount": 28.18,
  "paymentId": 0,
  "paymentType": "NOTPAY",
  "prescriberId": null,
  "requestedDeliveryTimestamp": "2023-11-04T10:11:39.282136",
  "requesterId": 0,
  "requesterType": "PROVIDER",
  "requesterUuid": "",
  "shippingAddressId": 0,
  "status": "NEW",
  "updatedAt": "2023-11-04T09:31:39.283844",
  "items": [
    {
      "id": 2002,
      "unit": 1,
      "comment": "dfddfvf",
      "shortName": "Half Chicken",
      "type": "ITEM",
      "currency": "£",
      "price": 6.99,
      "isDiscountApplied": null,
      "discountPrice": null,
      "components": [
        {
          "id": 2014,
          "shortName": "Peri Peri",
          "type": "COMPONENT",
          "groupName": "Flavours",
          "currency": "",
          "price": 0,
          "unit": 1,
          "comment": "",
          "components": {
            "id": 2086,
            "shortName": "Hot",
            "type": "COMPONENT",
            "groupName": null,
            "currency": "",
            "price": 0,
            "unit": 1,
            "comment": "",
            "components": null
          }
        },
        {
          "id": 2013,
          "shortName": "Meal Deal (Rice)",
          "type": "COMPONENT",
          "groupName": "Choose One",
          "currency": "£",
          "price": 3,
          "unit": 1,
          "comment": "",
          "components": {
            "id": 2024,
            "shortName": "Mirianda Orange",
            "type": "COMPONENT",
            "groupName": null,
            "currency": "",
            "price": 0,
            "unit": 1,
            "comment": "",
            "components": null
          }
        },
        {
          "id": 2017,
          "shortName": "Chili Dips",
          "type": "COMPONENT",
          "groupName": "Extra",
          "currency": "£",
          "price": 0.2,
          "unit": 1,
          "comment": "",
          "components": null
        },
        {
          "id": 2016,
          "shortName": "Mayonnaise Dips",
          "type": "COMPONENT",
          "groupName": "Extra",
          "currency": "£",
          "price": 0.2,
          "unit": 1,
          "comment": "",
          "components": null
        }
      ],
      "extra": [
        {
          "id": 2172,
          "shortName": "Dressing 1",
          "type": "COMPONENT",
          "groupName": null,
          "currency": "",
          "price": 0,
          "unit": 1,
          "comment": "dressing",
          "components": null
        },
        {
          "id": 2173,
          "shortName": "Dressing 2",
          "type": "COMPONENT",
          "groupName": null,
          "currency": "",
          "price": 0,
          "unit": 1,
          "comment": "dressing",
          "components": null
        },
        {
          "id": 2168,
          "shortName": "Letuce",
          "type": "COMPONENT",
          "groupName": null,
          "currency": "",
          "price": 0,
          "unit": 1,
          "comment": "topping",
          "components": null
        },
        {
          "id": 2176,
          "shortName": "Add On 2",
          "type": "COMPONENT",
          "groupName": null,
          "currency": "",
          "price": 0,
          "unit": 1,
          "comment": "addon",
          "components": null
        },
        {
          "id": 2175,
          "shortName": "Add On 1",
          "type": "COMPONENT",
          "groupName": null,
          "currency": "£",
          "price": 0.5,
          "unit": 1,
          "comment": "addon",
          "components": null
        }
      ],
      "subcategorykey": "grill-items"
    },
    {
      "id": 2113,
      "unit": 1,
      "comment": "",
      "shortName": "Diet Pepsi (1.5 Litre)",
      "type": "ITEM",
      "currency": "£",
      "price": 3.1,
      "isDiscountApplied": null,
      "discountPrice": null,
      "components": [],
      "extra": [],
      "subcategorykey": "drinks"
    },
    {
      "id": 2120,
      "unit": 1,
      "comment": "",
      "shortName": "Dr Pepper (Can)",
      "type": "ITEM",
      "currency": "£",
      "price": 1.3,
      "isDiscountApplied": null,
      "discountPrice": null,
      "components": [],
      "extra": [],
      "subcategorykey": "drinks"
    },
    {
      "id": 962509,
      "unit": 1,
      "comment": "",
      "shortName": "dfbdbfb",
      "type": "ITEM",
      "currency": "£",
      "price": 5,
      "isDiscountApplied": null,
      "discountPrice": null,
      "components": [],
      "extra": [],
      "subcategorykey": ""
    },
    {
      "id": 2009,
      "unit": 1,
      "comment": "",
      "shortName": "Chicken Strips",
      "type": "ITEM",
      "currency": "£",
      "price": 4.99,
      "isDiscountApplied": null,
      "discountPrice": null,
      "components": [
        {
          "id": 2012,
          "shortName": "Meal Deal (Chips)",
          "type": "COMPONENT",
          "groupName": "Choose One",
          "currency": "£",
          "price": 2.5,
          "unit": 1,
          "comment": "",
          "components": {
            "id": 2024,
            "shortName": "Mirianda Orange",
            "type": "COMPONENT",
            "groupName": null,
            "currency": "",
            "price": 0,
            "unit": 1,
            "comment": "",
            "components": null
          }
        },
        {
          "id": 2015,
          "shortName": "BBQ",
          "type": "COMPONENT",
          "groupName": "Flavours",
          "currency": "",
          "price": 0,
          "unit": 1,
          "comment": "",
          "components": null
        },
        {
          "id": 2017,
          "shortName": "Chili Dips",
          "type": "COMPONENT",
          "groupName": "Extra",
          "currency": "£",
          "price": 0.2,
          "unit": 1,
          "comment": "",
          "components": null
        },
        {
          "id": 2016,
          "shortName": "Mayonnaise Dips",
          "type": "COMPONENT",
          "groupName": "Extra",
          "currency": "£",
          "price": 0.2,
          "unit": 1,
          "comment": "",
          "components": null
        }
      ],
      "extra": [],
      "subcategorykey": "grill-items"
    }
  ],
  "customer": {
    "firstName": "Tanvir",
    "lastName": "Mahamud",
    "phone": "01932331718",
    "email": "",
    "address": {
      "type": "PRIMARY",
      "building": "5",
      "street": "Dunstable Road",
      "city": "",
      "postcode": ""
    }
  },
  "cashEntry": null
};
// var orderjson = {
//   "id": 10630,
//   "local_id": null,
//   "order_type": "COLLECTION",
//   "order_channel": "ONLINE",
//   "order_date": "2023-10-30 06:12:21",
//   "requester_type": "GUEST",
//   "requester_id": 7486,
//   "requester_uuid": "9a7db12d-79da-4fda-8b02-6f6a88b5d5ad",
//   "shipping_address_id": null,
//   "requested_delivery_timestamp": "2023-10-30 15:20:00",
//   "status": "NEW",
//   "net_amount": 87.87,
//   "discounted_amount": 8.787,
//   "delivery_charge": 0,
//   "payable_amount": 79.083,
//   "payment_type": "CASH",
//   "payment_id": null,
//   "prescriber_id": null,
//   "branch_id": 1001,
//   "comment": null,
//   "created_at": "2023-10-30T06:12:21.000000Z",
//   "updated_at": "2023-10-30T06:12:22.000000Z",
//   "order_products": [
//     {
//       "id": 24209,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2001,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 11.99,
//       "discountable_amount": 0,
//       "comment": "lkjolkoln",
//       "product": {
//         "id": 2001,
//         "sort_order": 1,
//         "uuid": "whole-chicken",
//         "barcode": null,
//         "type": "ITEM",
//         "short_name": "Whole Chicken",
//         "description": null,
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": "Whole Chicken,wholechicken,category,category,grill items,grillitems,",
//         "property": {
//           "platform": "WEB",
//           "epos_category": "grill-items",
//           "is_coupon": "FALSE",
//           "category": "grill-items",
//           "featured": "0"
//         },
//         "files": [
//           {
//             "id": 1,
//             "product_id": 2001,
//             "file_name": "roast-chicken_1692795352.jpg",
//             "type": "general",
//             "file_path": "/var/www/html/ordering_platform_php/flava/api/public/files/products"
//           }
//         ]
//       },
//       "components": [
//         {
//           "id": 24210,
//           "parent_id": 24209,
//           "order_id": 10630,
//           "product_id": 2010,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2010,
//             "sort_order": 1,
//             "uuid": "normal",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Normal",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24211,
//           "parent_id": 24209,
//           "order_id": 10630,
//           "product_id": 2016,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2016,
//             "sort_order": 6,
//             "uuid": "mayonnaise-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Mayonnaise Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24212,
//           "parent_id": 24209,
//           "order_id": 10630,
//           "product_id": 2017,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2017,
//             "sort_order": 7,
//             "uuid": "chili-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Chili Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24213,
//           "parent_id": 24209,
//           "order_id": 10630,
//           "product_id": 2014,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24214,
//               "parent_id": 24213,
//               "order_id": 10630,
//               "product_id": 2086,
//               "parent_product_id": 2014,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2086,
//                 "sort_order": 1,
//                 "uuid": "hot",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Hot",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2014,
//             "sort_order": 4,
//             "uuid": "peri-peri",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Peri Peri",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24215,
//           "parent_id": 24209,
//           "order_id": 10630,
//           "product_id": 2015,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2015,
//             "sort_order": 5,
//             "uuid": "bbq",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "BBQ",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": "topping",
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     },
//     {
//       "id": 24216,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2002,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 6.99,
//       "discountable_amount": 0,
//       "comment": "lpjpo",
//       "product": {
//         "id": 2002,
//         "sort_order": 2,
//         "uuid": "half-chicken",
//         "barcode": null,
//         "type": "ITEM",
//         "short_name": "Half Chicken",
//         "description": null,
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": "Half Chicken,halfchicken,category,category,grill items,grillitems,",
//         "property": {
//           "platform": "BOTH",
//           "epos_category": "grill-items",
//           "is_coupon": "FALSE",
//           "category": "grill-items"
//         },
//         "files": []
//       },
//       "components": [
//         {
//           "id": 24217,
//           "parent_id": 24216,
//           "order_id": 10630,
//           "product_id": 2012,
//           "parent_product_id": 2002,
//           "unit": 1,
//           "net_amount": 2.5,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24218,
//               "parent_id": 24217,
//               "order_id": 10630,
//               "product_id": 2024,
//               "parent_product_id": 2012,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2024,
//                 "sort_order": 11,
//                 "uuid": "mirianda-orange",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Mirianda Orange",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2012,
//             "sort_order": 2,
//             "uuid": "meal-deal-chips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Meal Deal (Chips)",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24219,
//           "parent_id": 24216,
//           "order_id": 10630,
//           "product_id": 2014,
//           "parent_product_id": 2002,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24220,
//               "parent_id": 24219,
//               "order_id": 10630,
//               "product_id": 2127,
//               "parent_product_id": 2014,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2127,
//                 "sort_order": 1,
//                 "uuid": "lemon",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Lemon",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2014,
//             "sort_order": 4,
//             "uuid": "peri-peri",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Peri Peri",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24221,
//           "parent_id": 24216,
//           "order_id": 10630,
//           "product_id": 2016,
//           "parent_product_id": 2002,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2016,
//             "sort_order": 6,
//             "uuid": "mayonnaise-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Mayonnaise Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24222,
//           "parent_id": 24216,
//           "order_id": 10630,
//           "product_id": 2017,
//           "parent_product_id": 2002,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2017,
//             "sort_order": 7,
//             "uuid": "chili-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Chili Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     },
//     {
//       "id": 24223,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2003,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 4.5,
//       "discountable_amount": 0,
//       "comment": "joihoiu",
//       "product": {
//         "id": 2003,
//         "sort_order": 3,
//         "uuid": "quarter-chicken",
//         "barcode": null,
//         "type": "ITEM",
//         "short_name": "Quarter Chicken",
//         "description": null,
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": "Quarter Chicken,quarterchicken,category,category,grill items,grillitems,",
//         "property": {
//           "platform": "BOTH",
//           "epos_category": "grill-items",
//           "is_coupon": "FALSE",
//           "category": "grill-items"
//         },
//         "files": []
//       },
//       "components": [
//         {
//           "id": 24224,
//           "parent_id": 24223,
//           "order_id": 10630,
//           "product_id": 2013,
//           "parent_product_id": 2003,
//           "unit": 1,
//           "net_amount": 3,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24225,
//               "parent_id": 24224,
//               "order_id": 10630,
//               "product_id": 2023,
//               "parent_product_id": 2013,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2023,
//                 "sort_order": 10,
//                 "uuid": "mirianda-strawberry",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Mirianda Strawberry",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2013,
//             "sort_order": 3,
//             "uuid": "meal-deal-rice",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Meal Deal (Rice)",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24226,
//           "parent_id": 24223,
//           "order_id": 10630,
//           "product_id": 2015,
//           "parent_product_id": 2003,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2015,
//             "sort_order": 5,
//             "uuid": "bbq",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "BBQ",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": "topping",
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24227,
//           "parent_id": 24223,
//           "order_id": 10630,
//           "product_id": 2017,
//           "parent_product_id": 2003,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2017,
//             "sort_order": 7,
//             "uuid": "chili-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Chili Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     },
//     {
//       "id": 24228,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2005,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 9.9,
//       "discountable_amount": 0,
//       "comment": "jopijojio",
//       "product": {
//         "id": 2005,
//         "sort_order": 5,
//         "uuid": "lamb-chops-4pc",
//         "barcode": null,
//         "type": "ITEM",
//         "short_name": "Lamb Chops (4pc)",
//         "description": null,
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": "Lamb Chops (4pc),lambchops4pc,category,category,grill items,grillitems,",
//         "property": {
//           "platform": "BOTH",
//           "epos_category": "grill-items",
//           "is_coupon": "FALSE",
//           "category": "grill-items",
//           "featured": "1"
//         },
//         "files": [
//           {
//             "id": 11,
//             "product_id": 2005,
//             "file_name": "WhatsApp Image 2023-08-24 at 01.03.52_1693050025.jpg",
//             "type": "general",
//             "file_path": "/var/www/html/ordering_platform_php/flava/api/public/files/products"
//           }
//         ]
//       },
//       "components": [
//         {
//           "id": 24229,
//           "parent_id": 24228,
//           "order_id": 10630,
//           "product_id": 2013,
//           "parent_product_id": 2005,
//           "unit": 1,
//           "net_amount": 3,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24230,
//               "parent_id": 24229,
//               "order_id": 10630,
//               "product_id": 2024,
//               "parent_product_id": 2013,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2024,
//                 "sort_order": 11,
//                 "uuid": "mirianda-orange",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Mirianda Orange",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2013,
//             "sort_order": 3,
//             "uuid": "meal-deal-rice",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Meal Deal (Rice)",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24231,
//           "parent_id": 24228,
//           "order_id": 10630,
//           "product_id": 2014,
//           "parent_product_id": 2005,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24232,
//               "parent_id": 24231,
//               "order_id": 10630,
//               "product_id": 2127,
//               "parent_product_id": 2014,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2127,
//                 "sort_order": 1,
//                 "uuid": "lemon",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Lemon",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2014,
//             "sort_order": 4,
//             "uuid": "peri-peri",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Peri Peri",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24233,
//           "parent_id": 24228,
//           "order_id": 10630,
//           "product_id": 2016,
//           "parent_product_id": 2005,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2016,
//             "sort_order": 6,
//             "uuid": "mayonnaise-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Mayonnaise Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24234,
//           "parent_id": 24228,
//           "order_id": 10630,
//           "product_id": 2017,
//           "parent_product_id": 2005,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2017,
//             "sort_order": 7,
//             "uuid": "chili-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Chili Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     },
//     {
//       "id": 24235,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2007,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 26.5,
//       "discountable_amount": 0,
//       "comment": "ijohibjuhbki",
//       "product": {
//         "id": 2007,
//         "sort_order": 7,
//         "uuid": "mega-mixed-grill",
//         "barcode": null,
//         "type": "ITEM",
//         "short_name": "Mega Mixed Grill",
//         "description": null,
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": "Mega Mixed Grill,megamixedgrill,category,category,grill items,grillitems,1/2 chicken, 2 lamb chops, 4 wings, 4 strips, 2 ribs & large chips",
//         "property": {
//           "short_description": "1/2 chicken, 2 lamb chops, 4 wings, 4 strips, 2 ribs & large chips",
//           "platform": "BOTH",
//           "epos_category": "grill-items",
//           "is_coupon": "FALSE",
//           "category": "grill-items"
//         },
//         "files": []
//       },
//       "components": [
//         {
//           "id": 24236,
//           "parent_id": 24235,
//           "order_id": 10630,
//           "product_id": 2013,
//           "parent_product_id": 2007,
//           "unit": 1,
//           "net_amount": 3,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24237,
//               "parent_id": 24236,
//               "order_id": 10630,
//               "product_id": 2023,
//               "parent_product_id": 2013,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2023,
//                 "sort_order": 10,
//                 "uuid": "mirianda-strawberry",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Mirianda Strawberry",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2013,
//             "sort_order": 3,
//             "uuid": "meal-deal-rice",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Meal Deal (Rice)",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24238,
//           "parent_id": 24235,
//           "order_id": 10630,
//           "product_id": 2015,
//           "parent_product_id": 2007,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2015,
//             "sort_order": 5,
//             "uuid": "bbq",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "BBQ",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": "topping",
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24239,
//           "parent_id": 24235,
//           "order_id": 10630,
//           "product_id": 2017,
//           "parent_product_id": 2007,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2017,
//             "sort_order": 7,
//             "uuid": "chili-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Chili Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24240,
//           "parent_id": 24235,
//           "order_id": 10630,
//           "product_id": 2016,
//           "parent_product_id": 2007,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2016,
//             "sort_order": 6,
//             "uuid": "mayonnaise-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Mayonnaise Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     },
//     {
//       "id": 24241,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2001,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 11.99,
//       "discountable_amount": 0,
//       "comment": null,
//       "product": {
//         "id": 2001,
//         "sort_order": 1,
//         "uuid": "whole-chicken",
//         "barcode": null,
//         "type": "ITEM",
//         "short_name": "Whole Chicken",
//         "description": null,
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": "Whole Chicken,wholechicken,category,category,grill items,grillitems,",
//         "property": {
//           "platform": "WEB",
//           "epos_category": "grill-items",
//           "is_coupon": "FALSE",
//           "category": "grill-items",
//           "featured": "0"
//         },
//         "files": [
//           {
//             "id": 1,
//             "product_id": 2001,
//             "file_name": "roast-chicken_1692795352.jpg",
//             "type": "general",
//             "file_path": "/var/www/html/ordering_platform_php/flava/api/public/files/products"
//           }
//         ]
//       },
//       "components": [
//         {
//           "id": 24242,
//           "parent_id": 24241,
//           "order_id": 10630,
//           "product_id": 2012,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 2.5,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24243,
//               "parent_id": 24242,
//               "order_id": 10630,
//               "product_id": 2023,
//               "parent_product_id": 2012,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2023,
//                 "sort_order": 10,
//                 "uuid": "mirianda-strawberry",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Mirianda Strawberry",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2012,
//             "sort_order": 2,
//             "uuid": "meal-deal-chips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Meal Deal (Chips)",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24244,
//           "parent_id": 24241,
//           "order_id": 10630,
//           "product_id": 2014,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [
//             {
//               "id": 24245,
//               "parent_id": 24244,
//               "order_id": 10630,
//               "product_id": 2127,
//               "parent_product_id": 2014,
//               "unit": 1,
//               "net_amount": 0,
//               "discountable_amount": 0,
//               "comment": null,
//               "components": [],
//               "product": {
//                 "id": 2127,
//                 "sort_order": 1,
//                 "uuid": "lemon",
//                 "barcode": null,
//                 "type": "COMPONENT",
//                 "short_name": "Lemon",
//                 "description": null,
//                 "status": 1,
//                 "discountable": 1,
//                 "creator_id": 2,
//                 "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//                 "tags": null,
//                 "property": {
//                   "platform": "BOTH",
//                   "is_coupon": "FALSE"
//                 }
//               }
//             }
//           ],
//           "product": {
//             "id": 2014,
//             "sort_order": 4,
//             "uuid": "peri-peri",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Peri Peri",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         },
//         {
//           "id": 24246,
//           "parent_id": 24241,
//           "order_id": 10630,
//           "product_id": 2017,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0.2,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2017,
//             "sort_order": 7,
//             "uuid": "chili-dips",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Chili Dips",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     },
//     {
//       "id": 24247,
//       "parent_id": null,
//       "order_id": 10630,
//       "product_id": 2019,
//       "parent_product_id": null,
//       "unit": 1,
//       "net_amount": 8.787,
//       "discountable_amount": 0,
//       "comment": "discounted",
//       "product": {
//         "id": 2019,
//         "sort_order": 2,
//         "uuid": "onallorder",
//         "barcode": null,
//         "type": "DISCOUNT",
//         "short_name": "ONALLORDER",
//         "description": "10% OFF on all order",
//         "status": 1,
//         "discountable": 1,
//         "creator_id": 2,
//         "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//         "tags": null,
//         "property": {
//           "platform": "WEB",
//           "discount_type": "PERCENTAGE",
//           "discount_value": "10",
//           "is_coupon": "FALSE"
//         },
//         "files": []
//       },
//       "components": []
//     }
//   ],
//   "requester": {
//     "id": 5,
//     "uuid": "",
//     "role":  "",
//     "name": "",
//     "email":  "",
//     "username": "",
//     "phone":  "",
//     "email_verified_at": "",
//     "provider":  "",
//     "provider_id": "",
//     "created_at":  "",
//     "updated_at": "",
//     "property":  {
//       "first_name": "",
//       "last_name": "",
//       "phone":  ""
//     }
//   },
//   "requester_guest": {
//     "id": 7486,
//     "uuid": "9a7db12d-79da-4fda-8b02-6f6a88b5d5ad",
//     "first_name": "Tanvir",
//     "last_name": "Mhamud",
//     "email": "shakilhassan887@gmail.com",
//     "phone": "01932334567",
//     "created_at": "2023-10-30T06:12:21.000000Z",
//     "updated_at": "2023-10-30T06:12:21.000000Z"
//   },
//   "shipping_address": {
//     "id": 5,
//     "name": "",
//     "type":  "",
//     "creator_type": "",
//     "creator_id":  5,
//     "status": 5,
//     "created_at":  "",
//     "updated_at": "",
//     "property":  {
//       "house": "",
//       "town": "",
//       "state":  "",
//       "postcode": "",
//       "address":  ""
//     }
//   },
//   "order_files": [],
//   "prescriber": null,
//   "payment": {
//     "id":  5,
//     "requester_type": "",
//     "requester_id":  5,
//     "reference": "",
//     "payment_date":  "",
//     "amount": 5,
//     "source":  "",
//     "card_type": "",
//     "status":  "",
//     "comment": "",
//     "created_at":  "",
//     "updated_at": ""
//   },
//   "cash_entry": [
//     {
//       "id": 5,
//       "parent_id": "",
//       "order_id":  5,
//       "product_id": 5,
//       "parent_product_id":  "",
//       "unit": 8,
//       "net_amount":  5.0,
//       "discountable_amount": 5,
//       "comment":  "",
//       "product": {
//         "id": 5,
//         "sort_order": 1,
//         "uuid":  "",
//         "barcode": "",
//         "type":  "",
//         "short_name": "",
//         "description":  "",
//         "status": 5,
//         "discountable":  5,
//         "creator_id": 5,
//         "creator_uuid":  "",
//         "tags": "",
//         "property":  {
//           "phone":  "",
//           "email": "",
//           "address":  "",
//           "postcode": ""
//         }
//       },
//       "components":  [
//         {
//           "id": 24210,
//           "parent_id": 24209,
//           "order_id": 10630,
//           "product_id": 2010,
//           "parent_product_id": 2001,
//           "unit": 1,
//           "net_amount": 0,
//           "discountable_amount": 0,
//           "comment": null,
//           "components": [],
//           "product": {
//             "id": 2010,
//             "sort_order": 1,
//             "uuid": "normal",
//             "barcode": null,
//             "type": "COMPONENT",
//             "short_name": "Normal",
//             "description": null,
//             "status": 1,
//             "discountable": 1,
//             "creator_id": 2,
//             "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
//             "tags": null,
//             "property": {
//               "platform": "BOTH",
//               "is_coupon": "FALSE"
//             }
//           }
//         }
//       ]
//     }
//   ],
//   "branch": {
//     "id": 1001,
//     "name": "Biscot Road",
//     "value": "biscot-road",
//     "created_at": null,
//     "updated_at": null,
//     "property": {
//       "phone": "01582 488900",
//       "email": "contact@theflava.co.uk",
//       "address": "157 Biscot Road, Luton, LU31AW",
//       "postcode": "LU3 1AW"
//     }
//   }
// };

var orderjson3 = {
  "id": null,
  "local_id": 6,
  "tableUniqID": 0,
  "table_id": 0,
  "order_type": "COLLECTION",
  "order_channel": "EPOS",
  "order_date": "2023-11-26 09:04:02",
  "requester_type": "PROVIDER",
  "requester_id": 0,
  "requester_uuid": 0,
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2023-11-27 09:04:02",
  "status": "READY",
  "net_amount": 8.89,
  "discounted_amount": 0,
  "delivery_charge": 0,
  "payable_amount": 8.89,
  "payment_type": "NOTPAY",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 0,
  "comment": "",
  "created_at": "2023-11-26T09:04:02.752526",
  "updated_at": "2023-11-26T09:04:02.754873",
  "order_products": [
    {
      "id": 2009,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 4.99,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2009,
        "sort_order": 9,
        "uuid": "chicken-strips",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken Strips",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Chicken Strips,chickenstrips,category,category,grill items,grillitems,",
        "property": {
          "platform": null,
          "epos_category": "grill-items",
          "is_coupon": null,
          "category": "grill-items",
          "featured": null,
          "discount_type": null,
          "discount_value": null
        },
        "files": []
      },
      "components": [
        {
          "id": 249,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 2.5,
          "discountable_amount": 0,
          "comment": "",
          "components": [
            {
              "id": 2024,
              "parent_id": null,
              "order_id": null,
              "product_id": null,
              "parent_product_id": null,
              "unit": 1,
              "net_amount": 0,
              "discountable_amount": 0,
              "comment": "",
              "components": [],
              "product": {
                "id": 2024,
                "sort_order": 11,
                "uuid": "mirianda-orange",
                "barcode": null,
                "type": "COMPONENT",
                "short_name": "Mirianda Orange",
                "description": null,
                "status": 1,
                "discountable": 1,
                "creator_id": 2,
                "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
                "tags": null,
                "property": null
              }
            }
          ],
          "product": {
            "id": 2012,
            "sort_order": 2,
            "uuid": "meal-deal-chips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Meal Deal (Chips)",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        },
        {
          "id": 254,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [
            {
              "id": 2086,
              "parent_id": null,
              "order_id": null,
              "product_id": null,
              "parent_product_id": null,
              "unit": 1,
              "net_amount": 0,
              "discountable_amount": 0,
              "comment": "",
              "components": [],
              "product": {
                "id": 2086,
                "sort_order": 1,
                "uuid": "hot",
                "barcode": null,
                "type": "COMPONENT",
                "short_name": "Hot",
                "description": null,
                "status": 1,
                "discountable": 1,
                "creator_id": 2,
                "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
                "tags": null,
                "property": null
              }
            }
          ],
          "product": {
            "id": 2014,
            "sort_order": 4,
            "uuid": "peri-peri",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Peri Peri",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        },
        {
          "id": 248,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.2,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2016,
            "sort_order": 6,
            "uuid": "mayonnaise-dips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Mayonnaise Dips",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        },
        {
          "id": 247,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.2,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2017,
            "sort_order": 7,
            "uuid": "chili-dips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Chili Dips",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        },
        {
          "id": 313,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2166,
            "sort_order": 9,
            "uuid": "9a71c706-9070-422f-859d-b33796be7c61",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Cheese",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "topping",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "topping"
            }
          }
        },
        {
          "id": 314,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2167,
            "sort_order": 10,
            "uuid": "9a71c71b-4270-496a-846e-8816307fa032",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Fried eggs",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "topping",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "topping"
            }
          }
        },
        {
          "id": 323,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.5,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2171,
            "sort_order": 2,
            "uuid": "9a71c997-d510-4848-80ec-4410477ac0bd",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Ralish",
            "description": "topping",
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "topping",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "topping"
            }
          }
        },
        {
          "id": 322,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2161,
            "sort_order": 4,
            "uuid": "9a71c6e0-5f16-4679-8831-fbc951779f7b",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Peri Mayonnaise",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "topping",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "topping"
            }
          }
        },
        {
          "id": 327,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.5,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2175,
            "sort_order": 1,
            "uuid": "add-on-1",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Add On 1",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "addon",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "addon"
            }
          }
        },
        {
          "id": 328,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2176,
            "sort_order": 2,
            "uuid": "add-on-2",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Add On 2",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "addon",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "addon"
            }
          }
        },
        {
          "id": 325,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2172,
            "sort_order": 1,
            "uuid": "dressing-1",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Dressing 1",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "dressing",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "dressing"
            }
          }
        },
        {
          "id": 326,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2173,
            "sort_order": 1,
            "uuid": "dressing-2",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Dressing 2",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "dressing",
            "property": {
              "platform": null,
              "is_coupon": null,
              "item_type": "dressing"
            }
          }
        }
      ]
    }
  ],
  "requester": {
    "id": 8,
    "uuid": null,
    "role": null,
    "name": "rrrrr yyyyyy",
    "email": "tanvir@gmail.com",
    "username": "rrrrr yyyyyy",
    "phone": "0196565855",
    "provider": null,
    "provider_id": null
  },
  "requester_guest": null,
  "shipping_address": {
    "id": null,
    "name": "",
    "type": null,
    "creator_type": "",
    "creator_id": 2,
    "status": null,
    "created_at": "2023-11-26T09:04:02.749406",
    "updated_at": null,
    "property": {
      "house": "52",
      "town": "",
      "state": "Grove Road",
      "postcode": "LU1 1BL",
      "address": "52 Grove Road LU1 1BL"
    }
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": null,
    "name": "biscot-road",
    "value": null,
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null
};

var bookingRequest = {
  "id": 5,
  "status": "rrrr",
  "name": "rrtrtr",
  "phone": "0159525212",
  "email": "shakilhassan887",
  "number_of_guest": 5,
  "arrival_time": "2023-11-22 08:54:30",
  "note": "dfbdfb",
  "branch_id": 5,
  "created_at": "2023-11-22T09:08:29.000000Z",
  "updated_at": "2023-11-22T09:08:29.000000Z"
};

var dailyReport = {
  "data": {
    "total_order": 2,
    "total_online_order": 2,
    "total_local_order": 0,
    "total_card_order": 0,
    "total_cash_order": 2,
    "total_card_order_amount": 165.80,
    "total_cash_order_amount": 39.58,
    "total_refund": 0,
    "total_cash_in": 0,
    "total_cash_out": 0,
    "date": "2023-11-27 09:32:19"
  }
};

var orderjson4 = {
  "id": null,
  "local_id": null,
  "tableUniqID": 0,
  "table_id": 0,
  "order_type": "COLLECTION",
  "order_channel": "EPOS",
  "order_date": "2023-11-28 01:55:56",
  "requester_type": "PROVIDER",
  "requester_id": 0,
  "requester_uuid": 0,
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2023-11-28 12:55:56",
  "status": "READY",
  "net_amount": 103.7,
  "discounted_amount": 0,
  "delivery_charge": 0,
  "payable_amount": 103.7,
  "payment_type": "NOTPAY",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 0,
  "comment": "",
  "created_at": "2023-11-28T12:55:56.791337",
  "updated_at": "2023-11-28T12:55:56.794947",
  "order_products": [
    {
      "id": 2474,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 4.25,
      "discountable_amount": null,
      "comment": "zxzxzxvzv",
      "product": {
        "id": 2474,
        "sort_order": 1,
        "uuid": "chicken-or-lamb-tikka-starter",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken or Lamb Tikka starter",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Chicken or Lamb Tikka starter,chickenorlambtikkastarter,category,category,starters,starters,",
        "property": {
          "platform": null,
          "epos_category": "starters",
          "is_coupon": null,
          "category": "starters",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "2"
        },
        "files": []
      },
      "components": [
        {
          "id": 177,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2476,
            "sort_order": 4,
            "uuid": "lamb",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Lamb",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        }
      ]
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
    "property": null
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": null,
    "name": "welwyn-garden-city",
    "value": null,
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null
};

var orderjson5 = {
  "id": null,
  "local_id": 5,
  "tableUniqID": 1701606652836315,
  "table_id": 0,
  "order_type": "COLLECTION",
  "order_channel": "EPOS_WAITER",
  "order_date": "2023-12-03 12:31:47",
  "requester_type": "PROVIDER",
  "requester_id": 0,
  "requester_uuid": "0",
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2023-12-03 13:01:47",
  "status": "READY",
  "net_amount": 14.799999999999999,
  "discounted_amount": 0,
  "delivery_charge": 0,
  "payable_amount": 14.799999999999999,
  "payment_type": "CARD",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 0,
  "comment": "",
  "created_at": "2023-12-03T12:31:47.346",
  "updated_at": "2023-12-03T12:31:50.555",
  "order_products": [
    {
      "id": 2005,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 9.9,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2005,
        "sort_order": 5,
        "uuid": "lamb-chops-4pc",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Lamb Chops (4pc)",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Lamb Chops (4pc),lambchops4pc,category,category,grill items,grillitems,",
        "property": {
          "platform": null,
          "epos_category": "grill-items",
          "is_coupon": null,
          "category": "grill-items",
          "featured": "1",
          "discount_type": null,
          "discount_value": null,
          "print_order": "10"
        },
        "files": []
      },
      "components": [
        {
          "id": 267,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        },
        {
          "id": 269,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2011,
            "sort_order": 1,
            "uuid": "none",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "None",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        },
        {
          "id": 264,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.2,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2016,
            "sort_order": 6,
            "uuid": "mayonnaise-dips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Mayonnaise Dips",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        }
      ]
    },
    {
      "id": 2004,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 4.5,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2004,
        "sort_order": 4,
        "uuid": "wings-6pc",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Wings (6pc)",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Wings (6pc),wings6pc,category,category,grill items,grillitems,",
        "property": {
          "platform": null,
          "epos_category": "grill-items",
          "is_coupon": null,
          "category": "grill-items",
          "featured": "1",
          "discount_type": null,
          "discount_value": null,
          "print_order": "10"
        },
        "files": []
      },
      "components": [
        {
          "id": 305,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": null,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        },
        {
          "id": 307,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": null,
            "sort_order": 1,
            "uuid": "none",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "None",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        },
        {
          "id": 310,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.2,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": null,
            "sort_order": 6,
            "uuid": "mayonnaise-dips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Mayonnaise Dips",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        }
      ]
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
    "property": null
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [
    {
      "id": null,
      "timestamp": "2023-12-03T12:31:37.263",
      "type": "CARD_ORDER",
      "order_id": null,
      "amount": 4.7,
      "comment": null,
      "created_at": null,
      "updated_at": null
    },
    {
      "id": null,
      "timestamp": "2023-12-03T12:31:37.263",
      "type": "CARD_ORDER",
      "order_id": null,
      "amount": 4.7,
      "comment": null,
      "created_at": null,
      "updated_at": null
    }
  ],
  "branch": {
    "id": null,
    "name": "biscot-road",
    "value": null,
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null
};

var orderjson6 = {
  "id": 10647,
  "local_id": null,
  "tableUniqID": null,
  "table_id": null,
  "order_type": "DELIVERY",
  "order_channel": "ONLINE",
  "order_date": "2023-12-03 15:48:50",
  "requester_type": "CONSUMER",
  "requester_id": 13,
  "requester_uuid": "9ac2e018-3fb6-4684-bb9c-65ad2e9d64b1",
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2023-12-03 16:28:50",
  "status": "NEW",
  "net_amount": 14.8,
  "discounted_amount": 1.48,
  "delivery_charge": 1,
  "payable_amount": 14.32,
  "payment_type": "CARD",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 1002,
  "comment": null,
  "created_at": "2023-12-03T15:48:50.000",
  "updated_at": "2023-12-03T15:52:08.000",
  "order_products": [
    {
      "id": 24042,
      "parent_id": null,
      "order_id": 10647,
      "product_id": 2026,
      "parent_product_id": null,
      "unit": 4,
      "net_amount": 8.8,
      "discountable_amount": 0,
      "comment": null,
      "product": {
        "id": 2026,
        "sort_order": 1,
        "uuid": "cheese-burger",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Cheese Burger",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Cheese Burger,cheeseburger,category,category,american burgers,americanburgers,",
        "property": {
          "platform": "BOTH",
          "epos_category": "american-burgers",
          "is_coupon": "FALSE",
          "category": "american-burgers",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": null
        },
        "files": []
      },
      "components": [
        {
          "id": 24043,
          "parent_id": 24042,
          "order_id": 10647,
          "product_id": 2010,
          "parent_product_id": 2026,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        }
      ]
    },
    {
      "id": 24044,
      "parent_id": null,
      "order_id": 10647,
      "product_id": 2038,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 6,
      "discountable_amount": 0,
      "comment": null,
      "product": {
        "id": 2038,
        "sort_order": 1,
        "uuid": "classic-cheese-burger",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Classic Cheese Burger",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Classic Cheese Burger,classiccheeseburger,category,category,gourmet burgers,gourmetburgers,6oz patty, lettuce, onion, gherkins, relish and cheese.",
        "property": {
          "platform": "BOTH",
          "epos_category": "gourmet-burgers",
          "is_coupon": "FALSE",
          "category": "gourmet-burgers",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": null
        },
        "files": []
      },
      "components": [
        {
          "id": 24045,
          "parent_id": 24044,
          "order_id": 10647,
          "product_id": 2010,
          "parent_product_id": 2038,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": null
          }
        }
      ]
    },
    {
      "id": 24046,
      "parent_id": null,
      "order_id": 10647,
      "product_id": 2019,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 1.48,
      "discountable_amount": 0,
      "comment": "discounted",
      "product": {
        "id": 2019,
        "sort_order": 2,
        "uuid": "onallorder",
        "barcode": null,
        "type": "DISCOUNT",
        "short_name": "ONALLORDER",
        "description": "10% OFF on all order",
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags": null,
        "property": {
          "platform": "WEB",
          "epos_category": null,
          "is_coupon": "FALSE",
          "category": null,
          "featured": null,
          "discount_type": "PERCENTAGE",
          "discount_value": "10",
          "print_order": null
        },
        "files": []
      },
      "components": []
    }
  ],
  "requester": {
    "id": 13,
    "uuid": "9ac2e018-3fb6-4684-bb9c-65ad2e9d64b1",
    "role": "CONSUMER",
    "name": "Christina Cole",
    "email": "christina.cole1@outlook.com",
    "username": "christinac481",
    "phone": "07306801566",
    "provider": null,
    "provider_id": null
  },
  "requester_guest": null,
  "shipping_address": {
    "id": 4059,
    "name": "Shipping Address",
    "type": "SHIPPING",
    "creator_type": "CONSUMER",
    "creator_id": 13,
    "status": 1,
    "created_at": "2023-12-03T15:39:41.000",
    "updated_at": "2023-12-03T15:39:41.000",
    "property": {
      "house": "66",
      "town": "Luton",
      "state": "Spinney Road",
      "postcode": "Lu3 3df",
      "address": "",
      "distance": null
    }
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": 1002,
    "name": "Sundon Park",
    "value": "sundon-park",
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null
};

///////////////////////////////////
///
var orderjson7 = {
  "id": null,
  "local_id": 4,
  "tableUniqID": 1701933780376942,
  "table_id": 0,
  "order_type": "COLLECTION",
  "order_channel": "EPOS",
  "order_date": "2023-12-07 07:35:37",
  "requester_type": "PROVIDER",
  "requester_id": 0,
  "requester_uuid": 0,
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2023-12-07 08:05:37",
  "status": "READY",
  "net_amount": 3.995,
  "discounted_amount": 0,
  "delivery_charge": 0,
  "payable_amount": 3.995,
  "payment_type": "NOTPAY",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 0,
  "comment": "",
  "created_at": "2023-12-07T07:35:37.228",
  "updated_at": "2023-12-07T07:35:37.229",
  "order_products": [
    {
      "id": 2003,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 4.5,
      "discountable_amount": 15,
      "comment": null,
      "product": {
        "id": 2003,
        "sort_order": 3,
        "uuid": "quarter-chicken",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Quarter Chicken",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Quarter Chicken,quarterchicken,category,category,grill items,grillitems,",
        "property": {
          "platform": null,
          "epos_category": "grill-items",
          "is_coupon": null,
          "category": "grill-items",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "10"
        },
        "files": []
      },
      "components": [
        {
          "id": 298,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2011,
            "sort_order": 1,
            "uuid": "none",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "None",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        },
        {
          "id": 302,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        },
        {
          "id": 296,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 0.2,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2016,
            "sort_order": 6,
            "uuid": "mayonnaise-dips",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Mayonnaise Dips",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          }
        }
      ]
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
    "property": null
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": null,
    "name": "biscot-road",
    "value": null,
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null
};

var neworderjson = {
  "id": 10972,
  "local_id": null,
  "table_id": null,
  "order_type": "COLLECTION",
  "order_channel": "ONLINE",
  "order_date": "2023-12-29 09:11:19",
  "requester_type": "GUEST",
  "requester_id": 7814,
  "requester_uuid": "9ad87777-5817-4959-9519-90d6a57066db",
  "shipping_address_id": null,
  "requested_delivery_timestamp": "2023-12-30 15:30:00",
  "status": "NEW",
  "net_amount": 11.99,
  "discounted_amount": 1.199,
  "delivery_charge": 0,
  "payable_amount": 10.791,
  "payment_type": "CASH",
  "payment_id": null,
  "prescriber_id": null,
  "branch_id": 1001,
  "comment": null,
  "created_at": "2023-12-14T09:11:19.000000Z",
  "updated_at": "2023-12-14T09:11:19.000000Z",
  "property": {"comment": null, "requested_delivery_timestamp_type": null},
  "order_products": [
    {
      "id": 26382,
      "parent_id": null,
      "order_id": 10972,
      "product_id": 2001,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 11.99,
      "discountable_amount": 0,
      "comment": null,
      "product": {
        "id": 2001,
        "platform": "BOTH",
        "sort_order": 1,
        "uuid": "whole-chicken",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Whole Chicken",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Whole Chicken,wholechicken,category,category,grill items,grillitems,",
        "property": {
          "platform": "WEB",
          "epos_category": "grill-items",
          "is_coupon": "FALSE",
          "category": "grill-items",
          "featured": "0",
          "print_order": "10"
        },
        "files": [
          {
            "id": 1,
            "product_id": 2001,
            "file_name": "roast-chicken_1692795352.jpg",
            "type": "general",
            "file_path":
                "/var/www/html/ordering_platform_php/flava/api/public/files/products"
          }
        ]
      },
      "components": [
        {
          "id": 26383,
          "parent_id": 26382,
          "order_id": 10972,
          "product_id": 2010,
          "parent_product_id": 2001,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2010,
            "platform": "BOTH",
            "sort_order": 1,
            "uuid": "normal",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Normal",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": "BOTH", "is_coupon": "FALSE"}
          }
        },
        {
          "id": 26384,
          "parent_id": 26382,
          "order_id": 10972,
          "product_id": 2183,
          "parent_product_id": 2001,
          "unit": 1,
          "net_amount": 0,
          "discountable_amount": 0,
          "comment": null,
          "components": [],
          "product": {
            "id": 2183,
            "platform": "BOTH",
            "sort_order": 14,
            "uuid": "spicy",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Spicy",
            "description": "Spicy",
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {
              "print_order": "10",
              "platform": "BOTH",
              "is_coupon": "FALSE"
            }
          }
        }
      ]
    },
    {
      "id": 26385,
      "parent_id": null,
      "order_id": 10972,
      "product_id": 2019,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 1.199,
      "discountable_amount": 0,
      "comment": "discounted",
      "product": {
        "id": 2019,
        "platform": "BOTH",
        "sort_order": 2,
        "uuid": "onallorder",
        "barcode": null,
        "type": "DISCOUNT",
        "short_name": "ONALLORDER",
        "description": "10% OFF on all order",
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags": null,
        "property": {
          "platform": "WEB",
          "discount_type": "PERCENTAGE",
          "discount_value": "10",
          "is_coupon": "FALSE"
        },
        "files": []
      },
      "components": []
    }
  ],
  "requester": null,
  "requester_guest": {
    "id": 7814,
    "uuid": "9ad87777-5817-4959-9519-90d6a57066db",
    "first_name": "Coby",
    "last_name": "Dominguez",
    "email": "muntasir.yuma@gmail.com",
    "phone": "03616952318",
    "created_at": "2023-12-14T09:11:19.000000Z",
    "updated_at": "2023-12-14T09:11:19.000000Z"
  },
  "shipping_address": null,
  "order_files": [],
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": 1001,
    "name": "Biscot Road",
    "value": "biscot-road",
    "created_at": null,
    "updated_at": null,
    "property": {
      "phone": "01582 488900",
      "email": "contact@theflava.co.uk",
      "address": "157 Biscot Road, Luton, LU31AW",
      "postcode": "LU3 1AW"
    }
  },
  "table": null
};

var oderjson9 = {
  "id": null,
  "errorcode": 0,
  "local_id": 2,
  "tableUniqID": 1706361905149523,
  "table_id": 0,
  "order_type": "COLLECTION",
  "order_channel": "EPOS",
  "order_date": "2024-01-27 13:29:04",
  "requester_type": "PROVIDER",
  "requester_id": 0,
  "requester_uuid": 0,
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2024-01-27 13:49:04",
  "status": "READY",
  "net_amount": 47.25,
  "discounted_amount": 0,
  "delivery_charge": 0,
  "payable_amount": 47.25,
  "payment_type": "NOTPAY",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 0,
  "comment": "",
  "created_at": "2024-01-27T13:29:04.998827",
  "updated_at": "2024-01-27T13:29:04.998944",
  "order_products": [
    {
      "id": 2915,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 12.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2915,
        "sort_order": 2,
        "uuid": "chicken-tikka-balti-IcfFB",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken Tikka Balti",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags":
            "Chicken Tikka Balti,chickentikkabalti,category,category,balti dishes,baltidishes,",
        "property": {
          "platform": null,
          "epos_category": "balti-dishes-cNR",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "3"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2914,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 12.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2914,
        "sort_order": 1,
        "uuid": "lamb-tikka-balti-vSLOB",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Lamb Tikka Balti",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags":
            "Lamb Tikka Balti,lambtikkabalti,category,category,balti dishes,baltidishes,",
        "property": {
          "platform": null,
          "epos_category": "balti-dishes-cNR",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "3"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2892,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 5.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2892,
        "sort_order": 2,
        "uuid": "chicken-chat-U5ZLH",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken Chat",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags":
            "Chicken Chat,chickenchat,category,category,non vegetarian starter,nonvegetarianstarter,pieces of chicken with chat massala",
        "property": {
          "platform": null,
          "epos_category": "non-vegetarian-starter-GuR",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "2"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2891,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 7.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2891,
        "sort_order": 1,
        "uuid": "chicken-momo-dDVIz",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chicken Momo",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags":
            "Chicken Momo,chickenmomo,category,category,non vegetarian starter,nonvegetarianstarter,Cooked in chefs own recipe",
        "property": {
          "platform": null,
          "epos_category": "non-vegetarian-starter-GuR",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "2"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2994,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 3.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2994,
        "sort_order": 2,
        "uuid": "pilau-rice-Bi0mh",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Pilau Rice",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags":
            "Pilau Rice,pilaurice,category,category,sundries,sundries,saffron",
        "property": {
          "platform": null,
          "epos_category": "sundries-8jl",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "5"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2993,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 3.5,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2993,
        "sort_order": 1,
        "uuid": "boiled-rice-Lp5Mc",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Boiled Rice",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags": "Boiled Rice,boiledrice,category,category,sundries,sundries,",
        "property": {
          "platform": null,
          "epos_category": "sundries-8jl",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "5"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
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
    "property": null
  },
  "waiterInfo": {
    "firstName": "Admin",
    "id": 0,
    "role": "ADMIN",
    "lastName": "",
    "phone": "admin",
    "password": "0000",
    "orderList": [],
    "loginTime": "2024-01-27 13:22:25",
    "logoutTime": "2024-01-27 13:22:25"
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": null,
    "name": "luton",
    "value": null,
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null,
  "property": {
    "comment": null,
    "requested_delivery_timestamp_type": null,
    "total_value_edit": null,
    "total_value_edit_note": null
  },
  "vat": 0,
  "totalvalueEdit": false,
  "totalValueEditcomment": "manager has edited price",
  "isDiscountGivenByAmount": false,
  "isDiscountGivenByUser": false
};

var orderjson10 = {
  "id": null,
  "errorcode": 0,
  "local_id": 3,
  "tableUniqID": 1706417593942754,
  "table_id": 0,
  "order_type": "COLLECTION",
  "order_channel": "EPOS",
  "order_date": "2024-01-28 04:53:31",
  "requester_type": "PROVIDER",
  "requester_id": 0,
  "requester_uuid": 0,
  "shipping_address_id": 0,
  "requested_delivery_timestamp": "2024-01-28 05:13:31",
  "status": "READY",
  "net_amount": 25.9,
  "discounted_amount": 5.18,
  "delivery_charge": 0,
  "payable_amount": 20.72,
  "payment_type": "NOTPAY",
  "payment_id": 0,
  "prescriber_id": 0,
  "branch_id": 0,
  "comment": "",
  "created_at": "2024-01-28T04:53:31.187501",
  "updated_at": "2024-01-28T04:53:31.187566",
  "order_products": [
    {
      "id": 3013,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 2.5,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 3013,
        "sort_order": 22,
        "uuid": "chapatti-04D57",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Chapatti",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags": "Chapatti,chapatti,category,category,sundries,sundries,",
        "property": {
          "platform": null,
          "epos_category": "sundries-8jl",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "5"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2993,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 3.5,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2993,
        "sort_order": 1,
        "uuid": "boiled-rice-Lp5Mc",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Boiled Rice",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags": "Boiled Rice,boiledrice,category,category,sundries,sundries,",
        "property": {
          "platform": null,
          "epos_category": "sundries-8jl",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "5"
        },
        "files": []
      },
      "components": [],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2971,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 6.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2971,
        "sort_order": 23,
        "uuid": "baltistan-korma-VwgnL",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Baltistan Korma",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549b",
        "tags":
            "Baltistan Korma,baltistankorma,category,category,classic dishes,classicdishes,",
        "property": {
          "platform": null,
          "epos_category": "classic-dishes-9pg",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": null,
          "discount_value": null,
          "print_order": "3"
        },
        "files": []
      },
      "components": [
        {
          "id": 853,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 3,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2055,
            "sort_order": 1,
            "uuid": "chicken",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Chicken",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          },
          "relation_type": "ONE_OF",
          "relation_group": "Options"
        }
      ],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
    },
    {
      "id": 2967,
      "parent_id": null,
      "order_id": null,
      "product_id": null,
      "parent_product_id": null,
      "unit": 1,
      "net_amount": 6.95,
      "discountable_amount": null,
      "comment": null,
      "product": {
        "id": 2967,
        "sort_order": 20,
        "uuid": "achari-zWt1z",
        "barcode": null,
        "type": "ITEM",
        "short_name": "Achari",
        "description": null,
        "status": 1,
        "discountable": 1,
        "creator_id": 2,
        "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
        "tags":
            "Achari,achari,category,category,classic dishes,classicdishes,Onions, ginger, garlic and pickle",
        "property": {
          "platform": null,
          "epos_category": "classic-dishes-9pg",
          "is_coupon": null,
          "category": "null",
          "featured": null,
          "discount_type": "Onions, ginger, garlic and pickle",
          "discount_value": "Onions, ginger, garlic and pickle",
          "print_order": "3"
        },
        "files": []
      },
      "components": [
        {
          "id": 817,
          "parent_id": null,
          "order_id": null,
          "product_id": null,
          "parent_product_id": null,
          "unit": 1,
          "net_amount": 3,
          "discountable_amount": 0,
          "comment": "",
          "components": [],
          "product": {
            "id": 2055,
            "sort_order": 1,
            "uuid": "chicken",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Chicken",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": null,
            "property": {"platform": null, "is_coupon": null, "item_type": null}
          },
          "relation_type": "ONE_OF",
          "relation_group": "Options"
        }
      ],
      "priceEditManage": false,
      "priceEditComment": "",
      "property": null
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
    "property": null
  },
  "waiterInfo": {
    "firstName": "Admin",
    "id": 0,
    "role": "ADMIN",
    "lastName": "",
    "phone": "admin",
    "password": "0000",
    "orderList": [],
    "loginTime": "2024-01-28 04:25:30",
    "logoutTime": "2024-01-28 04:25:30"
  },
  "order_files": [],
  "prescriber": null,
  "payment": null,
  "cash_entry": [],
  "branch": {
    "id": null,
    "name": "luton",
    "value": null,
    "created_at": null,
    "updated_at": null,
    "property": null
  },
  "table": null,
  "property": {
    "comment": null,
    "requested_delivery_timestamp_type": null,
    "total_value_edit": null,
    "total_value_edit_note": null
  },
  "vat": 0,
  "totalvalueEdit": false,
  "totalValueEditcomment": "manager has edited price",
  "isDiscountGivenByAmount": false,
  "isDiscountGivenByUser": false
};
