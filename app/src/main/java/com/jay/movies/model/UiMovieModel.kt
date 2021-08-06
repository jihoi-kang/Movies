package com.jay.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiMovieModel(
    val id: Int,
    val posterUrl: String,
    val backgroundUrl: String,
    val title: String,
    val genres: List<UiGenreModel>, // need to change List<String>, from id to title
    val overview: String,
    val releaseDate: String,
    val voteAverage: Float,
) : Parcelable