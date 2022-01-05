package com.edisiswanto.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val title: String,
    val overview: String,
    val relesaseDate: String,
    val voteAverage: Double,
    val posterPath: String
) : Parcelable
