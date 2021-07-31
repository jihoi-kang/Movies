package com.jay.movies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.jay.movies.api.response.Genre
import com.jay.movies.api.response.Movie
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.movie.MovieRepository
import com.jay.movies.model.Filter
import com.jay.movies.ui.movie.filter.MovieFilterFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName

    private var lastRequestedPage = STARTING_PAGE_INDEX

    val isRefreshing: LiveData<Boolean> get() = movieItems.map { false }

    private val _movieItems = MutableLiveData<List<Movie>>(emptyList())
    val movieItems: LiveData<List<Movie>> get() = _movieItems

    private val _genreItems = MutableLiveData<List<Genre>>()
    val genreItems: LiveData<List<Genre>> get() = _genreItems

    private val _openFilter = MutableLiveData<Event<Unit>>()
    val openFilter: LiveData<Event<Unit>> get() = _openFilter

    private val _currentFilter = MutableLiveData<Filter>()
    val currentFilter: LiveData<Filter> get() = _currentFilter

    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> get() = _openMovieEvent

    private var isRequestInProgress = false

    init {
        _currentFilter.value = MovieFilterFragment.DEFAULT_FILTER
        getGenres()
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            getMovies()
        }
    }

    fun refresh() {
        getMovies()
    }

    private fun getGenres() {
        viewModelScope.launch {
            _genreItems.value = movieRepository.getGenres()
            getMovies()
        }
    }

    private fun getMovies() {
        if (isRequestInProgress) return
        val sortByName = currentFilter.value?.sortByName ?: return

        viewModelScope.launch {
            isRequestInProgress = true
            _movieItems.postValue(
                movieRepository.getMovies(
                    sortByName,
                    lastRequestedPage++
                )
            )
            isRequestInProgress = false
        }
    }

    fun openFilter() {
        _openFilter.value = Event(Unit)
    }

    fun setupFilter(filter: Filter) {
        _currentFilter.value = filter
        movieRepository.clearCachedMovies()
        lastRequestedPage = STARTING_PAGE_INDEX
        getMovies()
    }

    fun openMovieDetail(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val VISIBLE_THRESHOLD = 3
    }

}