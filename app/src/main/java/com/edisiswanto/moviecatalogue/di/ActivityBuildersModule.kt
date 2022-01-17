package com.edisiswanto.moviecatalogue.di

import com.edisiswanto.moviecatalogue.di.favorite.FavoriteFragmentBuildersModule
import com.edisiswanto.moviecatalogue.di.home.HomeFragmentBuildersModule
import com.edisiswanto.moviecatalogue.ui.detailMovie.DetailMovieActivity
import com.edisiswanto.moviecatalogue.ui.detailTV.DetailTvActivity
import com.edisiswanto.moviecatalogue.ui.favorite.FavoriteActivity
import com.edisiswanto.moviecatalogue.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [HomeFragmentBuildersModule::class])
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [FavoriteFragmentBuildersModule::class])
    abstract fun contributeFavoriteActivity(): FavoriteActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailMovieActivity(): DetailMovieActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailTvActivity(): DetailTvActivity

}