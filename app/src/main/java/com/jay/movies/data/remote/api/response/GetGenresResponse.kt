package com.jay.movies.data.remote.api.response

import com.google.gson.annotations.SerializedName

data class GetGenresResponse(
    @SerializedName("genres")
    val genres: List<Genre> = emptyList(),
) {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
    )
}