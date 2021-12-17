package com.path_studio.nike.ui.main.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.ProductEntity

class FavoriteViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getFavoriteProducts(): LiveData<PagedList<ProductEntity>> {
        return nikeRepository.getFavoriteProducts()
    }

    fun setFavorite(selectedProduct: ProductEntity) {
        val newState = !selectedProduct.favorite!!
        nikeRepository.setFavoriteProduct(selectedProduct, newState)
    }
}