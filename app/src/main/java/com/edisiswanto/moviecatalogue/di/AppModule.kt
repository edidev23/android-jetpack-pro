package com.edisiswanto.moviecatalogue.di

import android.app.Application
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.LocalDataSource
import com.edisiswanto.moviecatalogue.data.source.local.room.MoviesDao
import com.edisiswanto.moviecatalogue.data.source.local.room.MoviesDatabase
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.utils.AppExecutors
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideMoviesDatabase(application: Application): MoviesDatabase =
            MoviesDatabase.getInstance(application)

        @Singleton
        @Provides
        fun provideCatalogDao(moviesDatabase: MoviesDatabase): MoviesDao =
            moviesDatabase.moviesDao()

        @Singleton
        @Provides
        fun provideLocalDataSource(moviesDao: MoviesDao): LocalDataSource =
            LocalDataSource(moviesDao)

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogueRepository = CatalogueRepository(remoteDataSource, localDataSource, appExecutors)

        @Singleton
        @Provides
        fun provideViewModelFactory(catalogRepository: CatalogueRepository): ViewModelFactory =
            ViewModelFactory(catalogRepository)
    }
}