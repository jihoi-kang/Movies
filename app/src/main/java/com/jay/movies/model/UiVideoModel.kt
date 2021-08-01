package com.jay.movies.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiVideoModel(
    val id: String,
    val name: String,
    val site: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val size: Int,
    val type: String,
) : Parcelable