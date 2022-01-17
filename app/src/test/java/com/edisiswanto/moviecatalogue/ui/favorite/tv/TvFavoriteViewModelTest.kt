package com.edisiswanto.moviecatalogue.ui.favorite.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class TvFavoriteViewModelTest {
    private lateinit var viewModel: TvFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvEntity>>

    @Mock
    private lateinit var tvPagedList: PagedList<TvEntity>

    @Before
    fun setUp() {
        viewModel = TvFavoriteViewModel(catalogRepository)
    }

    @Test
    fun getBookmarkedTv() {
        Mockito.`when`(tvPagedList.size).thenReturn(5)
        val tvDummy = MutableLiveData<PagedList<TvEntity>>()
        tvDummy.value = tvPagedList

        Mockito.`when`(catalogRepository.getBookmarkedTv()).thenReturn(tvDummy)
        val tvEntity = viewModel.getBookmarkedTv().value
        Mockito.verify(catalogRepository).getBookmarkedTv()
        assertNotNull(tvEntity)
        assertEquals(5, tvEntity?.size)

        viewModel.getBookmarkedTv().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(tvPagedList)
    }
}