package com.ali_sajjadi.frenchpastry.data.remote.model.details

import com.google.gson.annotations.SerializedName

data class Pastry(
    @SerializedName("active_special_discount")
    val activeSpecialDiscount: Boolean = false,//تخفیف ویژه
    @SerializedName("active_stock")
    val activeStock: Boolean = false,//وضعیت موجودی
    @SerializedName("bookmark")
    val bookmark: Boolean = false,//علاقمندی
    @SerializedName("categories")
    val categories: List<Any>,//دسته بندی محصول
    @SerializedName("comment_count")
    val commentCount: Int = 0,//تعداد کامنت ها
    @SerializedName("comments")
    val comments: List<Comment>,//لیستی از کامنت ها
    @SerializedName("content")
    val content: String = "",//محتوای توضیحی محصول
    @SerializedName("date")
    val date: String = "",//تاریخ انتشار محصول
    @SerializedName("date_i18n")
    val dateI18n: String = "",// تاریخ انتشار با فرمت خاص
    @SerializedName("discount_percent")
    val discountPercent: Int = 0,//درصد تخفیف
    @SerializedName("discount_percent_110n")
    val discountPercent110n: String = "",//درصد تخفیف
    @SerializedName("excerpt")
    val excerpt: String = "",// خلاصه ای از توضیحات
    @SerializedName("gallery")
    val gallery: List<Any>,//لیستی از تصاویر گالری
    @SerializedName("has_discount")
    val hasDiscount: Boolean = false,//وضعیت تخفیف
    @SerializedName("ID")
    val iD: Int = 0,
    @SerializedName("materials")
    val materials: List<Material>,//لیستی از مواد اولیه
    @SerializedName("max_order")
    val maxOrder: Int = 0,
    @SerializedName("min_order")
    val minOrder: Int = 0,
    @SerializedName("order_step")
    val orderStep: Int = 0,
    @SerializedName("price")
    val price: String = "",//قیمت اصلی
    @SerializedName("rate")
    val rate: Rate,//
    @SerializedName("related")
    val related: List<Related>,
    @SerializedName("sale_price")
    val salePrice: Int = 0,// قیمت با تخفیف
    @SerializedName("special_discount")
    val specialDiscount: Int = 0,//درصد تخفیف ویژه
    @SerializedName("special_discount_date")
    val specialDiscountDate: Any,
    @SerializedName("status")
    val status: String = "",
    @SerializedName("stock")
    val stock: Any,
    @SerializedName("thumbnail")
    val thumbnail: String = "",//تصویر اصلی
    @SerializedName("title")
    val title: String,
    @SerializedName("weight")
    val weight: Int = 0,//وزن
    @SerializedName("whole_price")
    val wholePrice: Int = 0,//قیمت عمده
    @SerializedName("whole_sale_price")
    val wholeSalePrice: Int = 0//قیمت عمده با تخفیف
)