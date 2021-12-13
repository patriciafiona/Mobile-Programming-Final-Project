package com.path_studio.nike.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.di.Injection
import com.path_studio.nike.ui.detailProduct.DetailProductViewModel
import com.path_studio.nike.ui.main.favorite.FavoriteViewModel
import com.path_studio.nike.ui.main.home.HomeViewModel
import com.path_studio.nike.ui.main.shoppingCart.ShoppingCartViewModel
import com.path_studio.nike.ui.seeAllProduct.SeeAllViewModel

class ViewModelFactory private constructor(private val mNikeRepository: NikeRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideNikeRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mNikeRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mNikeRepository) as T
            }
            modelClass.isAssignableFrom(SeeAllViewModel::class.java) -> {
                SeeAllViewModel(mNikeRepository) as T
            }
            modelClass.isAssignableFrom(DetailProductViewModel::class.java) -> {
                DetailProductViewModel(mNikeRepository) as T
            }
            modelClass.isAssignableFrom(ShoppingCartViewModel::class.java) -> {
                ShoppingCartViewModel(mNikeRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}