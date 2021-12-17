package com.path_studio.nike.data.source.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.path_studio.nike.data.source.local.entity.CartDetailEntity
import com.path_studio.nike.data.source.local.entity.CartEntity
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

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId GROUP BY productId ORDER BY rating")
    fun getProductByCategory(categoryId: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE productId = :id")
    fun getProductById(id: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId GROUP BY productId ORDER BY rating DESC LIMIT :limit")
    fun getProductByCategoryWithLimit(categoryId: Int, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities WHERE categoryId = :categoryId AND typeName LIKE :type_name GROUP BY productId ORDER BY updated_at DESC LIMIT :limit")
    fun getProductsByCategoryAndTypeWithLimit(categoryId: Int, type_name: String, limit: Int): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT * FROM product_entities where favorite = 1")
    fun getFavoriteProducts(): DataSource.Factory<Int, ProductEntity>

    @Query("SELECT productDetailId FROM product_entities where favorite = 1")
    fun getFavoriteProductsDetailId(): DataSource.Factory<Int, Int>

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

    //-------------------------------------------------------------------------------------
    //Category Section
    @Query("SELECT DISTINCT cart_entities.id, cart_entities.product_detail_id AS productDetailId, cart_entities.quantity, " +
            "cart_entities.size, product_entities.name, product_entities.productId, product_entities.typeName, " +
            "product_entities.categoryName, product_entities.price, product_entities.colorId, product_entities.colorCode, product_entities.colorDescription, " +
            "product_entities.photo01, product_entities.stock, product_entities.discount " +
            "FROM cart_entities INNER JOIN product_entities ON cart_entities.product_detail_id = product_entities.productDetailId ")
    fun getShoppingCart(): DataSource.Factory<Int, CartDetailEntity>

    @Query("SELECT DISTINCT cart_entities.id, cart_entities.product_detail_id AS productDetailId, cart_entities.quantity, " +
            "cart_entities.size, product_entities.name, product_entities.productId, product_entities.typeName, " +
            "product_entities.categoryName, product_entities.price, product_entities.colorId, product_entities.colorCode, product_entities.colorDescription, " +
            "product_entities.photo01, product_entities.stock, product_entities.discount " +
            "FROM cart_entities INNER JOIN product_entities ON product_entities.productDetailId = cart_entities.product_detail_id " +
            "WHERE cart_entities.product_detail_id = :productDetailId")
    fun getProductInCartById(productDetailId: Int): DataSource.Factory<Int, CartDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartProduct(product: List<CartEntity>)

    @Update
    fun updateCartProduct(product: CartEntity)

    @Query("DELETE FROM cart_entities WHERE id = :id")
    fun deleteCartProduct(id: Int)
}