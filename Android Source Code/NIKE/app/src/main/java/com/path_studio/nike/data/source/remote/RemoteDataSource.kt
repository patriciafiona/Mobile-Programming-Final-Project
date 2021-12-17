package com.path_studio.nike.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.path_studio.nike.data.source.local.entity.TransactionEntity
import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.data.source.remote.api.ApiConfig
import com.path_studio.nike.data.source.remote.response.CategoryResponseItem
import com.path_studio.nike.data.source.remote.response.ProductResponseItem
import com.path_studio.nike.data.source.remote.response.TransactionResponseItem
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

    fun getProductById(id: Int): LiveData<ApiResponse<List<ProductResponseItem>>> {
        EspressoIdlingResource.increment()
        val resultProductResponse = MutableLiveData<ApiResponse<List<ProductResponseItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getProductByID(id.toString()).await()
                resultProductResponse.postValue(ApiResponse.success(response.results))
            }catch (e: IOException){
                Log.e("getProductByID Error", e.message.toString())
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

    fun getProductByCategory(category_id: Int): LiveData<ApiResponse<List<ProductResponseItem>>> {
        EspressoIdlingResource.increment()
        val resultProductResponse = MutableLiveData<ApiResponse<List<ProductResponseItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getProductByCategory(category_id.toString()).await()
                resultProductResponse.postValue(ApiResponse.success(response.results))
            }catch (e: IOException){
                Log.e("getProductByCategory Error", e.message.toString())
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

    fun getProductByCategoryWithLimit(category_id: Int, limit: Int): LiveData<ApiResponse<List<ProductResponseItem>>> {
        EspressoIdlingResource.increment()
        val resultProductResponse = MutableLiveData<ApiResponse<List<ProductResponseItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getProductByCategoryWithLimit(category_id.toString(), limit).await()
                resultProductResponse.postValue(ApiResponse.success(response.results))
            }catch (e: IOException){
                Log.e("getProductByCategoryWithLimit Error", e.message.toString())
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

    fun getProductsByCategoryAndTypeWithLimit(category_id: Int, type_name: String, limit: Int): LiveData<ApiResponse<List<ProductResponseItem>>> {
        EspressoIdlingResource.increment()
        val resultProductResponse = MutableLiveData<ApiResponse<List<ProductResponseItem>>>()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = ApiConfig.getApiService().getProductsByCategoryAndTypeWithLimit(category_id.toString(),
                    type_name, limit).await()
                resultProductResponse.postValue(ApiResponse.success(response.results))
            }catch (e: IOException){
                Log.e("getProductsByCategoryAndTypeWithLimit Error", e.message.toString())
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
                Log.e("getAllCategory Error", e.message.toString())
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

    suspend fun insertTransaction(data: TransactionEntity, email: String, callback: CallbackLoadInsertResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().insertTransaction(
            data.transaction_id,
            email,
            data.product_id,
            data.quantity,
            data.color_id,
            data.discount,
            data.price,
            data.totalAllPrice,
            data.totalAllProduct,
            data.size,
            1
        )
            .await().status.let{
                    listResult -> callback.onInsertResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    suspend fun deleteTransaction(transaction_id: String, callback: CallbackLoadDeleteResult) {
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().deleteTransaction(transaction_id)
            .await().status.let{
                    listResult -> callback.onDeleteResultReceived((
                    listResult
                    ))
                EspressoIdlingResource.decrement()
            }
    }

    suspend fun getUserTransactionByStatus(userId: Int, status: Int, callback: CallbackLoadGetTransactionResult){
        EspressoIdlingResource.increment()
        ApiConfig.getApiService().getUserTransactionByStatus(userId, status)
                .await().results.let{
                    listResult -> callback.onGetResultReceived((
                        listResult
                        ))
                    EspressoIdlingResource.decrement()
                }
    }

    interface CallbackLoadGetTransactionResult{
        fun onGetResultReceived(showResponse: List<TransactionResponseItem?>?)
    }
}