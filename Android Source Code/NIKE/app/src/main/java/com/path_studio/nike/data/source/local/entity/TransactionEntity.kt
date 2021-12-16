package com.path_studio.nike.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransactionEntity(
    val int: Int?,
    val transaction_id: String,
    val user_id: Int,
    val product_id : Int,
    val quantity: Int,
    val color_id: Int,
    val discount: Int,
    val price: Double,
    val totalAllPrice: Double,
    val totalAllProduct: Int,
    val size: Int,
    val status_id: Int
):Parcelable
