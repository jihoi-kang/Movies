package com.jay.movies.ui.setting

import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>(
    R.layout.fragment_setting,
    SettingViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

}
