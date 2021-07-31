package com.jay.movies.data.movie

import com.jay.movies.api.response.Genre
import com.jay.movies.api.response.Movie
import com.jay.movies.api.response.Video

interface MovieRepository {

    suspend fun getMovies(sortBy: String, page: Int): List<Movie>

    fun clearCachedMovies()

    suspend fun getTrailers(movieId: Int): List<Video>

    suspend fun getGenres(): List<Genre>

}