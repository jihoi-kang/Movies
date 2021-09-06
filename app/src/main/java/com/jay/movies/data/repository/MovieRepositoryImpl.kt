package com.jay.movies.data.repository

import com.jay.movies.data.remote.MovieRemoteDataSource
import com.jay.movies.data.remote.api.response.GetGenresResponse
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import com.jay.movies.data.remote.api.response.GetVideosResponse
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override suspend fun getMovies(sortBy: String, page: Int): List<GetMoviesResponse.Movie> =
        movieRemoteDataSource.getMovies(sortBy, page)

    override suspend fun getTrailers(movieId: Int): List<GetVideosResponse.Video> =
        movieRemoteDataSource.getTrailers(movieId)

    override suspend fun getGenres(): List<GetGenresResponse.Genre> =
        movieRemoteDataSource.getGenres()

}