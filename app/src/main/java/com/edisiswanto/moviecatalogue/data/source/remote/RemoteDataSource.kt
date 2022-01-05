package com.edisiswanto.moviecatalogue.data.source.remote

import com.edisiswanto.moviecatalogue.data.source.remote.api.ApiConfig
import com.edisiswanto.moviecatalogue.data.source.remote.response.MovieDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.response.TvDiscover
import com.edisiswanto.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getMovieDiscover(
        callback: LoadMoviesCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getMovieDiscover().await().results.let { listMovie ->
            callback.onAllMoviesReceived(
                listMovie
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieDiscover>)
    }

    suspend fun getTvDiscover(
        callback: LoadTvShowCallback
    ) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getTvDiscover().await().results.let { listTvShow ->
            callback.onAllTvShowReceived(
                listTvShow
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: List<TvDiscover>)
    }
}