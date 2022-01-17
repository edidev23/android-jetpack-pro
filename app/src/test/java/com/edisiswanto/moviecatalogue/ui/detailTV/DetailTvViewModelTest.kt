package com.edisiswanto.moviecatalogue.ui.detailTV

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.utils.DataDummy
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {
    private lateinit var viewModel: DetailTvViewModel
    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tvId = dummyTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<TvEntity>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(catalogueRepository)
        viewModel.setSelectedTv(tvId)
    }

    @Test
    fun getTvShowDetail() {
        val detailTv = MutableLiveData<TvEntity>()
        detailTv.value = dummyTv

        Mockito.`when`(catalogueRepository.getTvShowDetail(dummyTv.id)).thenReturn(detailTv)

        Assert.assertNotNull(detailTv)
        assertEquals(dummyTv.id, detailTv.value?.id)
        assertEquals(dummyTv.name, detailTv.value?.name)
        assertEquals(dummyTv.overview, detailTv.value?.overview)
        assertEquals(dummyTv.firstAirDate, detailTv.value?.firstAirDate)
        assertEquals(dummyTv.voteAverage, detailTv.value?.voteAverage)
        assertEquals(dummyTv.posterPath, detailTv.value?.posterPath)

        viewModel.detailTv.observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTv)
    }
}