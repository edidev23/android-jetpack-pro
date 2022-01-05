package com.edisiswanto.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvDiscoverResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<TvDiscover>,

	@field:SerializedName("total_results")
	val totalResults: Int
)

data class TvDiscover(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("poster_path")
	val posterPath: String,
)
