package com.jay.movies.data.repository

import com.jay.movies.data.remote.api.response.Genre
import com.jay.movies.data.remote.api.response.Movie
import com.jay.movies.data.remote.api.response.Video

interface MovieRepository {

    suspend fun getMovies(sortBy: String, page: Int): List<Movie>

    fun clearCachedMovies()

    suspend fun getTrailers(movieId: Int): List<Video>

    suspend fun getGenres(): List<Genre>

}