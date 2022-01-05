package com.edisiswanto.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {

    fun getMovies(): LiveData<List<MovieEntity>> = catalogueRepository.getMovieDiscover()
}