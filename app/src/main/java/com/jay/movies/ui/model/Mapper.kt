package com.jay.movies.ui.model

import com.jay.movies.data.Movie

fun Movie.asUiModel() =
    UiMovieModel(
        id = id,
        title = title,
        posterPath = poster_path ?: "",
        voteAverage = vote_average,
        releaseDate = release_date ?: ""
    )