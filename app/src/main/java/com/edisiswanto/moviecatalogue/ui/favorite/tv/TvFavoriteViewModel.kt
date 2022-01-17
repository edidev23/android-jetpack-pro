package com.edisiswanto.moviecatalogue.ui.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import javax.inject.Inject

class TvFavoriteViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getBookmarkedTv(): LiveData<PagedList<TvEntity>> = catalogueRepository.getBookmarkedTv()

    fun setBookmark(tvShow: TvEntity) {
        val newState = !tvShow.bookmarked
        catalogueRepository.setTvBookmark(tvShow, newState)
    }
}