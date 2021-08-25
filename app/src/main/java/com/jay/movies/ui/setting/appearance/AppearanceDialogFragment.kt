package com.jay.movies.ui.setting.appearance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.jay.movies.R
import com.jay.movies.base.BaseDialogFragment
import com.jay.movies.common.eventObserve
import com.jay.movies.databinding.DialogAppearanceBinding
import com.jay.movies.model.enums.Appearance
import com.jay.movies.ui.system.SystemViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppearanceDialogFragment : BaseDialogFragment<AppearanceViewModel, DialogAppearanceBinding>(
    R.layout.dialog_appearance,
    AppearanceViewModel::class.java
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
        viewModel.closeEvent.eventObserve(viewLifecycleOwner) {
            systemVm.onChangedAppearance(it)
            dismiss()
        }
        viewModel.appearance.observe(viewLifecycleOwner, ::checkAppearance)
    }

    private fun checkAppearance(appearance: Appearance) {
        binding.rgRadio.check(appearance.idRes)
    }

}