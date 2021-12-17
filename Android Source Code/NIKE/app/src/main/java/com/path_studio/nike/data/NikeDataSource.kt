package com.path_studio.nike.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.path_studio.nike.data.source.local.entity.*
import com.path_studio.nike.vo.Resource

interface NikeDataSource {
    fun getAllProduct(): LiveData<Resource<PagedList<ProductEntity>>>
    fun getSearchResult(name: String): LiveData<List<ProductEntity>>
    fun getProductById(id: Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getLatestProductWithLimit(activity: LifecycleOwner, categoryId: Int, limit: Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductByCategory(activity: LifecycleOwner, categoryId: Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductByCategoryWithLimit(activity: LifecycleOwner, categoryId: Int, limit:Int): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductsByCategoryAndTypeWithLimit(activity: LifecycleOwner, categoryId: Int, type_name: String, limit:Int): LiveData<Resource<PagedList<ProductEntity>>>

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

    fun insertTransaction(data: TransactionEntity, email: String): LiveData<String>
    fun deleteTransaction(transaction_id: String): LiveData<String>
    fun getUserTransactionByStatus(userId: Int, status: Int): LiveData<List<TransactionEntity>>
}