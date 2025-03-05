package com.ali_sajjadi.frenchpastry.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("count")
    val count: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String
)