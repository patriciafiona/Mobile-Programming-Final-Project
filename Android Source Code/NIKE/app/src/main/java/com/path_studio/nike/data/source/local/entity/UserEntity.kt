package com.path_studio.nike.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class UserEntity (
    var id: Int?,
    var name: String,
    var email: String,
    var password: String,
    var phone_number: String,
    var address: String,
    var birthday: String,
    var isLogin: Int?
): Parcelable