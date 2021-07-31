package com.jay.movies.api.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetGenresResponse(
    @SerializedName("genres")
    val genres: List<Genre> = emptyList(),
)

@Parcelize
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
) : Parcelable