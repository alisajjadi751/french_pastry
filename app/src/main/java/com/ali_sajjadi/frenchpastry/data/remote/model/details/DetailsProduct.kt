package com.ali_sajjadi.frenchpastry.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class DetailsProduct(
    @SerializedName("http_code")
    val httpCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("pastry")
    val pastry: Pastry,
    @SerializedName("success")
    val success: Int
)