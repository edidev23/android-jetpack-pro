package com.edisiswanto.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity

@Dao
interface MoviesDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovieSort(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities where bookmarked = 1")
    fun getBookmarkedMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tventities")
    fun getTv(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tventities where bookmarked = 1")
    fun getBookmarkedTv(): DataSource.Factory<Int, TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: List<TvEntity>)

    @Update
    fun updateTv(tv: TvEntity)

    @Query("SELECT * FROM movieentities WHERE id = :movieId")
    fun getDetailMovieById(movieId: Int) : LiveData<MovieEntity>

    @Query("SELECT * FROM tventities WHERE id = :tvShowId")
    fun getDetailTvShowById(tvShowId: Int) : LiveData<TvEntity>
}