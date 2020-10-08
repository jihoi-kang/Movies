package com.jay.movies.api

import com.jay.movies.BuildConfig
import com.jay.movies.api.DiscoverMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {

    @GET("/3/discover/movie?language=en")
    suspend fun fetchDiscoverMovie(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.TMDB_API_KEY
    ): DiscoverMovieResponse

}