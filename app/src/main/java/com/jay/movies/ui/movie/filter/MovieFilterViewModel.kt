package com.jay.movies.ui.movie.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.model.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFilterViewModel @Inject constructor() : BaseViewModel() {

    private val _currentFilter = MutableLiveData<Filter>()
    val currentFilter: LiveData<Filter> get() = _currentFilter

    private val _submitEvent = MutableLiveData<Event<Unit>>()
    val submitEvent: LiveData<Event<Unit>> get() = _submitEvent

    fun setupFilter(filter: Filter) {
        _currentFilter.value = filter
    }

    fun submit() {
        _submitEvent.value = Event(Unit)
    }

}