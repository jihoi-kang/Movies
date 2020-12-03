package com.jay.movies.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event

class SettingViewModel @ViewModelInject constructor() : BaseViewModel() {

    private val _themeClickEvent = MutableLiveData<Event<Unit>>()
    val themeClickEvent: LiveData<Event<Unit>> get() = _themeClickEvent

    fun onClickTheme() {
        _themeClickEvent.value = Event(Unit)
    }

}