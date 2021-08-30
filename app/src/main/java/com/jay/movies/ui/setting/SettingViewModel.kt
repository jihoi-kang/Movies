package com.jay.movies.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.model.enums.Appearance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : BaseViewModel() {

    private val _showAppearanceEvent = MutableLiveData<Event<Appearance>>()
    val showAppearanceEvent: LiveData<Event<Appearance>> get() = _showAppearanceEvent

    private val _currentAppearance = MutableLiveData<Appearance>()
    val currentAppearance: LiveData<Appearance> get() = _currentAppearance

    fun setAppearance(appearance: Appearance) {
        _currentAppearance.value = appearance
    }

    fun showAppearance() {
        val currentAppearance = currentAppearance.value ?: return

        _showAppearanceEvent.value = Event(currentAppearance)
    }

}