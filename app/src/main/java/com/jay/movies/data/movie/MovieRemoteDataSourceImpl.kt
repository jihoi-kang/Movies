package com.jay.movies.data.movie

import com.jay.movies.api.MovieService
import com.jay.movies.api.response.Genre
import com.jay.movies.api.response.Movie
import com.jay.movies.api.response.Video

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