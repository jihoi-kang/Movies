package com.jay.movies.data.remote.api.response

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("results")
    val movies: List<Movie> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
) {
    data class Movie(
        @SerializedName("id")
        val id: Int,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("popularity")
        val popularity: Float,
        @SerializedName("vote_count")
        val voteCount: Int,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Float,
    )
}