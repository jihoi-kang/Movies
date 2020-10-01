package com.jay.movies.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.ui.model.UiPosterModel


class MovieViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _loadingEvent = MutableLiveData(LoadingType.NORMAL)
    val posterList: LiveData<List<UiPosterModel>> = MutableLiveData<List<UiPosterModel>>().apply {
        setValue(listOf(
            UiPosterModel("1", "title #1", "7.8", "2020-07-29"),
            UiPosterModel("2", "title #2", "4.8", "2020-08-13"),
            UiPosterModel("3", "title #3", "6.8", "2020-08-30"),
            UiPosterModel("4", "title #4", "7.2", "2020-09-02"),
            UiPosterModel("5", "title #5", "7.3", "2020-09-26")
        ))
    }

    val isRefreshing: LiveData<Boolean> = posterList.map { false }

    private val _itemEvent = MutableLiveData<Event<String>>()
    val itemEvent: LiveData<Event<String>> get() = _itemEvent

    init {
        loading(LoadingType.NORMAL)
    }

    fun refresh() {
        loading(LoadingType.FORCE)
    }

    fun onClickItem(posterId: String) {
        _itemEvent.value = Event(posterId)
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