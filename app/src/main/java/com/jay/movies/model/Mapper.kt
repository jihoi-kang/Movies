package com.jay.movies.model

import com.jay.movies.data.remote.api.response.GetGenresResponse.Genre
import com.jay.movies.data.remote.api.response.GetMoviesResponse.Movie
import com.jay.movies.data.remote.api.response.GetVideosResponse.Video

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w780"
private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"
private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="

fun Genre.asUiModel(): UiGenreModel =
    UiGenreModel(
        this.id,
        this.name,
    )

fun Movie.asUiModel(allGenres: List<UiGenreModel>): UiMovieModel {
    val genres = allGenres.filter {
        genreIds.contains(it.id)
    }

    return UiMovieModel(
        this.id,
        "$BASE_IMAGE_URL${this.posterPath}",
        "$BASE_IMAGE_URL${this.backdropPath}",
        this.title,
        genres,
        this.overview,
        this.releaseDate,
        this.voteAverage,
    )
}

fun Video.asUiModel(): UiVideoModel =
    UiVideoModel(
        this.id,
        this.name,
        this.site,
        "$YOUTUBE_THUMBNAIL_URL${this.key}/default.jpg",
        "$YOUTUBE_VIDEO_URL${this.key}",
        this.size,
        this.type,
    )