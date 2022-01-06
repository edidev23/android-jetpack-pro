package com.edisiswanto.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.TvEntity
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
class TvViewModelTest {
    private val dummyTv = DataDummy.generateDummyTv()

    private lateinit var viewModel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TvEntity>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(catalogRepository)
    }

    @Test
    fun getTvDiscover() {
        val tvShow = MutableLiveData<List<TvEntity>>()
        tvShow.value = dummyTv

        Mockito.`when`(catalogRepository.getTvDiscover()).thenReturn(tvShow)

        val dataListTv = viewModel.getTv().value

        verify(catalogRepository).getTvDiscover()
        Assert.assertNotNull(dataListTv)
        Assert.assertEquals(10, dataListTv?.size)

        viewModel.getTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}