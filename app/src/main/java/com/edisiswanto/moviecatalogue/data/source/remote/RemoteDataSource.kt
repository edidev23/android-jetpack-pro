package com.edisiswanto.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edisiswanto.moviecatalogue.data.source.remote.api.ApiConfig
import com.edisiswanto.moviecatalogue.data.source.remote.response.MovieDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.response.TvDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.vo.ApiResponse
import com.edisiswanto.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
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

    fun getMovies(): LiveData<ApiResponse<List<MovieDiscover>>> {
        EspressoIdlingResource.increment()
        val resultMovieResponse = MutableLiveData<ApiResponse<List<MovieDiscover>>>()
        val client = ApiConfig.getApiService()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = client.getMovieDiscover().await()
                resultMovieResponse.postValue(ApiResponse.success(response.results!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultMovieResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultMovieResponse
    }

    fun getTvShow(): LiveData<ApiResponse<List<TvDiscover>>> {
        EspressoIdlingResource.increment()
        val resultTVResponse = MutableLiveData<ApiResponse<List<TvDiscover>>>()
        val client = ApiConfig.getApiService()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = client.getTvDiscover().await()
                resultTVResponse.postValue(ApiResponse.success(response.results!!))
            } catch (e: IOException) {
                e.printStackTrace()
                resultTVResponse.postValue(
                    ApiResponse.error(
                        e.message.toString(),
                        mutableListOf()
                    )
                )
            }
        }
        EspressoIdlingResource.decrement()
        return resultTVResponse
    }
}