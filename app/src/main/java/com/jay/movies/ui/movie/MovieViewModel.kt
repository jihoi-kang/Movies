package com.jay.movies.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.repository.DiscoverRepository
import com.jay.movies.ui.model.UiMovieModel
import com.jay.movies.ui.model.asUiModel


class MovieViewModel @ViewModelInject constructor(
    private val discoverRepository: DiscoverRepository
) : BaseViewModel() {

    private val _loadingEvent = MutableLiveData(LoadingType.NORMAL)
    val posterList: LiveData<List<UiMovieModel>> = _loadingEvent.switchMap { loadingType ->
        liveData {
            val movies = discoverRepository.loadMovies(1) {
                // error
            }.map {
                it.map { movie -> movie.asUiModel() }
            }

            emitSource(
                movies.asFlow().asLiveData()
            )
        }
    }

    val isRefreshing: LiveData<Boolean> = posterList.map { false }

    private val _itemEvent = MutableLiveData<Event<Int>>()
    val itemEvent: LiveData<Event<Int>> get() = _itemEvent

    init {
        loading(LoadingType.NORMAL)
    }

    fun refresh() {
        loading(LoadingType.FORCE)
    }

    fun onClickItem(movieId: Int) {
        _itemEvent.value = Event(movieId)
    }

    fun onClickFilter() {
        // filter
    }

    private fun loading(loadingType: LoadingType) {
        _loadingEvent.value = loadingType
    }

}

private enum class LoadingType {
    NORMAL, FORCE
}