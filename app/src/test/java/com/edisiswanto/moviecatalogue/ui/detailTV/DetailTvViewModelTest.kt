package com.edisiswanto.moviecatalogue.ui.detailTV

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
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
    fun detailMovie() {

        viewModel.detailTv.observeForever(observer)

        val detailTv = MutableLiveData<TvEntity>()
        detailTv.value = dummyTv

        val expectedValue = detailTv.value
        val actualValue = viewModel.detailTv.value

        assertEquals(expectedValue, actualValue)
    }
}