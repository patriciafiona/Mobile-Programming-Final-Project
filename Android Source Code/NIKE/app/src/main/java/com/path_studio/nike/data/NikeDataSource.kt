package com.path_studio.nike.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.path_studio.nike.vo.Resource
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity

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
}