package com.jay.movies.ui.system

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.movies.MoviesApplication
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.model.enums.Appearance
import com.jay.movies.model.enums.getAppearance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SystemViewModel @Inject constructor(
    dispatchers: DispatcherProvider,
    private val preferences: SharedPreferences,
) : BaseViewModel() {

    private val _currentTheme = MutableLiveData(Appearance.SYSTEM)
    val currentTheme: LiveData<Appearance> get() = _currentTheme

    init {
        viewModelScope.launch(dispatchers.io()) {
            val mode = preferences.getInt(MoviesApplication.CURRENT_THEME,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            _currentTheme.postValue(getAppearance(mode))
        }
    }

    fun onChangedTheme(mode: Int) {
        _currentTheme.value = getAppearance(mode)
        preferences.edit { putInt(MoviesApplication.CURRENT_THEME, mode) }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

}