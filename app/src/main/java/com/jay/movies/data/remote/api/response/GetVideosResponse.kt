package com.jay.movies.data.remote.api.response

import com.google.gson.annotations.SerializedName

data class GetVideosResponse(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("results") val videos: List<Video> = emptyList(),
) {
    data class Video(
        @SerializedName("id")
        val id: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("site")
        val site: String,
        @SerializedName("key")
        val key: String,
        @SerializedName("size")
        val size: Int,
        @SerializedName("type")
        val type: String,
    )
}