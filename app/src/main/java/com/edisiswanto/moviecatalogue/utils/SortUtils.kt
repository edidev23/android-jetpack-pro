package com.edisiswanto.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"
    fun getSortedMovieQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movieentities ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY id DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY id ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedTvQuery(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tventities ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY id DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY id ASC")
            }
            RANDOM -> {
                simpleQuery.append("ORDER BY RANDOM()")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}