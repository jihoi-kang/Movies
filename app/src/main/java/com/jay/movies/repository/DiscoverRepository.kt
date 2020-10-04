package com.jay.movies.repository

import androidx.lifecycle.MutableLiveData
import com.jay.movies.api.ApiResponse
import com.jay.movies.api.client.DiscoverClient
import com.jay.movies.api.message
import com.jay.movies.data.Movie
import com.jay.movies.room.MovieDao
import javax.inject.Inject

class DiscoverRepository @Inject constructor(
    private val discoverClient: DiscoverClient,
    private val movieDao: MovieDao
) {

    fun loadMovies(page: Int, error: (String) -> Unit): MutableLiveData<List<Movie>> {
        val liveData = MutableLiveData<List<Movie>>()
        var movies = movieDao.getMovieList(page)
        if (movies.isEmpty()) {
            discoverClient.fetchDiscoverMovie(page) { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        response.data?.let { data ->
                            movies = data.results
                            movies.forEach { it.page = page }
                            liveData.postValue(movies)
                            movieDao.insertMovieList(movies)
                        }
                    }
                    is ApiResponse.Failure.Error -> error(response.message())
                    is ApiResponse.Failure.Exception -> error(response.message())
                }
            }
        }
        liveData.postValue(movies)
        return liveData
    }

}
