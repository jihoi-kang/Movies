package com.jay.movies.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = [("id")])
data class Movie(
    var page: Int,
    val id: Int,
    val poster_path: String? = "",
    val adult: Boolean,
    val overview: String = "",
    val release_date: String? = "",
    val genre_ids: List<Int> = emptyList(),
    val original_title: String = "",
    val original_language: String = "",
    val title: String = "",
    val backdrop_path: String? = "",
    val popularity: Float = 0f,
    val vote_count: Int = 0,
    val video: Boolean,
    val vote_average: Float = 0f
) : Parcelable
