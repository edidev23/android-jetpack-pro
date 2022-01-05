package com.edisiswanto.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.data.TvEntity

interface CatalogueDataSource {
    fun getMovieDiscover(): LiveData<List<MovieEntity>>

    fun getTvDiscover(): LiveData<List<TvEntity>>
}