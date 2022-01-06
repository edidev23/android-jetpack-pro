package com.edisiswanto.moviecatalogue.ui.detailTV

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.TvEntity
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
class DetailTvViewModelTest {
    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tvId = dummyTv.id

    private lateinit var viewModel: DetailTvViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvEntity>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(catalogRepository)
    }

    @Test
    fun getTvDetail() {
        val tvDummy = MutableLiveData<TvEntity>()
        tvDummy.value = dummyTv

        Mockito.`when`(catalogRepository.getTvShowDetail(tvId)).thenReturn(tvDummy)

        val tvData = viewModel.getTvShowDetail(tvId).value as TvEntity

        Assert.assertNotNull(tvData)
        assertEquals(dummyTv.id, tvData.id)
        assertEquals(dummyTv.name, tvData.name)
        assertEquals(dummyTv.overview, tvData.overview)
        assertEquals(dummyTv.firstAirDate, tvData.firstAirDate)
        assertEquals(dummyTv.voteAverage, tvData.voteAverage, 0.0)
        assertEquals(dummyTv.posterPath, tvData.posterPath)

        viewModel.getTvShowDetail(tvId).observeForever(observer)
        verify(observer).onChanged(dummyTv)

    }
}