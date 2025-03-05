package com.ali_sajjadi.frenchpastry.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Related(
    @SerializedName("min_order")
    val minOrder: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("sale_price")
    val salePrice: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
)