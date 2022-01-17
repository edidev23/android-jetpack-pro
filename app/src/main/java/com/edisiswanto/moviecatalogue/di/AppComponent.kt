package com.edisiswanto.moviecatalogue.di

import com.edisiswanto.moviecatalogue.ui.favorite.FavoriteActivity
import com.edisiswanto.moviecatalogue.ui.home.HomeActivity
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class]
)
interface AppComponent {
    fun inject(activity: HomeActivity)
    fun inject(activity: FavoriteActivity)
}