package com.jay.movies.ui.setting.theme

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import com.jay.movies.R
import com.jay.movies.base.BaseDialogFragment
import com.jay.movies.common.eventObserve
import com.jay.movies.databinding.DialogThemeBinding
import com.jay.movies.ui.system.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThemeDialogFragment : BaseDialogFragment<ThemeViewModel, DialogThemeBinding>(
    R.layout.dialog_theme,
    ThemeViewModel::class.java
) {

    private val systemVm: SystemViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Theme_Movies_Dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObserve()
    }

    private fun setupObserve() {
        viewModel.closeEvent.eventObserve(this) { dismiss() }
        viewModel.themeClickEvent.eventObserve(this) {
            checkAppearance(it)
            systemVm.onChangedTheme(it)
        }
    }

    private fun checkAppearance(mode: Int) {
        binding.rgRadio.check(when (mode) {
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> R.id.rb_use_device
            AppCompatDelegate.MODE_NIGHT_NO -> R.id.rb_light
            AppCompatDelegate.MODE_NIGHT_YES -> R.id.rb_dark
            else -> -1
        })
    }

}