package com.jay.movies.ui.system

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.movies.MovieApplication
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.common.Event
import kotlinx.coroutines.launch

class SystemViewModel @ViewModelInject constructor(
    dispatchers: DispatcherProvider,
    private val preferences: SharedPreferences,
) : BaseViewModel() {

    private val _currentTheme = MutableLiveData<Event<Int>>()
    val currentTheme: LiveData<Event<Int>> get() = _currentTheme

    init {
        viewModelScope.launch(dispatchers.io()) {
            val theme = preferences.getInt(MovieApplication.CURRENT_THEME,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            _currentTheme.postValue(Event(theme))
        }
    }

    fun onChangedTheme(theme: Int) {
        _currentTheme.value = Event(theme)
        preferences.edit { putInt(MovieApplication.CURRENT_THEME, theme) }
        AppCompatDelegate.setDefaultNightMode(theme)
    }

}