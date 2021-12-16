package com.path_studio.nike.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.CartEntity
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.data.source.remote.response.UserResponse
import com.path_studio.nike.vo.Resource

interface NikeDataSource {
    fun getAllProduct(): LiveData<Resource<PagedList<ProductEntity>>>
    fun getSearchResult(name: String): LiveData<List<ProductEntity>>
    fun getProductById(id: Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getLatestProductWithLimit(categoryId: Int, limit: Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductByCategory(categoryId: Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductByCategoryWithLimit(categoryId: Int, limit:Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit:Int): LiveData<Resource<PagedList<ProductEntity>>>

    fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>>

    fun getFavoriteProducts(): LiveData<PagedList<ProductEntity>>
    fun setFavoriteProduct(product: ProductEntity, newState: Boolean)

    fun getProductInCart(): LiveData<PagedList<CartDetailEntity>>
    fun getProductInCartById(productDetailId: Int): LiveData<PagedList<CartDetailEntity>>
    fun insertProductToCart(data: CartEntity)
    fun updateProductToCart(data: CartEntity)
    fun deleteProductToCart(id: Int)

    fun getUserData(email: String): LiveData<UserEntity>
    fun insertUserData(data: UserEntity): LiveData<String>
    fun updateUserLogin(email: String, password: String, isLogin: Int): LiveData<String>
    fun updateUserLogin(email: String, isLogin: Int): LiveData<String>
    fun updateUserData(data: UserEntity): LiveData<String>
    fun deleteUserdata(email: String, password: String, reinput_password: String): LiveData<String>
}