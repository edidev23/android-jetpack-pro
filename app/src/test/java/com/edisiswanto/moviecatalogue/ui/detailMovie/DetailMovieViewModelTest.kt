package com.edisiswanto.moviecatalogue.ui.detailMovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

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
    fun detailMovie() {

        viewModel.detailMovie.observeForever(observer)

        val detailMovie = MutableLiveData<MovieEntity>()
        detailMovie.value = dummyMovie

        val expectedValue = detailMovie.value
        val actualValue = viewModel.detailMovie.value

        assertEquals(expectedValue, actualValue)
    }
}