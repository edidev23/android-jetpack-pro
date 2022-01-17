package com.edisiswanto.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {
    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(catalogRepository)
    }

    @Test
    fun getMoviesBookmark() {
        Mockito.`when`(moviePagedList.size).thenReturn(5)
        val movieDummy = MutableLiveData<PagedList<MovieEntity>>()
        movieDummy.value = moviePagedList

        Mockito.`when`(catalogRepository.getBookmarkedMovie()).thenReturn(movieDummy)
        val movieEntity = viewModel.getMoviesBookmark().value
        Mockito.verify(catalogRepository).getBookmarkedMovie()
        assertNotNull(movieEntity)
        assertEquals(5, movieEntity?.size)

        viewModel.getMoviesBookmark().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(moviePagedList)
    }
}