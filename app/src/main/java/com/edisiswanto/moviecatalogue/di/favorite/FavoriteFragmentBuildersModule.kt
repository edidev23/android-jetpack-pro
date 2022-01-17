package com.edisiswanto.moviecatalogue.di.favorite

import com.edisiswanto.moviecatalogue.ui.favorite.movie.MovieFavoriteFragment
import com.edisiswanto.moviecatalogue.ui.favorite.tv.TvFavoriteFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFavoriteFragment() : MovieFavoriteFragment

    @ContributesAndroidInjector
    abstract fun contributeTvFavoriteFragment() : TvFavoriteFragment

}