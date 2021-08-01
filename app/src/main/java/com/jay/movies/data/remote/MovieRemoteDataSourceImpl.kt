package com.jay.movies.data.remote

import com.jay.movies.data.remote.api.MovieService
import com.jay.movies.data.remote.api.response.Genre
import com.jay.movies.data.remote.api.response.Movie
import com.jay.movies.data.remote.api.response.Video

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService,
) : MovieRemoteDataSource {

    override suspend fun getMovies(sortBy: String, page: Int): List<Movie> =
        movieService.getMovies(sortBy, page).movies

    override suspend fun getTrailers(movieId: Int): List<Video> =
        movieService.getMovieVideos(movieId).videos

    override suspend fun getGenres(): List<Genre> =
        movieService.getMovieGenres().genres

}