package com.jay.movies.ui.system

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.movies.MoviesApplication
import com.jay.movies.base.BaseViewModel
import com.jay.movies.model.enums.Appearance
import com.jay.movies.model.enums.getAppearance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SystemViewModel @Inject constructor(
    private val preferences: SharedPreferences,
) : BaseViewModel() {

    private val _currentAppearance = MutableLiveData(Appearance.SYSTEM)
    val currentAppearance: LiveData<Appearance> get() = _currentAppearance

    init {
        viewModelScope.launch {
            val appearanceMode = preferences.getInt(MoviesApplication.CURRENT_APPEARANCE,
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            _currentAppearance.postValue(getAppearance(appearanceMode))
        }
    }

    fun onChangedAppearance(appearance: Appearance) {
        _currentAppearance.value = appearance

        preferences.edit { putInt(MoviesApplication.CURRENT_APPEARANCE, appearance.mode) }
        AppCompatDelegate.setDefaultNightMode(appearance.mode)
    }

}