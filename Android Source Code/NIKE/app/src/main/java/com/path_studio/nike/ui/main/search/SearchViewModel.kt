package com.path_studio.nike.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.entity.ProductEntity

class SearchViewModel(private val nikeRepository: NikeRepository): ViewModel() {
    fun getSearchResult(name: String): LiveData<List<ProductEntity>> = nikeRepository.getSearchResult(name)
    fun getLoading(): LiveData<Boolean> = nikeRepository.isLoading
}