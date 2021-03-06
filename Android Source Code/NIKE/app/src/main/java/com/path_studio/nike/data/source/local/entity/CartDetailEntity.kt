package com.path_studio.nike.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartDetailEntity(
    var id: Int,
    var productDetailId: Int,
    var quantity: Int,
    var size: Int,

    val name: String,
    val productId: Long,
    val typeName: String,
    val categoryName: String,
    val price: Double,
    val colorId: Int,
    val colorCode: String,
    val colorDescription: String,
    val photo01: String,
    val stock: Int,
    val discount: Int
):Parcelable