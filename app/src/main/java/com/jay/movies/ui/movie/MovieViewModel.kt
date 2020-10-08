package com.jay.movies.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jay.movies.api.model.MovieSearchResult
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.common.Event
import com.jay.movies.data.DiscoverRepository
import com.jay.movies.ui.movie.MovieFragment.Companion.DEFAULT_SORT_BY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieViewModel @ViewModelInject constructor(
    private val dispatchers: DispatcherProvider,
    private val discoverRepository: DiscoverRepository
) : BaseViewModel() {

    private val TAG = this::class.java.simpleName

    private val sortByLiveData = MutableLiveData<String>()
    val movieResult: LiveData<MovieSearchResult> = sortByLiveData.switchMap { sortBy ->
        liveData {
            val movies =
                discoverRepository.getSearchResultStream(sortBy).asLiveData(dispatchers.main())
            emitSource(movies)
        }
    }

    fun searchMovie(sortBy: String) {
        sortByLiveData.postValue(sortBy)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            sortByLiveData.value?.let { immutableSortBy ->
                viewModelScope.launch {
                    discoverRepository.requestMore(immutableSortBy)
                }
            }
        }
    }


    private val _itemEvent = MutableLiveData<Event<Int>>()
    val itemEvent: LiveData<Event<Int>> get() = _itemEvent

    val isRefreshing: LiveData<Boolean> = movieResult.map { false }

    fun refresh() {
        searchMovie(sortByLiveData.value ?: DEFAULT_SORT_BY)
    }

    fun onClickItem(movieId: Int) {
        _itemEvent.value = Event(movieId)
    }

    fun onClickFilter() {
        // filter
    }

    companion object {
        private const val VISIBLE_THRESHOLD = 3
    }

}