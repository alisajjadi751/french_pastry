package com.ali_sajjadi.frenchpastry.data.remote.model.home


import com.google.gson.annotations.SerializedName

data class Banner(
    @SerializedName("ID")
    val iD: String,
    @SerializedName("large")
    val large: String
)