package com.jay.movies.ui.setting

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentSettingBinding
import com.jay.movies.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>(
    R.layout.fragment_setting,
    SettingViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserve()
    }

    private fun initView() {
        binding.clTheme.setOnClickListener {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES ->
                    viewModel.onClickTheme(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    viewModel.onClickTheme(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    private fun initObserve() {
        viewModel.themeEvent.eventObserve(viewLifecycleOwner) { theme ->
            AppCompatDelegate.setDefaultNightMode(theme)
        }
    }

}
