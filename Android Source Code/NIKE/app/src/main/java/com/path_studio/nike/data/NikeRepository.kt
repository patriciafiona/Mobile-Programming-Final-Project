package com.path_studio.nike.data

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.path_studio.nike.data.source.local.LocalDataSource
import com.path_studio.nike.data.source.local.entity.*
import com.path_studio.nike.data.source.remote.ApiResponse
import com.path_studio.nike.data.source.remote.RemoteDataSource
import com.path_studio.nike.data.source.remote.StatusResponse
import com.path_studio.nike.data.source.remote.response.CategoryResponseItem
import com.path_studio.nike.data.source.remote.response.ProductResponseItem
import com.path_studio.nike.data.source.remote.response.TransactionResponseItem
import com.path_studio.nike.utils.AppExecutors
import com.path_studio.nike.vo.Resource
import kotlinx.coroutines.*

class NikeRepository private constructor(private val remoteDataSource: RemoteDataSource,
                                         private val localDataSource: LocalDataSource,
                                         private val appExecutors: AppExecutors
) : NikeDataSource {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        @Volatile
        private var instance: NikeRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): NikeRepository =
            instance ?: synchronized(this) {
                instance ?: NikeRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllProduct(activity: LifecycleOwner): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllProduct(), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProduct()

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()

                for(response in data) {
                    val product = ProductEntity(
                        0,
                        response.productId.toLong(),
                        response.productDetailId,
                        response.typeName,
                        response.categoryName,
                        response.typeId,
                        response.colorDescription,
                        response.rating,
                        response.description,
                        response.colorId,
                        response.categoryId,
                        response.price,
                        response.name,
                        response.style,
                        response.stock,
                        response.colorCode,
                        response.photo01,
                        response.photo02,
                        response.photo03,
                        response.photo04,
                        response.photo05,
                        response.createdAt,
                        response.updatedAt,
                        response.discount
                    )
                    products.add(product)
                }

                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductByCategory(activity: LifecycleOwner, categoryId: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductByCategory(categoryId), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getProductByCategory(categoryId)

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()

                for(response in data) {
                    val product = ProductEntity(
                        0,
                        response.productId.toLong(),
                        response.productDetailId,
                        response.typeName,
                        response.categoryName,
                        response.typeId,
                        response.colorDescription,
                        response.rating,
                        response.description,
                        response.colorId,
                        response.categoryId,
                        response.price,
                        response.name,
                        response.style,
                        response.stock,
                        response.colorCode,
                        response.photo01,
                        response.photo02,
                        response.photo03,
                        response.photo04,
                        response.photo05,
                        response.createdAt,
                        response.updatedAt,
                        response.discount
                    )
                    products.add(product)
                }

                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductByCategoryWithLimit(activity: LifecycleOwner, categoryId: Int, limit: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductByCategoryWithLimit(categoryId, limit), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getProductByCategoryWithLimit(categoryId, limit)

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()

                for(response in data) {
                    val product = ProductEntity(
                        0,
                        response.productId.toLong(),
                        response.productDetailId,
                        response.typeName,
                        response.categoryName,
                        response.typeId,
                        response.colorDescription,
                        response.rating,
                        response.description,
                        response.colorId,
                        response.categoryId,
                        response.price,
                        response.name,
                        response.style,
                        response.stock,
                        response.colorCode,
                        response.photo01,
                        response.photo02,
                        response.photo03,
                        response.photo04,
                        response.photo05,
                        response.createdAt,
                        response.updatedAt,
                        response.discount
                    )
                    products.add(product)
                }

                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductsByCategoryAndTypeWithLimit(activity: LifecycleOwner, categoryId: Int, type_name: String, limit: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductsByCategoryAndTypeWithLimit(categoryId, type_name, limit), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getProductsByCategoryAndTypeWithLimit(categoryId, type_name, limit)

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()

                for(response in data) {
                    val product = ProductEntity(
                        0,
                        response.productId.toLong(),
                        response.productDetailId,
                        response.typeName,
                        response.categoryName,
                        response.typeId,
                        response.colorDescription,
                        response.rating,
                        response.description,
                        response.colorId,
                        response.categoryId,
                        response.price,
                        response.name,
                        response.style,
                        response.stock,
                        response.colorCode,
                        response.photo01,
                        response.photo02,
                        response.photo03,
                        response.photo04,
                        response.photo05,
                        response.createdAt,
                        response.updatedAt,
                        response.discount
                    )
                    products.add(product)
                }

                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getAllCategory(): LiveData<Resource<PagedList<CategoryEntity>>> {
        return object: NetworkBoundResource<PagedList<CategoryEntity>, List<CategoryResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<CategoryEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllCategory(), config).build()
            }

            override fun shouldFetch(data: PagedList<CategoryEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<CategoryResponseItem>>> =
                remoteDataSource.getAllCategory()

            public override fun saveCallResult(data: List<CategoryResponseItem>) {
                val categories = ArrayList<CategoryEntity>()
                for(response in data){
                    val category = CategoryEntity(
                        response.id.toLong(),
                        response.name,
                    )
                    categories.add(category)
                }
                localDataSource.insertCategories(categories)
            }
        }.asLiveData()
    }

    override fun getLatestProductWithLimit(activity: LifecycleOwner, categoryId: Int, limit: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getLatestProductWithLimit(categoryId, limit), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getProductByCategory(categoryId)

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()

                for(response in data) {
                    val product = ProductEntity(
                        0,
                        response.productId.toLong(),
                        response.productDetailId,
                        response.typeName,
                        response.categoryName,
                        response.typeId,
                        response.colorDescription,
                        response.rating,
                        response.description,
                        response.colorId,
                        response.categoryId,
                        response.price,
                        response.name,
                        response.style,
                        response.stock,
                        response.colorCode,
                        response.photo01,
                        response.photo02,
                        response.photo03,
                        response.photo04,
                        response.photo05,
                        response.createdAt,
                        response.updatedAt,
                        response.discount
                    )
                    products.add(product)
                }

                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getFavoriteProducts(): LiveData<PagedList<ProductEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1000)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteProducts(), config).build()
    }

    override fun setFavoriteProduct(product: ProductEntity, newState: Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setProductFavorite(product, newState)
        }
    }

    override fun getSearchResult(name: String): LiveData<List<ProductEntity>> {
        _isLoading.value = true
        val listOfResult = MutableLiveData<List<ProductEntity>>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.getSearchResult(name, object : RemoteDataSource.CallbackLoadSearchResult{
                override fun onSearchResultReceived(showResponse: List<ProductResponseItem?>?) {
                    val products = ArrayList<ProductEntity>()
                    if (showResponse != null) {
                        for(responseSearch in showResponse){
                            if (responseSearch != null) {
                                val product = ProductEntity(
                                    0,
                                    responseSearch.productId.toLong(),
                                    responseSearch.productDetailId,
                                    responseSearch.typeName,
                                    responseSearch.categoryName,
                                    responseSearch.typeId,
                                    responseSearch.colorDescription,
                                    responseSearch.rating,
                                    responseSearch.description,
                                    responseSearch.colorId,
                                    responseSearch.categoryId,
                                    responseSearch.price,
                                    responseSearch.name,
                                    responseSearch.style,
                                    responseSearch.stock,
                                    responseSearch.colorCode,
                                    responseSearch.photo01,
                                    responseSearch.photo02,
                                    responseSearch.photo03,
                                    responseSearch.photo04,
                                    responseSearch.photo05,
                                    responseSearch.createdAt,
                                    responseSearch.updatedAt,
                                    responseSearch.discount
                                )
                                products.add(product)
                            }
                        }
                    }
                    _isLoading.postValue(false)
                    listOfResult.postValue(products)
                }
            })
        }
        return listOfResult
    }

    override fun getProductById(id: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1000)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductById(id), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getProductById(id)

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                for(response in data){
                    val product = ProductEntity(
                        0,
                        response.productId.toLong(),
                        response.productDetailId,
                        response.typeName,
                        response.categoryName,
                        response.typeId,
                        response.colorDescription,
                        response.rating,
                        response.description,
                        response.colorId,
                        response.categoryId,
                        response.price,
                        response.name,
                        response.style,
                        response.stock,
                        response.colorCode,
                        response.photo01,
                        response.photo02,
                        response.photo03,
                        response.photo04,
                        response.photo05,
                        response.createdAt,
                        response.updatedAt,
                        response.discount
                    )
                    products.add(product)
                }
                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductInCart(): LiveData<PagedList<CartDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1000)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getShoppingCart(), config).build()
    }

    override fun getProductInCartById(productDetailId: Int): LiveData<PagedList<CartDetailEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1000)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getProductInCartById(productDetailId), config).build()
    }

    override fun insertProductToCart(data: CartEntity){
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertCartProduct(arrayListOf(data))
        }
    }

    override fun updateProductToCart(data: CartEntity){
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.updateCartProduct(data)
        }
    }

    override fun deleteProductToCart(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.deleteCartProduct(id)
        }
    }

    override fun getUserData(email: String): LiveData<UserEntity> {
        _isLoading.value = true
        val result = MutableLiveData<UserEntity>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.getUserData(email, object : RemoteDataSource.CallbackLoadGetUserResult{
                override fun onGetResultReceived(showResponse: UserEntity?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        result.postValue(showResponse!!)
                    }
                }
            })
        }
        return result
    }

    override fun insertUserData(data: UserEntity): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.insertUserData(data, object : RemoteDataSource.CallbackLoadInsertResult{
                override fun onInsertResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun updateUserLogin(email: String, password: String, isLogin: Int): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.updateUserLogin(email, password, isLogin, object : RemoteDataSource.CallbackLoadUpdateLoginResult{
                override fun onUpdateLoginResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun updateUserLogin(email: String, isLogin: Int): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.userLogout(email, isLogin, object : RemoteDataSource.CallbackLoadUpdateLoginResult{
                override fun onUpdateLoginResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun updateUserData(data: UserEntity): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.updateUserData(data, object : RemoteDataSource.CallbackLoadUpdateResult{
                override fun onUpdateResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun deleteUserdata(email: String, password: String, reinput_password: String): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.deleteUserData(email, password, reinput_password, object : RemoteDataSource.CallbackLoadDeleteResult{
                override fun onDeleteResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun insertTransaction(data: TransactionEntity, email: String): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.insertTransaction(data, email, object : RemoteDataSource.CallbackLoadInsertResult{
                override fun onInsertResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun deleteTransaction(transaction_id: String): LiveData<String>{
        _isLoading.value = true
        val statusResult = MutableLiveData<String>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.deleteTransaction(transaction_id, object : RemoteDataSource.CallbackLoadDeleteResult{
                override fun onDeleteResultReceived(showResponse: String?) {
                    if (showResponse != null) {
                        _isLoading.postValue(false)
                        statusResult.postValue(showResponse!!)
                    }
                }
            })
        }
        return statusResult
    }

    override fun getUserTransactionByStatus(userId: Int, status: Int): LiveData<List<TransactionEntity>>{
        _isLoading.value = true
        val listOfResult = MutableLiveData<List<TransactionEntity>>()
        CoroutineScope(Dispatchers.IO).launch{
            remoteDataSource.getUserTransactionByStatus(userId, status, object: RemoteDataSource.CallbackLoadGetTransactionResult{
                override fun onGetResultReceived(showResponse: List<TransactionResponseItem?>?) {
                    val transactions = ArrayList<TransactionEntity>()
                    if (showResponse != null) {
                        for(response in showResponse){
                            if (response != null) {
                                val transaction = TransactionEntity(
                                        response.id,
                                        response.transactionId,
                                        response.userId,
                                        response.productId,
                                        response.quantity,
                                        response.colorId,
                                        response.discount,
                                        response.price,
                                        response.totalAllPrice,
                                        response.totalAllProduct,
                                        response.size,
                                        response.statusId
                                )
                                transactions.add(transaction)
                            }
                        }
                    }
                    _isLoading.postValue(false)
                    listOfResult.postValue(transactions)
                }
            })
        }
        return listOfResult
    }

}