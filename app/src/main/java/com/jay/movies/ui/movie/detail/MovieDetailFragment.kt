package com.jay.movies.ui.movie.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.common.eventObserve
import com.jay.movies.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(
    R.layout.fragment_movie_detail,
    MovieDetailViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_share -> {
                    viewModel.onClickShare()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupObserve() {
        viewModel.shareEvent.eventObserve(viewLifecycleOwner) {
            startActivity(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it)
                type = "text/plain"
            })
        }
        viewModel.showVideo.eventObserve(viewLifecycleOwner) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

}
