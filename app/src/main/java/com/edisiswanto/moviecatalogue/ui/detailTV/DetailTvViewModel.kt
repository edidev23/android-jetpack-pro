package com.edisiswanto.moviecatalogue.ui.detailTV

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository

class DetailTvViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {

    fun getTvShowDetail(tvId: Int): LiveData<TvEntity> =
        catalogRepository.getTvShowDetail(tvId)

}