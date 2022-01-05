package com.edisiswanto.moviecatalogue.data.source.remote.api

import com.edisiswanto.moviecatalogue.BuildConfig
import com.edisiswanto.moviecatalogue.data.source.remote.response.MovieDiscoverResponse
import com.edisiswanto.moviecatalogue.data.source.remote.response.TvDiscoverResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("discover/tv")
    fun getTvDiscover(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("sort_by") sort_by: String = "popularity.desc"
    ): Call<TvDiscoverResponse>

    @GET("discover/movie")
    fun getMovieDiscover(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("sort_by") sort_by: String = "popularity.desc"
    ): Call<MovieDiscoverResponse>
}
