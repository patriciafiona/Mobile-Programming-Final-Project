package com.path_studio.nike.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.path_studio.nike.data.source.remote.response.CategoryResponseItem
import com.path_studio.nike.data.source.remote.response.ProductResponseItem
import com.path_studio.nike.data.source.remote.api.ApiConfig
import com.path_studio.nike.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource ? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    //No need to connect to Room, so keep the code
    suspend fun getSearchResult(name: String, callback: CallbackLoadSearchResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getSearchResult(name).await().results.let{
                listResult -> callback.onSearchResultReceived((
                    listResult
                ))
            EspressoIdlingResource.decrement()
        }
    }

    interface CallbackLoadSearchResult{
        fun onSearchResultReceived(showResponse: List<ProductResponseItem?>?)
    }

    fun getAllProduct(): LiveData<ApiResponse<List<ProductResponseItem>>> {
        EspressoIdlingResource.increment()
        val resultProductResponse = MutableLiveData<ApiResponse<List<ProductResponseItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getAllProduct().await()
                resultProductResponse.postValue(ApiResponse.success(response.results))
            }catch (e: IOException){
                Log.e("getAllProduct Error", e.message.toString())
                resultProductResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return  resultProductResponse
    }

    fun getAllCategory(): LiveData<ApiResponse<List<CategoryResponseItem>>> {
        EspressoIdlingResource.increment()
        val resultCategoryResponse = MutableLiveData<ApiResponse<List<CategoryResponseItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getAllCategories().await()
                resultCategoryResponse.postValue(ApiResponse.success(response.results))
            }catch (e: IOException){
                Log.e("getAllProduct Error", e.message.toString())
                resultCategoryResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return  resultCategoryResponse
    }
}