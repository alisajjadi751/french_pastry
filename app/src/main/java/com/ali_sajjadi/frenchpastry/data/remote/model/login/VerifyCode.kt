package com.ali_sajjadi.frenchpastry.data.remote.model.login


import com.google.gson.annotations.SerializedName

data class VerifyCode(
    @SerializedName("api")
    val api: String = "",
    @SerializedName("http_code")
    val httpCode: Int = 0,
    @SerializedName("message")
    val message: String = "",
    @SerializedName("success")
    val success: Int = 0,
    @SerializedName("user")
    val user: User = User()
)