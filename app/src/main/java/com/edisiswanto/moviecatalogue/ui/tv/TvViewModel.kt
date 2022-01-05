package com.edisiswanto.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edisiswanto.moviecatalogue.data.TvEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository

class TvViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTv(): LiveData<List<TvEntity>> = catalogueRepository.getTvDiscover()
}