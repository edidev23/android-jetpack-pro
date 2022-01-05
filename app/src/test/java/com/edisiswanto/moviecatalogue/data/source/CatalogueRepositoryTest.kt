package com.edisiswanto.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edisiswanto.moviecatalogue.LiveDataTestUtil
import com.edisiswanto.moviecatalogue.data.source.remote.RemoteDataSource
import com.edisiswanto.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doAnswer

class CatalogueRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val catalogRepository = FakeCatalogueRepository(remote)

    private val listMovieResponse = DataDummy.generateDummyMoviesResponse()
    private val listTvResonse = DataDummy.generateDummyTvResponse()

    private val movieId = listMovieResponse[0].id
    private val tvShowId = listTvResonse[0].id
    private val movieResponse = DataDummy.generateDummyMoviesResponse()[0]
    private val tvShowResponse = DataDummy.generateDummyTvResponse()[0]

    @Test
    fun getMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getMovieDiscover(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovieDiscover())

        runBlocking {
            verify(remote).getMovieDiscover(any())
        }

        Assert.assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getMovieDetail() {
        runBlocking {
            com.nhaarman.mockitokotlin2.doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadMovieDetailCallback).onMovieDetailReceived(
                    movieResponse
                )
                null
            }.`when`(remote).getMovieDetail(eq(movieId), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getMovieDetail(movieId))

        runBlocking {
            verify(remote).getMovieDetail(eq(movieId), any())
        }

        Assert.assertNotNull(data)
        assertEquals(movieResponse.id, data.id)
    }

    @Test
    fun getTvDiscover() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadTvShowCallback).onAllTvShowReceived(
                    listTvResonse
                )
                null
            }.`when`(remote).getTvDiscover(any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvDiscover())

        runBlocking {
            verify(remote).getTvDiscover(any())
        }

        Assert.assertNotNull(data)
        assertEquals(listTvResonse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getTvShowDetail() {
        runBlocking {
            com.nhaarman.mockitokotlin2.doAnswer { invocation ->
                (invocation.arguments[1] as RemoteDataSource.LoadTvShowDetailCallback).onTvShowDetailReceived(
                    tvShowResponse
                )
                null
            }.`when`(remote).getTvShowDetail(eq(tvShowId), any())
        }

        val data = LiveDataTestUtil.getValue(catalogRepository.getTvShowDetail(tvShowId))

        runBlocking {
            verify(remote).getTvShowDetail(eq(tvShowId), any())
        }

        Assert.assertNotNull(data)
        assertEquals(tvShowResponse.id, data.id)
    }
}