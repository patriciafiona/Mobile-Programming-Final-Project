package com.path_studio.nike.ui.main.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.vo.Resource

class HomeViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>> = nikeRepository.getAllCategory()
    fun getProductsByCategoryWithLimit(activity: LifecycleOwner,  categoryId: Int, limit: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductByCategoryWithLimit(activity, categoryId, limit)
    fun getLatestProductWithLimit(activity: LifecycleOwner, categoryId: Int, limit: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getLatestProductWithLimit(activity, categoryId, limit)
    fun getProductsByCategoryAndTypeWithLimit(activity: LifecycleOwner, categoryId: Int, type_name: String, limit: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductsByCategoryAndTypeWithLimit(activity, categoryId, type_name, limit)

    fun setFavorite(selectedProduct: ProductEntity) {
        val newState = !selectedProduct.favorite
        nikeRepository.setFavoriteProduct(selectedProduct, newState)
    }
}