package com.ali_sajjadi.frenchpastry.data.remote.model.home


import com.google.gson.annotations.SerializedName

data class PastryX(
    @SerializedName("active_stock")
    val activeStock: Boolean = false, // موجود یا ناموجود
    @SerializedName("date")
    val date: String = "",
    @SerializedName("date_l10n")
    val dateL10n: String = "",
    @SerializedName("discount")
    val discount: String = "",//تخفیف
    @SerializedName("discount_percent")
    val discountPercent: Int = 0,// درصد تخفیف
    @SerializedName("discount_percent_l10n")
    val discountPercentL10n: String = "",// درصد تخفیف
    @SerializedName("has_discount")
    val hasDiscount: Boolean = false,// تخفیف دارد یا ندارد
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("min_order")
    val minOrder: Int = 0,// حداقل سفارش
    @SerializedName("price")
    val price: String = "",// قیمت اصلی بدون تخفیف
    @SerializedName("sale_price")
    val salePrice: Int = 0, // قیمت با تخفیف
    @SerializedName("status")
    val status: String = "", // وضیعیت انتشار
    @SerializedName("stock")
    val stock: Int = 0,// مقدار موجودی
    @SerializedName("thumbnail")
    val thumbnail: String = "",//تصویر
    @SerializedName("title")
    val title: String = ""
)