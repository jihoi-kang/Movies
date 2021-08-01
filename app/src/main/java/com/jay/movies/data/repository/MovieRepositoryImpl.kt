package com.jay.movies.data.repository

import com.jay.movies.data.remote.MovieRemoteDataSource
import com.jay.movies.data.remote.api.response.GetGenresResponse
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import com.jay.movies.data.remote.api.response.GetVideosResponse

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    private val inMemoryCacheMovies = mutableListOf<GetMoviesResponse.Movie>()

    override suspend fun getMovies(sortBy: String, page: Int): List<GetMoviesResponse.Movie> {
        val movies = movieRemoteDataSource.getMovies(sortBy, page)
        inMemoryCacheMovies.addAll(movies.filter {
            !inMemoryCacheMovies.contains(it)
        })

        return inMemoryCacheMovies
    }

    override fun clearCachedMovies() {
        inMemoryCacheMovies.clear()
    }

    override suspend fun getTrailers(movieId: Int): List<GetVideosResponse.Video> =
        movieRemoteDataSource.getTrailers(movieId)

    override suspend fun getGenres(): List<GetGenresResponse.Genre> =
        movieRemoteDataSource.getGenres()

}