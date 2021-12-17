package com.path_studio.nike.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "category_entities", indices = [Index(value = ["name"], unique = true)])
data class CategoryEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    var name: String
)