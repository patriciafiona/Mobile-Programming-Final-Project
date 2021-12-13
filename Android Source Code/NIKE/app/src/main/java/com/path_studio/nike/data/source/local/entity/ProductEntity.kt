package com.path_studio.nike.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_entities")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @NonNull
    @ColumnInfo(name = "productId")
    val productId: Long,

    @ColumnInfo(name = "productDetailId")
    val productDetailId: Int,

    @ColumnInfo(name = "typeName")
    val typeName: String,

    @ColumnInfo(name = "categoryName")
    val categoryName: String,

    @ColumnInfo(name = "typeId")
    val typeId: Int,

    @ColumnInfo(name = "colorDescription")
    val colorDescription: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "colorId")
    val colorId: Int,

    @ColumnInfo(name = "categoryId")
    val categoryId: Int,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "style")
    val style: String,

    @ColumnInfo(name = "stock")
    val stock: Int,

    @ColumnInfo(name = "colorCode")
    val colorCode: String,

    @ColumnInfo(name = "photo01")
    val photo01: String,

    @ColumnInfo(name = "photo02")
    val photo02: String? = null,

    @ColumnInfo(name = "photo03")
    val photo03: String? = null,

    @ColumnInfo(name = "photo04")
    val photo04: String? = null,

    @ColumnInfo(name = "photo05")
    val photo05: String? = null,

    @ColumnInfo(name = "created_at")
    val created_at: Long,

    @ColumnInfo(name = "updated_at")
    val updated_at: Long,

    @NonNull
    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)