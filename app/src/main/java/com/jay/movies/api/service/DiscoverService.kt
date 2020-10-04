package com.jay.movies.api.service

import com.jay.movies.api.model.DiscoverMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DiscoverService {

    @GET("/3/discover/movie?language=en&sort_by=popularity.desc")
    fun fetchDiscoverMovie(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "29863c47b79cdbd8c0b24afc4e67ff9c"
    ): Call<DiscoverMovieResponse>

}