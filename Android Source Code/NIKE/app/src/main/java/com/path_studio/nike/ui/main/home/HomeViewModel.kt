package com.path_studio.nike.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.vo.Resource

class HomeViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>> = nikeRepository.getAllCategory()
    fun getProductsByCategoryWithLimit(categoryId: Int, limit: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductByCategoryWithLimit(categoryId, limit)
    fun getLatestProductWithLimit(categoryId: Int, limit: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getLatestProductWithLimit(categoryId, limit)
    fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductsByCategoryAndTypeWithLimit(categoryId, type_name, limit)

    fun setFavorite(selectedProduct: ProductEntity) {
        val newState = !selectedProduct.favorite
        nikeRepository.setFavoriteProduct(selectedProduct, newState)
    }
}