package com.jay.movies.ui.setting.theme

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.movies.MovieApplication
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.common.Event
import kotlinx.coroutines.launch

class ThemeViewModel @ViewModelInject constructor(
    dispatchers: DispatcherProvider,
    private val preferences: SharedPreferences,
) : BaseViewModel() {

    private val _closeEvent = MutableLiveData<Event<Unit>>()
    val closeEvent: LiveData<Event<Unit>> get() = _closeEvent

    private val _themeClickEvent = MutableLiveData<Event<Int>>()
    val themeClickEvent: LiveData<Event<Int>> get() = _themeClickEvent

    init {
        viewModelScope.launch(dispatchers.io()) {
            val theme = preferences.getInt(MovieApplication.CURRENT_THEME,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            _themeClickEvent.postValue(Event(theme))
        }
    }

    fun onClickTheme(theme: Int) {
        if (_themeClickEvent.value?.peekContent() == theme) return
        _themeClickEvent.value = Event(theme)
        close()
    }

    fun close() {
        _closeEvent.value = Event(Unit)
    }

}