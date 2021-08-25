package com.jay.movies.data.remote

import com.jay.movies.data.remote.api.MovieService
import com.jay.movies.data.remote.api.response.GetGenresResponse
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import com.jay.movies.data.remote.api.response.GetVideosResponse
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
) : MovieRemoteDataSource {

    override suspend fun getMovies(sortBy: String, page: Int): List<GetMoviesResponse.Movie> =
        movieService.getMovies(sortBy, page).movies

    override suspend fun getTrailers(movieId: Int): List<GetVideosResponse.Video> =
        movieService.getMovieVideos(movieId).videos

    override suspend fun getGenres(): List<GetGenresResponse.Genre> =
        movieService.getMovieGenres().genres

}