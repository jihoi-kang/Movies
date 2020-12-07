package com.jay.movies.api

object Api {

    const val BASE_TMDB_API_PATH = "https://api.themoviedb.org"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
    private const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"

    fun getBackdropPath(backdropPath: String?) =
        BASE_BACKDROP_PATH + backdropPath

    fun getYoutubeVideoPath(videoPath: String) =
        YOUTUBE_VIDEO_URL + videoPath

    fun getYoutubeThumbnailPath(thumbnailPath: String): String {
        return "$YOUTUBE_THUMBNAIL_URL$thumbnailPath/default.jpg"
    }

}