package com.jay.movies.api

import com.google.gson.annotations.SerializedName
import com.jay.movies.model.Genre
import com.jay.movies.model.Movie
import com.jay.movies.model.Video

data class MovieResponse(
    @SerializedName("results") val results: List<Movie> = emptyList(),
    @SerializedName("total_pages") val totalPages: Int = 0
)

data class MovieGenreResponse(
    @SerializedName("genres") val genres: List<Genre> = emptyList()
)

data class MovieVideoResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("results") val results: List<Video> = emptyList()
)