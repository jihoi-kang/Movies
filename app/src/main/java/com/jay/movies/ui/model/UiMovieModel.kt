package com.jay.movies.ui.model

data class UiMovieModel(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Float,
    val releaseDate: String
)