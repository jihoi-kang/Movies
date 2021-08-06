package com.jay.movies.ui.movie.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jay.movies.BR
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentMovieDetailBinding
import com.jay.movies.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(
    R.layout.fragment_movie_detail,
    MovieDetailViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private val args: MovieDetailFragmentArgs by navArgs()

    private val videoAdapter: VideoAdapter by lazy {
        VideoAdapter(viewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieTrailer(args.movie.id)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        binding.setVariable(BR.item, args.movie)
        binding.rvVideo.adapter = videoAdapter
        binding.toolbar.run {
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_share -> {
                        val movie = binding.item ?: return@setOnMenuItemClickListener false
                        viewModel.onClickShare(movie)
                        true
                    }
                    else -> false
                }
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
        viewModel.trailerVideoItems.observe(viewLifecycleOwner, {
            videoAdapter.submitList(it)
            videoAdapter.notifyDataSetChanged()
        })
    }

}
