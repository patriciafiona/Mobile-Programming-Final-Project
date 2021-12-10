package com.path_studio.nike.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
	@field:SerializedName("status")
	val status: Int,

	@field:SerializedName("results")
	val results: List<ProductResponseItem>
)

data class ProductResponseItem(

	@field:SerializedName("type_name")
	val typeName: String,

	@field:SerializedName("photo_01")
	val photo01: String,

	@field:SerializedName("photo_02")
	val photo02: String? = null,

	@field:SerializedName("category_name")
	val categoryName: String,

	@field:SerializedName("type_id")
	val typeId: Int,

	@field:SerializedName("color_description")
	val colorDescription: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("color_id")
	val colorId: Int,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("price")
	val price: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("style")
	val style: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("stock")
	val stock: Int,

	@field:SerializedName("photo_03")
	val photo03: String? = null,

	@field:SerializedName("photo_04")
	val photo04: String? = null,

	@field:SerializedName("product_detail_id")
	val productDetailId: Int,

	@field:SerializedName("photo_05")
	val photo05: String? = null,

	@field:SerializedName("color_code")
	val colorCode: String
)
