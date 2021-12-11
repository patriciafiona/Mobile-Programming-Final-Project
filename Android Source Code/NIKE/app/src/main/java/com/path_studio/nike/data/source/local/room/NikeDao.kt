package com.path_studio.nike.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity

@Dao
interface NikeDao {

    //-------------------------------------------------------------------------------------
    //Product Section
    @Query("SELECT * FROM product_entities ORDER BY id ASC")
    fun getAllProduct(): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId ORDER BY updated_at DESC LIMIT :limit")
    fun getLatestProductWithLimit(categoryId: Int, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId")
    fun getProductByCategory(categoryId: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId LIMIT :limit")
    fun getProductByCategoryWithLimit(categoryId: Int, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId AND typeName LIKE :type_name LIMIT :limit")
    fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities where favorite = 1")
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