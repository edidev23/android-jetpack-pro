package com.edisiswanto.moviecatalogue.ui.detailMovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
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
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogRepository)
    }

    @Test
    fun getMovieDetail() {
        val movieDummy = MutableLiveData<MovieEntity>()
        movieDummy.value = dummyMovie

        Mockito.`when`(catalogRepository.getMovieDetail(movieId)).thenReturn(movieDummy)

        val movieData = viewModel.getMovieDetail(movieId).value as MovieEntity

        Assert.assertNotNull(movieData)
        assertEquals(dummyMovie.id, movieData.id)
        assertEquals(dummyMovie.title, movieData.title)
        assertEquals(dummyMovie.overview, movieData.overview)
        assertEquals(dummyMovie.relesaseDate, movieData.relesaseDate)
        assertEquals(dummyMovie.voteAverage, movieData.voteAverage, 0.0)
        assertEquals(dummyMovie.posterPath, movieData.posterPath)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }
}