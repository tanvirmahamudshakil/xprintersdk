package com.example.xprintersdk.Model.ProductModel


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ProductPrint(
    @SerializedName("barcode")
    val barcode: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("expire")
    val expire: String?
)