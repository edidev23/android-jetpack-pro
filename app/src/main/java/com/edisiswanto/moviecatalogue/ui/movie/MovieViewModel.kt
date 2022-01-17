package com.edisiswanto.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.vo.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

     private val sort = MutableLiveData<String>()

    fun setSort(sort: String) {
        this.sort.value = sort
    }

    var movieSort: LiveData<PagedList<MovieEntity>> = Transformations.switchMap(sort) { sort ->
        catalogueRepository.getMovieSort(sort)
    }

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogueRepository.getMovieDiscover()
}