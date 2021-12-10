package com.path_studio.nike.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.path_studio.moviecatalogue.vo.Resource
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity

interface NikeDataSource {
    fun getAllProduct(): LiveData<Resource<PagedList<ProductEntity>>>
    fun getProductByCategory(categoryId: Int): LiveData<Resource<PagedList<ProductEntity>>>

    fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>>
}