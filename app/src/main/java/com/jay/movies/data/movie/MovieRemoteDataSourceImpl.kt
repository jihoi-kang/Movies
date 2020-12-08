package com.jay.movies.data.movie

import com.jay.movies.api.MovieService
import com.jay.movies.model.Genre
import com.jay.movies.model.Movie
import com.jay.movies.model.Video

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override suspend fun fetchMovies(sortBy: String, page: Int): List<Movie> =
        movieService.fetchMovies(sortBy, page).results

    override suspend fun fetchTrailers(movieId: Int): List<Video> =
        movieService.fetchMovieVideos(movieId).results

    override suspend fun fetchGenres(): List<Genre> =
        movieService.fetchMovieGenres().genres

}