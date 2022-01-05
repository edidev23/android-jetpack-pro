package com.edisiswanto.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.data.TvEntity
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.data.source.remote.response.MovieDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.response.TvDiscover
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FakeCatalogueRepository (private val remoteDataSource: RemoteDataSource) : CatalogueDataSource {
    override fun getMovieDiscover(): LiveData<List<MovieEntity>> {
        val listMovieResult = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDiscover(object : RemoteDataSource.LoadMoviesCallback{
                override fun onAllMoviesReceived(movieResponse: List<MovieDiscover>) {
                    val movieList = ArrayList<MovieEntity>()
                    for (response in movieResponse){
                        val movie = MovieEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.releaseDate,
                            response.voteAverage,
                            response.posterPath
                        )

                        movieList.add(movie)
                    }
                    listMovieResult.postValue(movieList)
                }
            })
        }

        return listMovieResult
    }

    override fun getTvDiscover(): LiveData<List<TvEntity>> {
        val listTvResult = MutableLiveData<List<TvEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvDiscover(object : RemoteDataSource.LoadTvShowCallback{
                override fun onAllTvShowReceived(tvResponse: List<TvDiscover>) {
                    val movieList = ArrayList<TvEntity>()
                    for (response in tvResponse){
                        val movie = TvEntity(
                            response.id,
                            response.name,
                            response.overview,
                            response.firstAirDate,
                            response.voteAverage,
                            response.posterPath
                        )

                        movieList.add(movie)
                    }
                    listTvResult.postValue(movieList)
                }
            })
        }

        return listTvResult
    }

}