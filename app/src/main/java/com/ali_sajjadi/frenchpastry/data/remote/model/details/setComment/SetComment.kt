package com.ali_sajjadi.frenchpastry.data.remote.model.details.setComment


import com.google.gson.annotations.SerializedName

data class SetComment(
    @SerializedName("http_code")
    val httpCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Int
)