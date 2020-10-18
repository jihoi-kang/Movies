package com.jay.movies.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event

class SettingViewModel @ViewModelInject constructor(
//    private val preference: SharedPreferences
) : BaseViewModel() {

    private val _themeEvent = MutableLiveData<Event<Int>>()
    val themeEvent: LiveData<Event<Int>> get() = _themeEvent

    fun onClickTheme(theme: Int) {
        _themeEvent.value = Event(theme)
    }

}