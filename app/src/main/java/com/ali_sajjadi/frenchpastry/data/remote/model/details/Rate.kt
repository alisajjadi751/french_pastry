package com.ali_sajjadi.frenchpastry.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Int
)