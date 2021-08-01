package com.jay.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiGenreModel(
    val id: Int,
    val name: String,
) : Parcelable