package com.jay.movies.ui.movie.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jay.movies.BR
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

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovie(args.movieId)

        initMenu()
        initObserve()
    }

    private fun initMenu() {
        binding.toolbar.run {
            setNavigationOnClickListener { findNavController().navigateUp() }
            setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.menu_share -> {
                        viewModel.onClickShare()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun initObserve() {
        viewModel.movieContents.observe(viewLifecycleOwner) {
            binding.setVariable(BR.item, it)
        }
    }

}
