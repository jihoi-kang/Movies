package com.jay.movies.api

import com.google.gson.annotations.SerializedName
import com.jay.movies.model.Movie

data class DiscoverMovieResponse(
    @SerializedName("results") val results: List<Movie> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int = 0
)
