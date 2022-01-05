package com.edisiswanto.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val movieId: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val userScore: Double,
    val imagePath: String
) : Parcelable
