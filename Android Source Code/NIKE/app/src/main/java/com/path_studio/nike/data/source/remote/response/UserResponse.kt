package com.path_studio.nike.data.source.remote.response

import android.os.Parcelable
import com.path_studio.nike.data.source.local.entity.UserEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val status: Int,
    val results: UserEntity
):Parcelable