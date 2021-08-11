package com.jay.movies.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.model.enums.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieSharedViewModel @Inject constructor() : BaseViewModel() {
    private val TAG = this::class.java.simpleName

    private val _currentFilter = MutableLiveData<Event<Filter>>()
    val currentFilter: LiveData<Event<Filter>> get() = _currentFilter

    fun setupFilter(filter: Filter) {
        _currentFilter.value = Event(filter)
    }

}