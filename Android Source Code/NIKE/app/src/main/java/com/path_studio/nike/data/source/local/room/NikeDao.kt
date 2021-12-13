package com.path_studio.nike.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity

@Dao
interface NikeDao {

    //-------------------------------------------------------------------------------------
    //Product Section
    @Query("SELECT * FROM product_entities GROUP BY productId ORDER BY productId ASC")
    fun getAllProduct(): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId GROUP BY productId ORDER BY updated_at DESC LIMIT :limit")
    fun getLatestProductWithLimit(categoryId: Int, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId GROUP BY productId")
    fun getProductByCategory(categoryId: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE productId = :id")
    fun getProductById(id: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId GROUP BY productId LIMIT :limit")
    fun getProductByCategoryWithLimit(categoryId: Int, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId AND typeName LIKE :type_name GROUP BY productId LIMIT :limit")
    fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities where favorite = 1 GROUP BY productId")
    fun getFavoriteProducts(): DataSource.Factory<Int, ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(product: List<ProductEntity>)

    @Update
    fun updateProduct(product: ProductEntity)

    //-------------------------------------------------------------------------------------
    //Category Section
    @Query("SELECT * FROM category_entities ORDER BY id ASC")
    fun getAllCategory(): DataSource.Factory<Int, CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(category: List<CategoryEntity>)

    @Update
    fun updateCategory(category: CategoryEntity)
}