package com.path_studio.nike.data.source.local

import androidx.paging.DataSource
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.CartEntity
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.data.source.local.room.NikeDao

class LocalDataSource private constructor(private val mNikeDao: NikeDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(nikeDao: NikeDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(nikeDao)
    }

    //-------------------------------------------------------------------------------------
    //Product Section
    fun getAllProduct(): DataSource.Factory<Int, ProductEntity> = mNikeDao.getAllProduct()
    fun getProductById(id: Int): DataSource.Factory<Int, ProductEntity> = mNikeDao.getProductById(id)
    fun getLatestProductWithLimit(categoryId:Int, limit: Int): DataSource.Factory<Int, ProductEntity> = mNikeDao.getLatestProductWithLimit(categoryId, limit)
    fun getProductByCategory(categoryId: Int): DataSource.Factory<Int, ProductEntity> = mNikeDao.getProductByCategory(categoryId)
    fun getProductByCategoryWithLimit(categoryId: Int, limit: Int):
            DataSource.Factory<Int, ProductEntity> = mNikeDao.getProductByCategoryWithLimit(categoryId, limit)
    fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit: Int):
            DataSource.Factory<Int, ProductEntity> = mNikeDao.getProductsByCategoryAndTypeWithLimit(categoryId, type_name, limit)
    fun getFavoriteProducts(): DataSource.Factory<Int, ProductEntity> = mNikeDao.getFavoriteProducts()
    fun getFavoriteProductsDetailId(): DataSource.Factory<Int, Int> = mNikeDao.getFavoriteProductsDetailId()

    fun insertProducts(products: ArrayList<ProductEntity>) = mNikeDao.insertProducts(products)
    fun setProductFavorite(product : ProductEntity, newState: Boolean) {
        product.favorite = newState
        mNikeDao.updateProduct(product)
    }

    //-------------------------------------------------------------------------------------
    //Category Section
    fun getAllCategory(): DataSource.Factory<Int, CategoryEntity> = mNikeDao.getAllCategory()
    fun insertCategories(categories: ArrayList<CategoryEntity>) = mNikeDao.insertCategories(categories)

    //-------------------------------------------------------------------------------------
    //Cart Section
    fun getShoppingCart(): DataSource.Factory<Int, CartDetailEntity> = mNikeDao.getShoppingCart()
    fun getProductInCartById(productDetailId: Int): DataSource.Factory<Int, CartDetailEntity> = mNikeDao.getProductInCartById(productDetailId)
    fun insertCartProduct(products: ArrayList<CartEntity>) = mNikeDao.insertCartProduct(products)
    fun updateCartProduct(product : CartEntity) = mNikeDao.updateCartProduct(product)
    fun deleteCartProduct(id : Int) = mNikeDao.deleteCartProduct(id)

}