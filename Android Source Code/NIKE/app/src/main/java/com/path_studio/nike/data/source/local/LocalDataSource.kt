package com.path_studio.nike.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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
    fun getProductByCategory(categoryId: Int): DataSource.Factory<Int, ProductEntity> = mNikeDao.getProductByCategory(categoryId)
    fun getFavoriteProduct(): DataSource.Factory<Int, ProductEntity> = mNikeDao.getFavoriteProduct()

    fun insertProducts(products: ArrayList<ProductEntity>) = mNikeDao.insertProducts(products)
    fun setProductFavorite(product : ProductEntity, newState: Boolean) {
        product.favorite = newState
        mNikeDao.updateProduct(product)
    }

    //-------------------------------------------------------------------------------------
    //Category Section
    fun getAllCategory(): DataSource.Factory<Int, CategoryEntity> = mNikeDao.getAllCategory()

    fun insertCategories(categories: ArrayList<CategoryEntity>) = mNikeDao.insertCategories(categories)

}