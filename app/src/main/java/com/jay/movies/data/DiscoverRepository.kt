package com.jay.movies.data

import com.jay.movies.api.DiscoverService
import com.jay.movies.api.model.MovieSearchResult
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
class DiscoverRepository @Inject constructor(
    private val discoverService: DiscoverService,
) {

    private val inMemoryCache = mutableListOf<Movie>()
    private val searchResults = ConflatedBroadcastChannel<MovieSearchResult>()

    private var lastRequestedPage = STARTING_PAGE_INDEX
    private var isRequestInProgress = false

    suspend fun getSearchResultStream(sortBy: String): Flow<MovieSearchResult> {
        lastRequestedPage = STARTING_PAGE_INDEX
        inMemoryCache.clear()
        requestAndSaveData(sortBy)

        return searchResults.asFlow()
    }

    suspend fun requestMore(query: String) {
        if (isRequestInProgress) return
        val successful = requestAndSaveData(query)
        if (successful) {
            lastRequestedPage++
        }
    }

    fun getById(id: Int): Flow<Movie>? {
        if(inMemoryCache.isNotEmpty()) {
            inMemoryCache.forEach { movie ->
                if(movie.id == id) {
                    return flow {
                        emit(movie)
                    }
                }
            }
        }
        return null
    }

    private suspend fun requestAndSaveData(sortBy: String): Boolean {
        isRequestInProgress = true
        var successful = false

        try {
            val response = discoverService.fetchDiscoverMovie(
                sortBy = sortBy, page = lastRequestedPage
            )
            val movies = response.results
            movies.forEach { movie ->
                if(!inMemoryCache.contains(movie)) {
                    inMemoryCache.add(movie)
                }
            }
            searchResults.offer(MovieSearchResult.Success(inMemoryCache))
            successful = true
        } catch (exception: IOException) {
            searchResults.offer(MovieSearchResult.Error(exception))
        } catch (exception: HttpException) {
            searchResults.offer(MovieSearchResult.Error(exception))
        }

        isRequestInProgress = false
        return successful
    }

}
