package com.jay.movies.data.repository

import com.jay.movies.data.remote.api.response.Genre
import com.jay.movies.data.remote.api.response.Movie
import com.jay.movies.data.remote.api.response.Video
import com.jay.movies.data.remote.MovieRemoteDataSource

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    private val inMemoryCacheMovies = mutableListOf<Movie>()

    override suspend fun getMovies(sortBy: String, page: Int): List<Movie> {
        val movies = movieRemoteDataSource.getMovies(sortBy, page)
        inMemoryCacheMovies.addAll(movies.filter {
            !inMemoryCacheMovies.contains(it)
        })

        return inMemoryCacheMovies
    }

    override fun clearCachedMovies() {
        inMemoryCacheMovies.clear()
    }

    override suspend fun getTrailers(movieId: Int): List<Video> =
        movieRemoteDataSource.getTrailers(movieId)

    override suspend fun getGenres(): List<Genre> =
        movieRemoteDataSource.getGenres()

}