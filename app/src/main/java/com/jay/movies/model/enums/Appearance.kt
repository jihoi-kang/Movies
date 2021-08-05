package com.jay.movies.model.enums

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import com.jay.movies.R

enum class Appearance(
    val mode: Int,
    @StringRes val textRes: Int,
) {
    SYSTEM(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, R.string.theme_use_device),
    LIGHT(AppCompatDelegate.MODE_NIGHT_NO, R.string.theme_light),
    NIGHT(AppCompatDelegate.MODE_NIGHT_YES, R.string.theme_dark),
}

fun getAppearance(mode: Int): Appearance =
    when (mode) {
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> Appearance.SYSTEM
        AppCompatDelegate.MODE_NIGHT_NO -> Appearance.LIGHT
        else -> Appearance.NIGHT
    }