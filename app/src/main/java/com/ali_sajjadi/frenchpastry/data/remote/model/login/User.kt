package com.ali_sajjadi.frenchpastry.data.remote.model.login


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("birthdate")
    val birthdate: String = "",
    @SerializedName("day")
    val day: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("first_name")
    val firstName: String = "",
    @SerializedName("fullname")
    val fullname: String = "",
    @SerializedName("last_name")
    val lastName: String = "",
    @SerializedName("month")
    val month: String = "",
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("sex")
    val sex: Int = 0,
    @SerializedName("username")
    val username: String = "",
    @SerializedName("year")
    val year: String = ""
)