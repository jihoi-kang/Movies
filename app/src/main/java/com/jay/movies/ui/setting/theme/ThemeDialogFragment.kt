package com.jay.movies.ui.setting.theme

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.jay.movies.R
import com.jay.movies.base.BaseDialogFragment
import com.jay.movies.databinding.DialogThemeBinding
import com.jay.movies.ui.system.SystemViewModel
import com.jay.movies.util.eventObserve
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
            systemVm.onChangedTheme(it)
        }
    }

}