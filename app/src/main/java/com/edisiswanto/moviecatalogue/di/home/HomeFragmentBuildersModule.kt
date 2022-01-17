package com.edisiswanto.moviecatalogue.di.home

import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.ui.tv.TvFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieFragment() : MovieFragment

    @ContributesAndroidInjector
    abstract fun contributeTvShowFragment() : TvFragment

}