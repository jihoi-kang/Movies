package com.jay.movies

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MoviesApplication : Application() {

    @Inject
    lateinit var preference: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val theme =
            preference.getInt(CURRENT_APPEARANCE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(theme)
    }

    companion object {
        const val CURRENT_APPEARANCE = "current_appearance"
    }

}