package com.path_studio.nike.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("results")
	val results: List<CategoryResponseItem>
)

data class CategoryResponseItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)
