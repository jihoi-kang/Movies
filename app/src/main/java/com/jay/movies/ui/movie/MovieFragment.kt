package com.jay.movies.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.common.eventObserve
import com.jay.movies.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<MovieViewModel, FragmentMovieBinding>(
    R.layout.fragment_movie,
    MovieViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private val sharedViewModel by activityViewModels<MovieSharedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        binding.rvMovie.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.rvMovie.layoutManager?.let {
                    val totalItemCount = it.itemCount
                    val visibleItemCount = it.childCount
                    val lastVisibleItem = (it as LinearLayoutManager).findLastVisibleItemPosition()

                    viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
                }
            }
        })
    }

    private fun setupObserve() {
        viewModel.openFilter.eventObserve(viewLifecycleOwner) {
            val action = MovieFragmentDirections.actionMovieToMovieFilter()
            findNavController().navigate(action)
        }
        viewModel.openMovieEvent.eventObserve(viewLifecycleOwner) { movie ->
            val action = MovieFragmentDirections.actionMovieToMovieDetail(movie)
            findNavController().navigate(action)
        }
        sharedViewModel.currentFilter.eventObserve(viewLifecycleOwner) {
            viewModel.updateFilter(it)
        }
    }

}