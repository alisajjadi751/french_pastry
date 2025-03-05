package com.ali_sajjadi.frenchpastry.data.remote.model.home


import com.google.gson.annotations.SerializedName

data class Pastry(
    @SerializedName("ID")
    val iD: String,
    @SerializedName("pastries")
    val pastries: List<PastryX>,
    @SerializedName("title")
    val title: String
)