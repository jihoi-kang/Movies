package com.jay.movies.api

import com.jay.movies.api.response.GenreResponse
import com.jay.movies.api.response.MovieResponse
import com.jay.movies.api.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface MovieService {

    @GET("/3/discover/movie")
    suspend fun fetchMovies(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("release_date.lte") today: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    ): MovieResponse

    @GET("/3/genre/movie/list")
    suspend fun fetchMovieGenres(): GenreResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun fetchMovieVideos(
        @Path("movie_id") id: Int,
    ): VideoResponse

}