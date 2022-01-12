package com.edisiswanto.moviecatalogue.di

import android.content.Context
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.LocalDataSource
import com.edisiswanto.moviecatalogue.data.source.local.room.MoviesDatabase
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): CatalogueRepository {

        val database = MoviesDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.moviesDao())
        val appExecutors = AppExecutors()

        return CatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}