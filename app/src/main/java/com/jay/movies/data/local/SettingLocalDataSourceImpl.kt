package com.jay.movies.data.local

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import com.jay.movies.MoviesApplication
import com.jay.movies.model.enums.Appearance
import javax.inject.Inject

class SettingLocalDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences,
) : SettingLocalDataSource {

    override fun getAppearance(): Appearance {
        val appearanceMode = preferences.getInt(MoviesApplication.CURRENT_APPEARANCE,
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        return when (appearanceMode) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> Appearance.SYSTEM
            AppCompatDelegate.MODE_NIGHT_NO -> Appearance.LIGHT
            else -> Appearance.NIGHT
        }
    }

    override fun setAppearance(appearance: Appearance) {
        preferences.edit {
            putInt(MoviesApplication.CURRENT_APPEARANCE, appearance.mode)
        }
    }
}