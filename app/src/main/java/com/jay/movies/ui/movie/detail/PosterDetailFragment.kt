package com.jay.movies.ui.movie.detail

import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentPosterDetailBinding
import com.jay.movies.databinding.FragmentTvBinding
import com.jay.movies.ui.tv.TvViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PosterDetailFragment : BaseFragment<PosterDetailViewModel, FragmentPosterDetailBinding>(
    R.layout.fragment_poster_detail,
    PosterDetailViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

}
