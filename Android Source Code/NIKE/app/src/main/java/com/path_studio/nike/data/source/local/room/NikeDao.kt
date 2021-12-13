package com.path_studio.nike.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.path_studio.nike.data.source.local.entity.*

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

    //-------------------------------------------------------------------------------------
    //Category Section
    @Query("SELECT cart_entities.id, cart_entities.product_detail_id AS productDetailId, cart_entities.quantity, " +
            "cart_entities.size, product_entities.name, product_entities.productId, product_entities.typeName, " +
            "product_entities.categoryName, product_entities.price, product_entities.colorCode, product_entities.photo01 " +
            "FROM cart_entities INNER JOIN product_entities ON product_entities.productDetailId = cart_entities.product_detail_id")
    fun getShoppingCart(): DataSource.Factory<Int, CartDetailEntity>

    @Query("SELECT cart_entities.id, cart_entities.product_detail_id AS productDetailId, cart_entities.quantity, " +
            "cart_entities.size, product_entities.name, product_entities.productId, product_entities.typeName, " +
            "product_entities.categoryName, product_entities.price, product_entities.colorCode, product_entities.photo01 " +
            "FROM cart_entities INNER JOIN product_entities ON product_entities.productDetailId = cart_entities.product_detail_id " +
            "WHERE cart_entities.product_detail_id = :productDetailId")
    fun getProductInCartById(productDetailId: Int): DataSource.Factory<Int, CartDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCartProduct(product: List<CartEntity>)

    @Update
    fun updateCartProduct(product: CartEntity)

    @Query("DELETE FROM cart_entities WHERE id = :id")
    fun deleteCartProduct(id: Int)

    //-------------------------------------------------------------------------------------
    //User Section
    @Query("SELECT * FROM user_entities")
    fun getUserData(): DataSource.Factory<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserData(data: List<UserEntity>)

    @Update
    fun updateUserData(data: UserEntity)

    @Query("DELETE FROM user_entities WHERE id = :userId")
    fun deleteUserData(userId: Int)
}