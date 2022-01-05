package com.edisiswanto.moviecatalogue.di

import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {

    fun provideCatalogRepository(): CatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogueRepository.getInstance(remoteDataSource)
    }
}