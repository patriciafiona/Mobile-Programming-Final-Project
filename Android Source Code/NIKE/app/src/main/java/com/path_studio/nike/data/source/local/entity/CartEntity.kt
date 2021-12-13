package com.path_studio.nike.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_entities")
data class CartEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "product_detail_id")
    var product_detail_id: Int,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "size")
    var size: Int
)