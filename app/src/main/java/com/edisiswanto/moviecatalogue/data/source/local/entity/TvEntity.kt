package com.edisiswanto.moviecatalogue.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tventities")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double,

    @ColumnInfo(name = "posterPath")
    var posterPath: String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
): Parcelable
