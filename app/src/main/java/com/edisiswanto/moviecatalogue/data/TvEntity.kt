package com.edisiswanto.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvEntity(
    val tvId: Int,
    val name: String,
    val overview: String,
    val first_air_date: String,
    val userScore: Double,
    val imagePath: String
): Parcelable
