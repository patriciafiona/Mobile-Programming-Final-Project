package com.path_studio.nike.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TransactionResponse(

	@field:SerializedName("results")
	val results: List<TransactionResponseItem>,

	@field:SerializedName("status")
	val status: Int
)

data class TransactionResponseItem(

	@field:SerializedName("transaction_id")
	val transactionId: String,

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("color_id")
	val colorId: Int,

	@field:SerializedName("status_id")
	val statusId: Int,

	@field:SerializedName("size")
	val size: Int,

	@field:SerializedName("updated_at")
	val updatedAt: Long,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("price")
	val price: Double,

	@field:SerializedName("totalAllPrice")
	val totalAllPrice: Double,

	@field:SerializedName("totalAllProduct")
	val totalAllProduct: Int,

	@field:SerializedName("product_id")
	val productId: Int,

	@field:SerializedName("discount")
	val discount: Int,

	@field:SerializedName("created_at")
	val createdAt: Long,

	@field:SerializedName("id")
	val id: Int
)
