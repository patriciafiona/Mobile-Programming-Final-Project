package com.path_studio.nike.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.path_studio.nike.data.source.local.entity.CartEntity
import com.path_studio.nike.data.source.local.entity.CategoryEntity
import com.path_studio.nike.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class, CategoryEntity::class, CartEntity::class],
    version = 1,
    exportSchema = false)
abstract class NikeDatabase : RoomDatabase() {
    abstract fun nikeDao(): NikeDao

    companion object {

        @Volatile
        private var INSTANCE: NikeDatabase? = null

        fun getInstance(context: Context): NikeDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    NikeDatabase::class.java,
                    "Nike.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}