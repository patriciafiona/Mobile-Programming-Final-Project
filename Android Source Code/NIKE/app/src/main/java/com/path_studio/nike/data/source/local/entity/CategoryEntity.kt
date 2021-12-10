package com.path_studio.nike.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "category_entities")
data class CategoryEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    var name: String
)