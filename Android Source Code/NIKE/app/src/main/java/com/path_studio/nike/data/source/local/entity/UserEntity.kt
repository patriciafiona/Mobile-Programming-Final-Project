package com.path_studio.nike.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entities")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "phone_number")
    var phone_number: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "birthday")
    var birthday: String
)