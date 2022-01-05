package com.edisiswanto.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvEntity(
    val id: Int,
    val name: String,
    val overview: String,
    val firstAirDate: String,
    val voteAverage: Double,
    val posterPath: String
): Parcelable
