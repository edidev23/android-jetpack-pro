package com.edisiswanto.moviecatalogue.ui.detailTV

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository

class DetailTvViewModel(private val catalogRepository: CatalogueRepository) : ViewModel() {

    val tvId = MutableLiveData<Int>()

    fun setSelectedTv(tvId: Int) {
        this.tvId.value = tvId
    }

    var detailTv: LiveData<TvEntity> = Transformations.switchMap(tvId) { tvId ->
        catalogRepository.getTvShowDetail(tvId)
    }

    fun setBookmark() {
        val tvDetail = detailTv.value
        if (tvDetail != null) {
            val newState = !tvDetail.bookmarked
            catalogRepository.setTvBookmark(tvDetail, newState)
        }
    }
}