package com.path_studio.nike.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.path_studio.nike.data.source.remote.api.ApiConfig
import com.path_studio.nike.data.source.remote.response.CategoryResponseItem
import com.path_studio.nike.data.source.remote.response.ProductResponseItem
import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.data.source.remote.response.UserResponse
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

    suspend fun insertUserData(data: UserEntity, callback: CallbackLoadInsertResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().insertUser(
            data.name,
            data.email,
            data.password,
            data.phone_number,
            data.address,
            data.birthday)
            .await().status.let{
                listResult -> callback.onInsertResultReceived((
                listResult
                ))
            EspressoIdlingResource.decrement()
        }
    }

    interface CallbackLoadInsertResult{
        fun onInsertResultReceived(showResponse: String?)
    }

    suspend fun updateUserLogin(email: String, password: String, isLogin: Int, callback: CallbackLoadUpdateLoginResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().updateUserLogin(email, password, isLogin)
            .await().status.let{
                    listResult -> callback.onUpdateLoginResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    suspend fun userLogout(email: String, isLogin: Int, callback: CallbackLoadUpdateLoginResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().userLogout(email, isLogin)
            .await().status.let{
                    listResult -> callback.onUpdateLoginResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    interface CallbackLoadUpdateLoginResult{
        fun onUpdateLoginResultReceived(showResponse: String?)
    }

    suspend fun updateUserData(data: UserEntity, callback: CallbackLoadUpdateResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().updateUser(
            data.name,
            data.email,
            data.password,
            data.phone_number,
            data.address,
            data.birthday)
            .await().status.let{
                    listResult -> callback.onUpdateResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    interface CallbackLoadUpdateResult{
        fun onUpdateResultReceived(showResponse: String?)
    }

    suspend fun deleteUserData(email: String, password: String, reinput_password: String, callback: CallbackLoadDeleteResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().deleteUser(
            email,
            password,
            reinput_password)
            .await().status.let{
                    listResult -> callback.onDeleteResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    interface CallbackLoadDeleteResult{
        fun onDeleteResultReceived(showResponse: String?)
    }

    suspend fun getUserData(email: String, callback: CallbackLoadGetUserResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getUserByEmail(email)
            .await().results.let{
                    listResult -> callback.onGetResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    interface CallbackLoadGetUserResult{
        fun onGetResultReceived(showResponse: UserEntity?)
    }
}