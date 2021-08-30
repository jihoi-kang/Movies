package com.jay.movies.ui.system

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.data.local.SettingLocalDataSource
import com.jay.movies.model.enums.Appearance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SystemViewModel @Inject constructor(
    private val settingLocalDataSource: SettingLocalDataSource,
) : BaseViewModel() {

    private val _currentAppearance = MutableLiveData(Appearance.SYSTEM)
    val currentAppearance: LiveData<Appearance> get() = _currentAppearance

    init {
        _currentAppearance.value = settingLocalDataSource.getAppearance()
    }

    fun onChangedAppearance(appearance: Appearance) {
        _currentAppearance.value = appearance

        settingLocalDataSource.setAppearance(appearance)
        AppCompatDelegate.setDefaultNightMode(appearance.mode)
    }

}