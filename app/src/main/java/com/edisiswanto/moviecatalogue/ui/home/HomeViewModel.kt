package com.edisiswanto.moviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity

class HomeViewModel constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getMovieSort(sort: String): LiveData<PagedList<MovieEntity>> = catalogueRepository.getMovieSort(sort)
}