package com.ali_sajjadi.frenchpastry.data.remote.model.home


import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("banners")
    val banners: List<Banner>,
    @SerializedName("http_code")
    val httpCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("pastries")
    val pastries: List<Pastry>,
    @SerializedName("sliders")
    val sliders: List<String>,
    @SerializedName("success")
    val success: Int
)