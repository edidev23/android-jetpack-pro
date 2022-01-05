package com.edisiswanto.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private val dummyMovie = DataDummy.generateDummyMovies()

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getMovieDiscover() {
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = dummyMovie as List<MovieEntity>

        Mockito.`when`(catalogRepository.getMovieDiscover()).thenReturn(movie)

        val dataListMovie = viewModel.getMovies().value

        verify(catalogRepository).getMovieDiscover()
        Assert.assertNotNull(dataListMovie)
        Assert.assertEquals(10, dataListMovie?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}