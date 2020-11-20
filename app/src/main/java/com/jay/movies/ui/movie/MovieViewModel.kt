package com.jay.movies.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jay.movies.api.MovieResult
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.common.Event
import com.jay.movies.data.MovieRepository
import com.jay.movies.model.Filter
import com.jay.movies.model.Genre
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieViewModel @ViewModelInject constructor(
    private val dispatchers: DispatcherProvider,
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName

    var allFilters: List<Filter> = emptyList()
    lateinit var selectedFilter: Filter

    var allGenres: List<Genre> = emptyList()

    private val sortByLiveData = MutableLiveData<String>()
    val movieResult: LiveData<MovieResult> = sortByLiveData.switchMap { sortBy ->
        liveData {
            if (allGenres.isEmpty()) {
                allGenres = movieRepository.fetchGenres()
            }

            val movieResultStream =
                movieRepository.fetchMovieResultStream(sortBy).asLiveData(dispatchers.main())
            emitSource(movieResultStream)
        }
    }

    fun fetchMovies(sortBy: String) {
        sortByLiveData.postValue(sortBy)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            sortByLiveData.value?.let { immutableSortBy ->
                viewModelScope.launch {
                    movieRepository.fetchMoviesMore(immutableSortBy)
                }
            }
        }
    }

    private val _itemEvent = MutableLiveData<Event<Int>>()
    val itemEvent: LiveData<Event<Int>> get() = _itemEvent

    private val _fabEvent = MutableLiveData<Event<String>>()
    val fabEvent: LiveData<Event<String>> get() = _fabEvent

    val isRefreshing: LiveData<Boolean> = movieResult.map { false }

    fun refresh() {
        fetchMovies(selectedFilter.sortByName)
    }

    fun onClickItem(movieId: Int) {
        _itemEvent.value = Event(movieId)
    }

    fun onClickFilter() {
        _fabEvent.value = Event("")
    }

    fun onClickSubmit() {
        refresh()
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 3
    }

}