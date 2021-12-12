package com.path_studio.nike.di

import android.content.Context
import com.path_studio.nike.data.NikeRepository
import com.path_studio.nike.data.source.local.LocalDataSource
import com.path_studio.nike.data.source.local.room.NikeDatabase
import com.path_studio.nike.data.source.remote.RemoteDataSource
import com.path_studio.nike.utils.AppExecutors

object Injection {
    fun provideNikeRepository(context: Context): NikeRepository {
        val database = NikeDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.nikeDao())
        val appExecutors = AppExecutors()

        return NikeRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}