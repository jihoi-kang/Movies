package com.jay.movies.data

import com.jay.movies.api.MovieService
import com.jay.movies.api.MovieResult
import com.jay.movies.model.Genre
import com.jay.movies.model.Movie
import com.jay.movies.model.Video
import com.jay.movies.room.GenreDao
import com.jay.movies.room.MovieDao
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
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
) {

    private val inMemoryCacheMovies = mutableListOf<Movie>()
    private val movieResults = ConflatedBroadcastChannel<MovieResult>()

    private var lastRequestedPage = STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    suspend fun fetchMovieResultStream(sortBy: String): Flow<MovieResult> {
        lastRequestedPage = STARTING_PAGE_INDEX
        inMemoryCacheMovies.clear()
        movieDao.clearMovieList()
        fetchMoviesAndCache(sortBy)

        return movieResults.asFlow()
    }

    suspend fun fetchMoviesMore(query: String) {
        if (isRequestInProgress) return

        val successful = fetchMoviesAndCache(query)
        if (successful) lastRequestedPage++
    }

    fun getById(id: Int): Flow<Movie> = flow {
        val movie = movieDao.getMovie(id)
        var videos = movie.videos ?: emptyList()
        if(videos.isNullOrEmpty()) {
            try {
                val response = movieService.fetchMovieVideos(id)
                videos = response.results
                movie.videos = videos
                movieDao.updateMovie(movie)
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: HttpException) {
                exception.printStackTrace()
            }
        }
        emit(movie)
    }

    private suspend fun fetchMoviesAndCache(sortBy: String): Boolean {
        isRequestInProgress = true
        var successful = false
        var movies = movieDao.getMovieList(lastRequestedPage)

        if (movies.isEmpty()) {
            try {
                val response = movieService.fetchMovies(
                    sortBy = sortBy, page = lastRequestedPage
                )
                movies = response.results
                movies.forEach { it.page = lastRequestedPage }
                movieDao.insertMovieList(movies)
                successful = true
            } catch (exception: IOException) {
                movieResults.offer(MovieResult.Error(exception))
            } catch (exception: HttpException) {
                movieResults.offer(MovieResult.Error(exception))
            }
        } else {
            successful = true
        }

        inMemoryCacheMovies.addAll(movies.filter {
            !inMemoryCacheMovies.contains(it)
        })
        movieResults.offer(MovieResult.Success(inMemoryCacheMovies))

        isRequestInProgress = false
        return successful
    }

    suspend fun fetchGenres(): List<Genre> {
        var genres = genreDao.getGenreList()
        if (genres.isEmpty()) {
            try {
                val response = movieService.fetchMovieGenres()
                genres = response.genres
                genreDao.insertGenreList(genres)
            } catch (exception: IOException) {
                exception.printStackTrace()
            } catch (exception: HttpException) {
                exception.printStackTrace()
            }
        }

        return genres
    }

}
