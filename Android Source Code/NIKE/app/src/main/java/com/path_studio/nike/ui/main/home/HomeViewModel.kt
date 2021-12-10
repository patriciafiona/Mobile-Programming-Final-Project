package com.path_studio.nike.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.moviecatalogue.vo.Resource
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity

class HomeViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getProductsByCategory(categoryId: Int): LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductByCategory(categoryId)
    fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>> = nikeRepository.getAllCategory()
}