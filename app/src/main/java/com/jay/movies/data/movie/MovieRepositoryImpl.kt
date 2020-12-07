package com.jay.movies.data.movie

import com.jay.movies.model.Movie
import com.jay.movies.model.Video
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    private val inMemoryCacheMovies = mutableListOf<Movie>()

    override suspend fun fetchMovies(sortBy: String, page: Int): List<Movie> {
        val movies = movieRemoteDataSource.fetchMovies(sortBy, page)
        inMemoryCacheMovies.addAll(movies.filter {
            !inMemoryCacheMovies.contains(it)
        })

        return inMemoryCacheMovies
    }

    override fun clearCachedMovies() {
        inMemoryCacheMovies.clear()
    }

    override suspend fun fetchTrailers(movieId: Int): List<Video> =
        movieRemoteDataSource.fetchTrailers(movieId)

}