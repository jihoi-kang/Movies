package com.jay.movies.data.remote.api

import com.jay.movies.data.remote.api.response.GetGenresResponse
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import com.jay.movies.data.remote.api.response.GetVideosResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface MovieService {

    @GET("/3/discover/movie")
    suspend fun getMovies(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("release_date.lte") today: String = SimpleDateFormat("yyyy-MM-dd").format(Date()),
    ): GetMoviesResponse

    @GET("/3/genre/movie/list")
    suspend fun getMovieGenres(): GetGenresResponse

    @GET("/3/movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") id: Int,
    ): GetVideosResponse

}