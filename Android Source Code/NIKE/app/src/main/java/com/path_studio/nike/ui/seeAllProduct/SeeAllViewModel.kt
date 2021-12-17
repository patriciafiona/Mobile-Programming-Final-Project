package com.path_studio.nike.ui.seeAllProduct

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.vo.Resource

class SeeAllViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>> = nikeRepository.getAllCategory()
    fun getProductsByCategory(activity: LifecycleOwner,  categoryId: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductByCategory(activity, categoryId)

    fun setFavorite(selectedProduct: ProductEntity) {
        val newState = !selectedProduct.favorite
        nikeRepository.setFavoriteProduct(selectedProduct, newState)
    }
}