package com.jay.movies.ui.movie.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jay.movies.BR
import com.jay.movies.R
import com.jay.movies.api.Api
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentMovieDetailBinding
import com.jay.movies.ui.movie.MovieAdapter
import com.jay.movies.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>(
    R.layout.fragment_movie_detail,
    MovieDetailViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private lateinit var videoAdapter: VideoAdapter

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovie(args.movieId)

        initView()
        initMenu()
        initObserve()
    }

    private fun initView() {
        videoAdapter = VideoAdapter(viewModel)
        binding.rvVideo.adapter = videoAdapter
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rvVideo.layoutManager = layoutManager
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
            it.videos?.toMutableList().let(videoAdapter::submitList)
        }

        viewModel.shareEvent.eventObserve(viewLifecycleOwner) {
            startActivity(Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, it)
                type = "text/plain"
            })
        }

        viewModel.itemEvent.eventObserve(viewLifecycleOwner) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Api.getYoutubeVideoPath(it))))
        }
    }

}
