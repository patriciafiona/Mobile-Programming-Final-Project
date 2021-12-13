package com.path_studio.nike.ui.detailProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.CartEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.vo.Resource

class DetailProductViewModel(private val nikeRepository: NikeRepository): ViewModel()  {
    fun getProductsByID(id: Int):
            LiveData<Resource<PagedList<ProductEntity>>> = nikeRepository.getProductById(id)

    fun setFavorite(selectedProduct: ProductEntity) {
        val newState = !selectedProduct.favorite
        nikeRepository.setFavoriteProduct(selectedProduct, newState)
    }

    fun insertProductToCart(data: CartEntity) = nikeRepository.insertProductToCart(data)
    fun updateProductToCart(data: CartEntity) = nikeRepository.updateProductToCart(data)
    fun checkProductInCart(productDetailId: Int):
            LiveData<PagedList<CartDetailEntity>> = nikeRepository.getProductInCartById(productDetailId)
}