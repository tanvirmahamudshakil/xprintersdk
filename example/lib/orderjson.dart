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
  "local_id": null,
  "table_id": null,
  "order_type": "COLLECTION",
  "order_channel": "EPOS",
  "order_date": "2023-11-12 09:52:59",
  "requester_type": "PROVIDER",
  "requester_id": null,
  "requester_uuid": null,
  "shipping_address_id": null,
  "requested_delivery_timestamp": "2023-11-12 09:52:59",
  "status": "READY",
  "net_amount": 8.89,
  "discounted_amount": 0,
  "delivery_charge": 0,
  "payable_amount": 8.89,
  "payment_type": "NOTPAY",
  "payment_id": null,
  "prescriber_id": null,
  "branch_id": null,
  "comment": "",
  "created_at": "2023-11-21T04:12:09.787",
  "updated_at": "2023-11-21T04:12:09.790",
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
      "comment": "jjjjjhhhjh",
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
        "tags": "Chicken Strips,chickenstrips,category,category,grill items,grillitems,",
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
          "comment": null,
          "components": [
            {
              "id": 2023,
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
                "id": 2023,
                "sort_order": 10,
                "uuid": "mirianda-strawberry",
                "barcode": null,
                "type": "COMPONENT",
                "short_name": "Mirianda Strawberry",
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
            "property": null
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
          "comment": null,
          "components": [
            {
              "id": 2127,
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
                "id": 2127,
                "sort_order": 1,
                "uuid": "lemon",
                "barcode": null,
                "type": "COMPONENT",
                "short_name": "Lemon",
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
            "property": null
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
          "comment": null,
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
            "property": null
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
          "comment": null,
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
            "property": null
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
          "comment": null,
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
            "property": null
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
          "comment": null,
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
            "property": null
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
          "comment": null,
          "components": [],
          "product": {
            "id": 2171,
            "sort_order": 2,
            "uuid": "9a71c997-d510-4848-80ec-4410477ac0bd",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Ralish",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "topping",
            "property": null
          }
        },
        {
          "id": 324,
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
            "id": 2169,
            "sort_order": 12,
            "uuid": "9a71c72b-b027-44bf-9c06-c94361523387",
            "barcode": null,
            "type": "COMPONENT",
            "short_name": "Turkey rashers",
            "description": null,
            "status": 1,
            "discountable": 1,
            "creator_id": 2,
            "creator_uuid": "98a8d4fb-5b65-4f62-b121-822924b9549c",
            "tags": "topping",
            "property": null
          }
        }
      ]
    }
  ],
  "requester": null,
  "requester_guest": null,
  "shipping_address": null,
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