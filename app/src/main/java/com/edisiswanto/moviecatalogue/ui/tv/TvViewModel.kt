package com.edisiswanto.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.vo.Resource

class TvViewModel constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    private val sort = MutableLiveData<String>()

    fun setSort(sort: String) {
        this.sort.value = sort
    }

    var tvSort: LiveData<PagedList<TvEntity>> = Transformations.switchMap(sort) { sort ->
        catalogueRepository.getTvSort(sort)
    }

    fun getTvShow(): LiveData<Resource<PagedList<TvEntity>>> = catalogueRepository.getTvDiscover()
}