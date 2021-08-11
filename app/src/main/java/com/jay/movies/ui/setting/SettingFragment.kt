package com.jay.movies.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.jay.movies.BR
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.common.eventObserve
import com.jay.movies.databinding.FragmentSettingBinding
import com.jay.movies.ui.system.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>(
    R.layout.fragment_setting,
    SettingViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private val systemViewModel: SystemViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        binding.setVariable(BR.systemVm, systemViewModel)
    }

    private fun setupObserve() {
        viewModel.themeClickEvent.eventObserve(viewLifecycleOwner) {
            val action = SettingFragmentDirections.actionSettingToTheme()
            findNavController().navigate(action)
        }
    }

}
