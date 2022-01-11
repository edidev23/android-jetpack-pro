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

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource {

    override fun getMovieDiscover(): LiveData<List<MovieEntity>> {
        val listMovieResult = MutableLiveData<List<MovieEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDiscover(object :
                RemoteDataSource.LoadMoviesCallback {
                override fun onAllMoviesReceived(movieResponse: List<MovieDiscover>) {
                    val movieList = ArrayList<MovieEntity>()
                    for (response in movieResponse) {
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

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.LoadMovieDetailCallback{
                override fun onMovieDetailReceived(movieResponse: MovieDiscover) {
                    val movie = MovieEntity(
                        movieResponse.id,
                        movieResponse.title,
                        movieResponse.overview,
                        movieResponse.releaseDate,
                        movieResponse.voteAverage,
                        movieResponse.posterPath
                    )

                    movieResult.postValue(movie)
                }
            })
        }

        return movieResult
    }

    override fun getTvDiscover(): LiveData<List<TvEntity>> {
        val listTvResult = MutableLiveData<List<TvEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvDiscover(object :
                RemoteDataSource.LoadTvShowCallback {
                override fun onAllTvShowReceived(tvShowResponse: List<TvDiscover>) {
                    val tvShowList = ArrayList<TvEntity>()
                    for (response in tvShowResponse) {
                        val tvShow = TvEntity(
                            response.id,
                            response.name,
                            response.overview,
                            response.firstAirDate,
                            response.voteAverage,
                            response.posterPath
                        )
                        tvShowList.add(tvShow)
                    }
                    listTvResult.postValue(tvShowList)
                }
            })
        }

        return listTvResult
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvEntity> {
        val tvShowResult = MutableLiveData<TvEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.LoadTvShowDetailCallback{
                override fun onTvShowDetailReceived(tvShowResponse: TvDiscover) {
                    val tvShow = TvEntity(
                        tvShowResponse.id,
                        tvShowResponse.name,
                        tvShowResponse.overview,
                        tvShowResponse.firstAirDate,
                        tvShowResponse.voteAverage,
                        tvShowResponse.posterPath
                    )

                    tvShowResult.postValue(tvShow)
                }
            })
        }

        return tvShowResult
    }

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteDataSource)
            }
    }

}