package com.jay.movies.data.remote

import com.jay.movies.data.remote.api.response.Genre
import com.jay.movies.data.remote.api.response.Movie
import com.jay.movies.data.remote.api.response.Video

interface MovieRemoteDataSource {

    suspend fun getMovies(sortBy: String, page: Int): List<Movie>

    suspend fun getTrailers(movieId: Int): List<Video>

    suspend fun getGenres(): List<Genre>

}