package com.edisiswanto.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun getMovieDiscover() {
        val dummyMovie = Resource.success(moviePagedList)
        Mockito.`when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movie.value = dummyMovie

        Mockito.`when`(catalogRepository.getMovieDiscover()).thenReturn(movie)
        val movieEntity = viewModel.getMovies().value?.data
        Mockito.verify(catalogRepository).getMovieDiscover()
        org.junit.Assert.assertNotNull(movieEntity)
        org.junit.Assert.assertEquals(5, movieEntity?.size)

        viewModel.getMovies().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }
}