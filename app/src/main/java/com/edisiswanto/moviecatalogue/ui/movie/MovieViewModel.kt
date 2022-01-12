package com.edisiswanto.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.vo.Resource

class MovieViewModel constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogueRepository.getMovieDiscover()
}