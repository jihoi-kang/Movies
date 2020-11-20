package com.jay.movies.api.response

import com.google.gson.annotations.SerializedName
import com.jay.movies.model.Video

data class VideoResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("results") val results: List<Video> = emptyList(),
)