package com.path_studio.nike.data.source.remote.api

import com.path_studio.nike.data.source.remote.response.CategoryResponse
import com.path_studio.nike.data.source.remote.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("product")
    fun getAllProduct(): Call<ProductResponse>

    @GET("product/{id}")
    fun getProductByID(
        @Path("id") id: String
    ): Call<ProductResponse>

    @GET("product/detail_id/{id}")
    fun getProductByDetailID(
        @Path("id") id: String
    ): Call<ProductResponse>

    @GET("product/category_id/{category_id}")
    fun getProductByCategory(
        @Path("category_id") category_id: String
    ): Call<ProductResponse>

    @GET("category")
    fun getAllCategories(): Call<CategoryResponse>
}