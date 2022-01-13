package com.edisiswanto.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
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
class TvViewModelTest {
    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observerTv: Observer<Resource<PagedList<TvEntity>>>

    @Mock
    private lateinit var tvPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = TvViewModel(catalogRepository)
    }

    @Test
    fun getTvDiscover() {
        val dummyTv = Resource.success(tvPagedList)
        Mockito.`when`(dummyTv.data?.size).thenReturn(5)
        val tvShow = MutableLiveData<Resource<PagedList<TvEntity>>>()
        tvShow.value = dummyTv

        Mockito.`when`(catalogRepository.getTvDiscover()).thenReturn(tvShow)
        val movieEntity = viewModel.getTvShow().value?.data
        Mockito.verify(catalogRepository).getTvDiscover()
        org.junit.Assert.assertNotNull(movieEntity)
        org.junit.Assert.assertEquals(5, movieEntity?.size)

        viewModel.getTvShow().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummyTv)
    }
}