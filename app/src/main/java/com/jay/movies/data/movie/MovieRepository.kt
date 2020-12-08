package com.jay.movies.data.movie

import com.jay.movies.model.Genre
import com.jay.movies.model.Movie
import com.jay.movies.model.Video

interface MovieRepository {

    suspend fun fetchMovies(sortBy: String, page: Int): List<Movie>

    fun clearCachedMovies()

    suspend fun fetchTrailers(movieId: Int): List<Video>

    suspend fun fetchGenres(): List<Genre>

}