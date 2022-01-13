package com.edisiswanto.moviecatalogue.ui.detailMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository

class DetailMovieViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {

    private val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var detailMovie: LiveData<MovieEntity> = Transformations.switchMap(movieId) { movieId ->
        catalogRepository.getMovieDetail(movieId)
    }

    fun setBookmark() {
        val movieDetail = detailMovie.value
        if (movieDetail != null) {
                val newState = !movieDetail.bookmarked
                catalogRepository.setMovieBookmark(movieDetail, newState)
        }
    }
}