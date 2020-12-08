package com.jay.movies.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.jay.movies.BR
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentMovieBinding
import com.jay.movies.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<MovieEmptyViewModel, FragmentMovieBinding>(
    R.layout.fragment_movie,
    MovieEmptyViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private lateinit var movieAdapter: MovieAdapter

    val movieViewModel by activityViewModels<MovieViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setVariable(BR.movieVm, movieViewModel)

        setupView()
        setupObserve()
    }

    private fun setupView() {
        movieAdapter = MovieAdapter(movieViewModel)
        binding.rvMovie.adapter = movieAdapter
        binding.rvMovie.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                binding.rvMovie.layoutManager?.let {
                    val totalItemCount = it.itemCount
                    val visibleItemCount = it.childCount
                    val lastVisibleItem = (it as LinearLayoutManager).findLastVisibleItemPosition()

                    movieViewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
                }
            }
        })

    }

    private fun setupObserve() {
        movieViewModel.openFilter.eventObserve(viewLifecycleOwner) {
            val action = MovieFragmentDirections.actionMovieToMovieFilter()
            findNavController().navigate(action)
        }
        movieViewModel.openMovieEvent.eventObserve(viewLifecycleOwner) { movie ->
            val action = MovieFragmentDirections.actionMovieToMovieDetail(movie)
            findNavController().navigate(action)
        }
    }

}