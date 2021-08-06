package com.jay.movies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.model.Filter
import com.jay.movies.model.UiGenreModel
import com.jay.movies.model.UiMovieModel
import com.jay.movies.model.asUiModel
import com.jay.movies.ui.movie.filter.MovieFilterFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {
    private val TAG = this::class.java.simpleName

    private var lastRequestedPage = STARTING_PAGE_INDEX

    val isRefreshing: LiveData<Boolean> get() = movieItems.map { false }

    private val _movieItems = MutableLiveData<List<UiMovieModel>>(emptyList())
    val movieItems: LiveData<List<UiMovieModel>> get() = _movieItems

    private lateinit var genreItems: List<UiGenreModel>

    private val _openFilter = MutableLiveData<Event<Unit>>()
    val openFilter: LiveData<Event<Unit>> get() = _openFilter

    private val _currentFilter = MutableLiveData<Filter>()
    val currentFilter: LiveData<Filter> get() = _currentFilter

    private val _openMovieEvent = MutableLiveData<Event<UiMovieModel>>()
    val openMovieEvent: LiveData<Event<UiMovieModel>> get() = _openMovieEvent

    private var isRequestInProgress = false

    init {
        viewModelScope.launch {
            _currentFilter.value = MovieFilterFragment.DEFAULT_FILTER
            val deferred = async {
                genreItems = movieRepository.getGenres().map {
                    it.asUiModel()
                }
            }

            deferred.await()
            getMovies()
        }
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            getMovies()
        }
    }

    fun refresh() {
        getMovies()
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
                ).map { it.asUiModel(genreItems) }
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

    fun openMovieDetail(movie: UiMovieModel) {
        _openMovieEvent.value = Event(movie)
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val VISIBLE_THRESHOLD = 3
    }

}