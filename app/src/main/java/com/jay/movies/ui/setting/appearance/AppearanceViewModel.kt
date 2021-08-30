package com.jay.movies.ui.setting.appearance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.model.enums.Appearance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppearanceViewModel @Inject constructor(
    state: SavedStateHandle,
) : BaseViewModel() {

    private val _closeEvent = MutableLiveData<Event<Appearance>>()
    val closeEvent: LiveData<Event<Appearance>> get() = _closeEvent

    private val _appearance = MutableLiveData<Appearance>()
    val appearance: LiveData<Appearance> get() = _appearance

    init {
        _appearance.value = state.get<Appearance>("appearance")
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