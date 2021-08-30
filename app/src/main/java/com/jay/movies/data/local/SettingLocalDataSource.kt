package com.jay.movies.data.local

import com.jay.movies.model.enums.Appearance

interface SettingLocalDataSource {
    fun getAppearance(): Appearance
    fun setAppearance(appearance: Appearance)
}