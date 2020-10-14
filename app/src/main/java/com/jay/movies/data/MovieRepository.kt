package com.jay.movies.data

import com.jay.movies.api.MovieService
import com.jay.movies.api.model.GenreResult
import com.jay.movies.api.model.MovieResult
import com.jay.movies.model.Genre
import com.jay.movies.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_PAGE_INDEX = 1

@ExperimentalCoroutinesApi
class MovieRepository @Inject constructor(
    private val movieService: MovieService
) {

    private val inMemoryCacheMovies = mutableListOf<Movie>()
    private val inMemoryCacheGenres = mutableListOf<Genre>()
    private val movieResults = ConflatedBroadcastChannel<MovieResult>()
    private val genreResults = ConflatedBroadcastChannel<GenreResult>()

    private var lastRequestedPage = STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    suspend fun fetchMovieResultStream(sortBy: String): Flow<MovieResult> {
        lastRequestedPage = STARTING_PAGE_INDEX
        inMemoryCacheMovies.clear()
        fetchMoviesAndCache(sortBy)

        return movieResults.asFlow()
    }

    suspend fun fetchMovieMore(query: String) {
        if (isRequestInProgress) return
        val successful = fetchMoviesAndCache(query)
        if (successful) {
            lastRequestedPage++
        }
    }

    fun getById(id: Int): Flow<Movie>? {
        if(inMemoryCacheMovies.isNotEmpty()) {
            inMemoryCacheMovies.forEach { movie ->
                if(movie.id == id) return flow { emit(movie) }
            }
        }
        return null
    }

    private suspend fun fetchMoviesAndCache(sortBy: String): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = movieService.fetchMovies(
                sortBy = sortBy, page = lastRequestedPage
            )
            val movies = response.results
            movies.forEach { movie ->
                if(!inMemoryCacheMovies.contains(movie)) {
                    inMemoryCacheMovies.add(movie)
                }
            }
            movieResults.offer(MovieResult.Success(inMemoryCacheMovies))
            successful = true
        } catch (exception: IOException) {
            movieResults.offer(MovieResult.Error(exception))
        } catch (exception: HttpException) {
            movieResults.offer(MovieResult.Error(exception))
        }

        isRequestInProgress = false
        return successful
    }

    suspend fun fetchGenreResultStream(): List<Genre> {
        inMemoryCacheGenres.clear()
        fetchGenresAndCache()

        return inMemoryCacheGenres
    }

    private suspend fun fetchGenresAndCache(): Boolean {
        var successful = false

        try {
            val response = movieService.fetchMovieGenres()
            val genres = response.genres
            inMemoryCacheGenres.addAll(genres)
            genreResults.offer(GenreResult.Success(inMemoryCacheGenres))
            successful = true
        } catch (exception: IOException) {
            genreResults.offer(GenreResult.Error(exception))
        } catch (exception: HttpException) {
            genreResults.offer(GenreResult.Error(exception))
        }

        return successful
    }

}
