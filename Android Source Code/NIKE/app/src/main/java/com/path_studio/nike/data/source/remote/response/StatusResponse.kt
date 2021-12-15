package com.path_studio.nike.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class StatusResponse (
    @field:SerializedName("status")
    val status: String
)