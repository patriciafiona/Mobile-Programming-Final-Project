package com.path_studio.nike.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.LivePagedListBuilder
import com.path_studio.nike.vo.Resource
import com.path_studio.nike.data.source.local.LocalDataSource
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity
import com.path_studio.nike.data.source.remote.ApiResponse
import com.path_studio.nike.data.source.remote.RemoteDataSource
import com.path_studio.nike.data.source.remote.response.CategoryResponseItem
import com.path_studio.nike.data.source.remote.response.ProductResponse
import com.path_studio.nike.data.source.remote.response.ProductResponseItem
import com.path_studio.nike.utils.AppExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

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

    override fun getAllProduct(): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
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
                for(response in data){
                    val product = ProductEntity(
                        response.id.toLong(),
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
                        response.updatedAt
                    )
                    products.add(product)
                }
                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductByCategory(categoryId: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductByCategory(categoryId), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProduct()

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                for(response in data){
                    val product = ProductEntity(
                        response.id.toLong(),
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
                        response.updatedAt
                    )
                    products.add(product)
                }
                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductByCategoryWithLimit(categoryId: Int, limit: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductByCategoryWithLimit(categoryId, limit), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProduct()

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                for(response in data){
                    val product = ProductEntity(
                        response.id.toLong(),
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
                        response.updatedAt
                    )
                    products.add(product)
                }
                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

    override fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductsByCategoryAndTypeWithLimit(categoryId, type_name, limit), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProduct()

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                for(response in data){
                    val product = ProductEntity(
                        response.id.toLong(),
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
                        response.updatedAt
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
                    .setInitialLoadSizeHint(4)
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

    override fun getLatestProductWithLimit(categoryId: Int, limit: Int): LiveData<Resource<PagedList<ProductEntity>>> {
        return object: NetworkBoundResource<PagedList<ProductEntity>, List<ProductResponseItem>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ProductEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getLatestProductWithLimit(categoryId, limit), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProduct()

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                for(response in data){
                    val product = ProductEntity(
                        response.id.toLong(),
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
                        response.updatedAt
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
            .setInitialLoadSizeHint(4)
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
                                    responseSearch.id.toLong(),
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
                                    responseSearch.updatedAt
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
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getProductById(id), config).build()
            }

            override fun shouldFetch(data: PagedList<ProductEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ProductResponseItem>>> =
                remoteDataSource.getAllProduct()

            public override fun saveCallResult(data: List<ProductResponseItem>) {
                val products = ArrayList<ProductEntity>()
                for(response in data){
                    val product = ProductEntity(
                        response.id.toLong(),
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
                        response.updatedAt
                    )
                    products.add(product)
                }
                localDataSource.insertProducts(products)
            }
        }.asLiveData()
    }

}