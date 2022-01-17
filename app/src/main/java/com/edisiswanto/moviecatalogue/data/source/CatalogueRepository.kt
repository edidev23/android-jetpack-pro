package com.edisiswanto.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.data.source.local.LocalDataSource
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.data.source.remote.response.MovieDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.response.TvDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.vo.ApiResponse
import com.edisiswanto.moviecatalogue.utils.AppExecutors
import com.edisiswanto.moviecatalogue.vo.Resource
import javax.inject.Inject

class CatalogueRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CatalogueDataSource {

    override fun getMovieSort(sort: String): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getMovieSort(sort), config).build()
    }

    override fun getTvSort(sort: String): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getTvSort(sort), config).build()
    }

    override fun getMovieDiscover(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieDiscover>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieDiscover>>> =
                remoteDataSource.getMovies()

            public override fun saveCallResult(data: List<MovieDiscover>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
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

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvDiscover(): LiveData<Resource<PagedList<TvEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvEntity>, List<TvDiscover>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<TvDiscover>>> =
                remoteDataSource.getTvShow()

            public override fun saveCallResult(data: List<TvDiscover>) {
                val tvList = ArrayList<TvEntity>()
                for (response in data) {
                    val tvShow = TvEntity(
                        response.id,
                        response.name,
                        response.overview,
                        response.firstAirDate,
                        response.voteAverage,
                        response.posterPath
                    )
                    tvList.add(tvShow)
                }

                localDataSource.insertTv(tvList)
            }
        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        localDataSource.getDetailMovie(movieId)

    override fun getTvShowDetail(tvId: Int): LiveData<TvEntity> =
        localDataSource.getDetailTvShow(tvId)

    override fun getBookmarkedMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedMovies(), config).build()
    }

    override fun setMovieBookmark(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movie, state) }

    override fun getBookmarkedTv(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedTv(), config).build()
    }

    override fun setTvBookmark(tvShow: TvEntity, state: Boolean)  =
        appExecutors.diskIO().execute { localDataSource.setTvBookmark(tvShow, state) }

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

}