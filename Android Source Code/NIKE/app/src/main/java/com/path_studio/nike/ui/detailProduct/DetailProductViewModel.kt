package com.path_studio.nike.ui.detailProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.vo.Resource

class DetailProductViewModel(private val nikeRepository: NikeRepository): ViewModel()  {
    fun getProductsByID(id: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductById(id)

    fun setFavorite(selectedProduct: ProductEntity) {
        val newState = !selectedProduct.favorite
        nikeRepository.setFavoriteProduct(selectedProduct, newState)
    }
}