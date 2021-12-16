package com.path_studio.nike.data.source.remote.api

import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.data.source.remote.response.*
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

    @FormUrlEncoded
    @POST("UserUpdate")
    fun updateUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("address") address: String,
        @Field("birthday") birthday: String
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("UserDelete")
    fun deleteUser(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("reinput_password") reinput_password: String,
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("TransactionInsert")
    fun insertTransaction(
        @Field("transaction_id") transaction_id: String,
        @Field("email") email: String,
        @Field("product_id") product_id: Int,
        @Field("quantity") quantity: Int,
        @Field("color_id") color_id: Int,
        @Field("discount") discount: Int,
        @Field("price") price: Double,
        @Field("total_all_price") total_all_price: Double,
        @Field("total_all_product") total_all_product: Int,
        @Field("size") size: Int,
        @Field("status_id") status_id: Int,
    ): Call<StatusResponse>

    @FormUrlEncoded
    @POST("TransactionDelete")
    fun deleteTransaction(
        @Field("transaction_id") transaction_id: String,
    ): Call<StatusResponse>

    @GET("FindUserTransactionByStatus")
    fun getUserTransactionByStatus(
            @Query("user_id") user_id: Int,
            @Query("status") status: Int,
    ): Call<TransactionResponse>
}