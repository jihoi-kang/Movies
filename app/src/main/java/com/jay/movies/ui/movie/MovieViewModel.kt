package com.jay.movies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.model.UiGenreModel
import com.jay.movies.model.UiMovieModel
import com.jay.movies.model.asUiModel
import com.jay.movies.model.enums.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {
    val isRefreshing: LiveData<Boolean> get() = movieItems.map { false }

    private val _movieItems = MutableLiveData<List<UiMovieModel>>(emptyList())
    val movieItems: LiveData<List<UiMovieModel>> get() = _movieItems

    private lateinit var genreItems: List<UiGenreModel>

    private val _openFilter = MutableLiveData<Event<Unit>>()
    val openFilter: LiveData<Event<Unit>> get() = _openFilter

    private val _openMovieEvent = MutableLiveData<Event<UiMovieModel>>()
    val openMovieEvent: LiveData<Event<UiMovieModel>> get() = _openMovieEvent

    private var lastRequestedPage = STARTING_PAGE_INDEX
    private var isRequestInProgress = false
    private var currentFilter: Filter = Filter.POPULARITY

    init {
        viewModelScope.launch {
            val genresDeferred = async { movieRepository.getGenres().map { it.asUiModel() } }
            val moviesDeferred = async { suspendGetMovies() }

            genreItems = genresDeferred.await()
            val movies = moviesDeferred.await()

            _movieItems.value = movies.map { it.asUiModel(genreItems) }
        }
    }

    fun refresh() {
        movieRepository.clearCachedMovies()
        lastRequestedPage = STARTING_PAGE_INDEX
        getMovies()
    }

    fun getMovies() {
        if (isRequestInProgress) return

        viewModelScope.launch {
            isRequestInProgress = true
            _movieItems.value = suspendGetMovies().map { it.asUiModel(genreItems) }
            isRequestInProgress = false
        }
    }

    fun openFilter() {
        _openFilter.value = Event(Unit)
    }

    fun openMovieDetail(movie: UiMovieModel) {
        _openMovieEvent.value = Event(movie)
    }

    fun updateFilter(filter: Filter) {
        currentFilter = filter
        refresh()
    }

    private suspend fun suspendGetMovies() =
        suspendCancellableCoroutine<List<GetMoviesResponse.Movie>> { continuation ->
            viewModelScope.launch {
                continuation.resume(
                    movieRepository.getMovies(currentFilter.sortBy, lastRequestedPage++)
                )
            }
        }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

}