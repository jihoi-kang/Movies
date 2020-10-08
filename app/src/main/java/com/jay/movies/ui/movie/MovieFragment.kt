package com.jay.movies.ui.movie

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.jay.movies.BR
import com.jay.movies.R
import com.jay.movies.api.model.MovieSearchResult
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

        with(binding) {
            setVariable(BR.movieVm, movieViewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        initView()
        initObserve()

        movieViewModel.movieResult.value?: let { movieViewModel.searchMovie(DEFAULT_SORT_BY) }
    }

    private fun initView() {
        movieAdapter = MovieAdapter(movieViewModel)
        binding.rvMovie.adapter = movieAdapter
        val layoutManager = LinearLayoutManager(context, VERTICAL, false)
        binding.rvMovie.layoutManager = layoutManager
        binding.rvMovie.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                movieViewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })

    }

    private fun initObserve() {
        movieViewModel.movieResult.observe(viewLifecycleOwner) { result ->
            binding.fabFilter.isVisible = true
            when(result) {
                is MovieSearchResult.Success -> result.data.toMutableList().let(movieAdapter::submitList)
                is MovieSearchResult.Error -> { }
            }
        }

        movieViewModel.itemEvent.eventObserve(viewLifecycleOwner) { movieId ->
            val action = MovieFragmentDirections.actionMovieToMovieDetail(movieId)
            findNavController().navigate(action)
        }
    }

    companion object {

        const val DEFAULT_SORT_BY = "popularity.desc"

    }

}