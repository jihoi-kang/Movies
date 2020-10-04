package com.jay.movies.ui.movie.detail

import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(
    R.layout.fragment_movie_detail,
    MovieDetailViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

}
