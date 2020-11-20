package com.jay.movies.api.response

import com.google.gson.annotations.SerializedName
import com.jay.movies.model.Genre

data class GenreResponse(
    @SerializedName("genres") val genres: List<Genre> = emptyList(),
)