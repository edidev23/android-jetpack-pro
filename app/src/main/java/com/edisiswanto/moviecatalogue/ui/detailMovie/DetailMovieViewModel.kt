package com.edisiswanto.moviecatalogue.ui.detailMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository

class DetailMovieViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        catalogRepository.getMovieDetail(movieId)

}