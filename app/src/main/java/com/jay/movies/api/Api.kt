package com.jay.movies.api

object Api {

    const val BASE_TMDB_API_PATH = "https://api.themoviedb.org"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"

    fun getBackdropPath(backdropPath: String?): String {
        return BASE_BACKDROP_PATH + backdropPath
    }

}