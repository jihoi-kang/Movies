package com.jay.movies.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : BaseViewModel() {

    private val _themeClickEvent = MutableLiveData<Event<Unit>>()
    val themeClickEvent: LiveData<Event<Unit>> get() = _themeClickEvent

    fun onClickTheme() {
        _themeClickEvent.value = Event(Unit)
    }

}