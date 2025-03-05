package com.ali_sajjadi.frenchpastry.data.remote.model.login


import com.google.gson.annotations.SerializedName

data class SendPhoneResponse(
    @SerializedName("expire_at")
    val expireAt: String,
    @SerializedName("http_code")
    val httpCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("seconds")
    val seconds: Int,
    @SerializedName("success")
    val success: Int
)