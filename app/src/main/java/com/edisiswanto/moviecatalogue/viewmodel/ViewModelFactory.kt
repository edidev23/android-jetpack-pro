package com.edisiswanto.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edisiswanto.moviecatalogue.data.source.CatalogueRepository
import com.edisiswanto.moviecatalogue.di.Injection
import com.edisiswanto.moviecatalogue.ui.detailMovie.DetailMovieViewModel
import com.edisiswanto.moviecatalogue.ui.detailTV.DetailTvViewModel
import com.edisiswanto.moviecatalogue.ui.movie.MovieViewModel
import com.edisiswanto.moviecatalogue.ui.tv.TvViewModel

class ViewModelFactory private constructor(private val mCatalogRepository: CatalogueRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mCatalogRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                DetailTvViewModel(mCatalogRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}