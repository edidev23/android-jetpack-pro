package com.edisiswanto.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.vo.Resource

class TvViewModel constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<TvEntity>>> = catalogueRepository.getTvDiscover()
}