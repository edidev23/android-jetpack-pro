package com.edisiswanto.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.local.room.MoviesDao
import com.edisiswanto.moviecatalogue.utils.SortUtils
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val mMoviesDao: MoviesDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MoviesDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getMovieSort(sort: String): DataSource.Factory<Int, MovieEntity> {
        val query = SortUtils.getSortedMovieQuery(sort)
        return mMoviesDao.getMovieSort(query)
    }

    fun getTvSort(sort: String): DataSource.Factory<Int, TvEntity> {
        val query = SortUtils.getSortedTvQuery(sort)
        return mMoviesDao.getTvSort(query)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMoviesDao.getMovies()

    fun getBookmarkedMovies(): DataSource.Factory<Int, MovieEntity> = mMoviesDao.getBookmarkedMovies()

    fun setMovieBookmark(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        mMoviesDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>) = mMoviesDao.insertMovies(movies)

    fun getAllTvShow(): DataSource.Factory<Int, TvEntity> = mMoviesDao.getTv()

    fun getBookmarkedTv(): DataSource.Factory<Int, TvEntity> = mMoviesDao.getBookmarkedTv()

    fun setTvBookmark(tvShow: TvEntity, newState: Boolean) {
        tvShow.bookmarked = newState
        mMoviesDao.updateTv(tvShow)
    }

    fun insertTv(tvShow: List<TvEntity>) = mMoviesDao.insertTv(tvShow)

    fun getDetailMovie(movieId: Int) : LiveData<MovieEntity> = mMoviesDao.getDetailMovieById(movieId)

    fun getDetailTvShow(tvShowId: Int) : LiveData<TvEntity> = mMoviesDao.getDetailTvShowById(tvShowId)

}