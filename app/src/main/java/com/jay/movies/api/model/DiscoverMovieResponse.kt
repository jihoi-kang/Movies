package com.jay.movies.api.model

import com.google.gson.annotations.SerializedName
import com.jay.movies.data.Movie

data class DiscoverMovieResponse(
    @field:SerializedName("results") val results: List<Movie>,
    @field:SerializedName("total_pages") val totalPages: Int
)
