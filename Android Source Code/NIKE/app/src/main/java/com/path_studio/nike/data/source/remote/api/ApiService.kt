package com.path_studio.nike.data.source.remote.api

import com.path_studio.nike.data.source.remote.response.CategoryResponse
import com.path_studio.nike.data.source.remote.response.ProductResponse
import com.path_studio.nike.data.source.remote.response.StatusResponse
import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

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

    //Limit Result
    @GET("product")
    fun getAllProductWithLimit(
        @Query("limit") limit: Int
    ): Call<ProductResponse>

    @GET("product/category_id/{category_id}")
    fun getProductByCategoryWithLimit(
        @Path("category_id") category_id: String,
        @Query("limit") limit: Int
    ): Call<ProductResponse>

    @GET("product/name/{name}")
    fun getSearchResult(
        @Path("name") name: String
    ): Call<ProductResponse>

    @FormUrlEncoded
    @POST("UserInsert")
    fun insertUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("address") address: String,
        @Field("birthday") birthday: String
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("UserLogin")
    fun updateUserLogin(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("isLogin") isLogin: Int,
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("UserLogout")
    fun userLogout(
        @Field("email") email: String,
        @Field("isLogin") isLogin: Int,
    ): Call<StatusResponse>

    @GET("user/email/{email}")
    fun getUserByEmail(
        @Path("email") email: String
    ): Call<UserResponse>
}