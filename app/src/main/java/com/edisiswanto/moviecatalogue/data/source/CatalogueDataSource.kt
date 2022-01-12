package com.edisiswanto.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getMovieDiscover(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvDiscover(): LiveData<Resource<PagedList<TvEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvId: Int): LiveData<TvEntity>

}