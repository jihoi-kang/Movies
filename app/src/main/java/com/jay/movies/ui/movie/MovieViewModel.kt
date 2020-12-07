package com.jay.movies.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.movie.MovieRepository
import com.jay.movies.model.Filter
import com.jay.movies.model.Movie
import com.jay.movies.ui.movie.filter.MovieFilterFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName

    private var lastRequestedPage = STARTING_PAGE_INDEX

    val isRefreshing: LiveData<Boolean> get() = movieItems.map { false }

    private val _movieItems = MutableLiveData<List<Movie>>(emptyList())
    val movieItems: LiveData<List<Movie>> get() = _movieItems

    private val _openFilter = MutableLiveData<Event<Unit>>()
    val openFilter: LiveData<Event<Unit>> get() = _openFilter

    private val _currentFilter = MutableLiveData<Filter>()
    val currentFilter: LiveData<Filter> get() = _currentFilter

    private val _openMovieEvent = MutableLiveData<Event<Movie>>()
    val openMovieEvent: LiveData<Event<Movie>> get() = _openMovieEvent

    init {
        _currentFilter.value = MovieFilterFragment.DEFAULT_FILTER
        fetchMovies()
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            fetchMovies()
        }
    }

    fun refresh() {
        fetchMovies()
    }

    private fun fetchMovies() {
        val sortByName = currentFilter.value?.sortByName ?: return

        viewModelScope.launch {
            _movieItems.postValue(
                movieRepository.fetchMovies(
                    sortByName,
                    lastRequestedPage++
                )
            )
        }
    }

    fun openFilter() {
        _openFilter.value = Event(Unit)
    }

    fun setupFilter(filter: Filter) {
        _currentFilter.value = filter
        movieRepository.clearCachedMovies()
        lastRequestedPage = STARTING_PAGE_INDEX
        fetchMovies()
    }

    fun openMovieDetail(movie: Movie) {
        _openMovieEvent.value = Event(movie)
    }

//    var allGenres: List<Genre> = emptyList()
//
//    private val sortByLiveData = MutableLiveData<String>()
//    val movieResult: LiveData<MovieResult> = sortByLiveData.switchMap { sortBy ->
//        liveData {
//            if (allGenres.isEmpty()) {
//                allGenres = movieRepositoryTemp.fetchGenres()
//            }
//
//            val movieResultStream =
//                movieRepositoryTemp.fetchMovieResultStream(sortBy).asLiveData(dispatchers.main())
//            emitSource(movieResultStream)
//        }
//    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val VISIBLE_THRESHOLD = 3
    }

}