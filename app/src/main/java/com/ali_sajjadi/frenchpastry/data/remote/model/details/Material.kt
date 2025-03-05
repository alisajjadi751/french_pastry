package com.ali_sajjadi.frenchpastry.data.remote.model.details


import com.google.gson.annotations.SerializedName

data class Material(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("material")
    val material: String
)