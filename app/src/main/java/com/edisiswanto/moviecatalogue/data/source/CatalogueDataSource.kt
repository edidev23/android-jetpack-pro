package com.edisiswanto.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getMovieSort(sort: String): LiveData<PagedList<MovieEntity>>

    fun getMovieDiscover(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvDiscover(): LiveData<Resource<PagedList<TvEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvId: Int): LiveData<TvEntity>

    fun getBookmarkedMovie(): LiveData<PagedList<MovieEntity>>

    fun setMovieBookmark(movie: MovieEntity, state: Boolean)

    fun getBookmarkedTv(): LiveData<PagedList<TvEntity>>

    fun setTvBookmark(movie: TvEntity, state: Boolean)
}