package com.edisiswanto.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.edisiswanto.moviecatalogue.LiveDataTestUtil
import com.edisiswanto.moviecatalogue.PagedListUtil
import com.edisiswanto.moviecatalogue.data.source.local.LocalDataSource
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.utils.AppExecutors
import com.edisiswanto.moviecatalogue.utils.DataDummy
import com.edisiswanto.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val catalogRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val listMovie = DataDummy.generateDummyMovies()
    private val listTvShow = DataDummy.generateDummyTv()
    private val movie = DataDummy.generateDummyMovies()[0]
    private val tvShow = DataDummy.generateDummyTv()[0]

    @Test
    fun getMovieDiscover() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getMovieDiscover()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        Mockito.verify(local).getAllMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getTvDiscover() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        catalogRepository.getTvDiscover()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
        Mockito.verify(local).getAllTvShow()
        org.junit.Assert.assertNotNull(tvEntity.data)
        assertEquals(listTvShow.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = movie
        Mockito.`when`(local.getDetailMovie(movie.id)).thenReturn(dummyMovie)

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movie.id))
        Mockito.verify(local).getDetailMovie(movie.id)
        org.junit.Assert.assertNotNull(data)
        assertEquals(movie.id, data.id)
    }

    @Test
    fun getTvShowDetail() {
        val dummyTV = MutableLiveData<TvEntity>()
        dummyTV.value = tvShow
        Mockito.`when`(local.getDetailTvShow(tvShow.id)).thenReturn(dummyTV)

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShow.id))
        Mockito.verify(local).getDetailTvShow(tvShow.id)
        org.junit.Assert.assertNotNull(data)
        assertEquals(tvShow.id, data.id)
    }

    @Test
    fun getBookmarkedMovie() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getBookmarkedMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getBookmarkedMovie()

        val movieEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        Mockito.verify(local).getBookmarkedMovies()
        org.junit.Assert.assertNotNull(movieEntity.data)
        assertEquals(listMovie.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getBookmarkedTv() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getBookmarkedTv()).thenReturn(dataSourceFactory)
        catalogRepository.getBookmarkedTv()

        val tvEntity = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
        Mockito.verify(local).getBookmarkedTv()
        org.junit.Assert.assertNotNull(tvEntity.data)
        assertEquals(listTvShow.size.toLong(), tvEntity.data?.size?.toLong())
    }
}