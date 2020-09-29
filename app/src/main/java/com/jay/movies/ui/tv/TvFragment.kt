package com.jay.movies.ui.tv

import android.os.Bundle
import android.view.View
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentTvBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : BaseFragment<TvViewModel, FragmentTvBinding>(
    R.layout.fragment_tv,
    TvViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

}
