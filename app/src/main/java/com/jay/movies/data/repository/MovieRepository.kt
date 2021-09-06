package com.jay.movies.data.repository

import com.jay.movies.data.remote.api.response.GetGenresResponse
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import com.jay.movies.data.remote.api.response.GetVideosResponse

interface MovieRepository {

    suspend fun getMovies(sortBy: String, page: Int): List<GetMoviesResponse.Movie>

    suspend fun getTrailers(movieId: Int): List<GetVideosResponse.Video>

    suspend fun getGenres(): List<GetGenresResponse.Genre>

}