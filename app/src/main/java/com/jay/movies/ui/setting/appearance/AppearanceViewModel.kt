package com.jay.movies.ui.setting.appearance

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jay.movies.MoviesApplication
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.model.enums.Appearance
import com.jay.movies.model.enums.getAppearance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppearanceViewModel @Inject constructor(
    preferences: SharedPreferences,
) : BaseViewModel() {

    private val _closeEvent = MutableLiveData<Event<Appearance>>()
    val closeEvent: LiveData<Event<Appearance>> get() = _closeEvent

    private val _appearance = MutableLiveData<Appearance>()
    val appearance: LiveData<Appearance> get() = _appearance

    init {
        val appearanceMode = preferences.getInt(
            MoviesApplication.CURRENT_APPEARANCE,
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
        _appearance.value = getAppearance(appearanceMode)
    }

    fun selectAppearance(appearance: Appearance) {
        if (this.appearance.value == appearance) return

        _appearance.value = appearance
        close()
    }

    fun close() {
        val appearance = this.appearance.value ?: return
        _closeEvent.value = Event(appearance)
    }

}