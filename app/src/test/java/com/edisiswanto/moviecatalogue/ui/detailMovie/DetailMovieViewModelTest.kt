package com.edisiswanto.moviecatalogue.ui.detailMovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.LiveDataTestUtil
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.FakeCatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.LocalDataSource
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.utils.AppExecutors
import com.edisiswanto.moviecatalogue.utils.DataDummy
import com.edisiswanto.moviecatalogue.vo.Resource
import junit.framework.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val movie = DataDummy.generateDummyMovies()[0]
    private val movieId = movie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovieDetail() {
        val detailMovie = MutableLiveData<MovieEntity>()
        detailMovie.value = movie

        Mockito.`when`(catalogueRepository.getMovieDetail(movie.id)).thenReturn(detailMovie)

        Assert.assertNotNull(detailMovie)
        assertEquals(movie.id, detailMovie?.value?.id)
        assertEquals(movie.title, detailMovie?.value?.title)
        assertEquals(movie.overview, detailMovie?.value?.overview)
        assertEquals(movie.relesaseDate, detailMovie?.value?.relesaseDate)
        assertEquals(movie.voteAverage, detailMovie?.value?.voteAverage)
        assertEquals(movie.posterPath, detailMovie?.value?.posterPath)

        viewModel.detailMovie.observeForever(observer)
        Mockito.verify(observer).onChanged(movie)

    }
}