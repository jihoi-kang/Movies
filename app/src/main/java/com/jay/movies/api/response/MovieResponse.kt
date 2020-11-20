package com.jay.movies.api.response

import com.google.gson.annotations.SerializedName
import com.jay.movies.model.Movie

data class MovieResponse(
    @SerializedName("results") val results: List<Movie> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int = 0,
)