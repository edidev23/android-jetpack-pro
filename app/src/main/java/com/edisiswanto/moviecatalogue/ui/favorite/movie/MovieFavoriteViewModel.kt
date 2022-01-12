package com.edisiswanto.moviecatalogue.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity

class MovieFavoriteViewModel constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {

    fun getMoviesBookmark(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getBookmarkedMovie()
}