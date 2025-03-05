package com.ali_sajjadi.frenchpastry.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_i18n")
    val dateI18n: String,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("rate")
    val rate: Int,
    @SerializedName("replies")
    val replies: Any
)