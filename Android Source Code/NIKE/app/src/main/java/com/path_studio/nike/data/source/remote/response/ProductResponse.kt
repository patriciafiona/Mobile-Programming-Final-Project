package com.path_studio.nike.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

	@field:SerializedName("results")
	val results: List<ProductResponseItem>,

	@field:SerializedName("status")
	val status: Int
)

data class ProductResponseItem(

	@field:SerializedName("type_name")
	val typeName: String,

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

	@field:SerializedName("created_at")
	val createdAt: Long,

	@field:SerializedName("color_id")
	val colorId: Int,

	@field:SerializedName("category_id")
	val categoryId: Int,

	@field:SerializedName("updated_at")
	val updatedAt: Long,

	@field:SerializedName("price")
	val price: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("style")
	val style: String,

	@field:SerializedName("productId")
	val productId: Int,

	@field:SerializedName("stock")
	val stock: Int,

	@field:SerializedName("photo_01")
	val photo01: String,

	@field:SerializedName("photo_02")
	val photo02: String,

	@field:SerializedName("photo_03")
	val photo03: String?,

	@field:SerializedName("photo_04")
	val photo04: String?,

	@field:SerializedName("photo_05")
	val photo05: String?,

	@field:SerializedName("product_detail_id")
	val productDetailId: Int,

	@field:SerializedName("color_code")
	val colorCode: String,

	@field:SerializedName("discount")
	val discount: Int,
)
