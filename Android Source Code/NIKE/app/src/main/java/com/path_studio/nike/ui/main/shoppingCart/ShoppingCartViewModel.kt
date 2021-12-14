package com.path_studio.nike.ui.main.shoppingCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.CartEntity

class ShoppingCartViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getShoppingCartData():
            LiveData<PagedList<CartDetailEntity>> = nikeRepository.getProductInCart()
    fun updateProductToCart(data: CartEntity) = nikeRepository.updateProductToCart(data)
    fun deleteProductToCart(id: Int) = nikeRepository.deleteProductToCart(id)
}