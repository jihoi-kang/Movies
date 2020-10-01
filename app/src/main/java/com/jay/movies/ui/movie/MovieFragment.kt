package com.jay.movies.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        with(binding) {
            setVariable(BR.movieVm, movieViewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        initView()
        initObserve()
    }

    private fun initView() {
        movieAdapter = MovieAdapter(movieViewModel)
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = movieAdapter
        }
    }

    private fun initObserve() {
        movieViewModel.posterList.observe(viewLifecycleOwner) { posters ->
            binding.fabFilter.isVisible = true

            posters.filter { poster ->
                // todo : implement need
                true
            }.let(movieAdapter::submitList)
        }

        movieViewModel.itemEvent.eventObserve(viewLifecycleOwner) { posterId ->
            val action = MovieFragmentDirections.actionMovieToPosterDetail(posterId)
            findNavController().navigate(action)
        }
    }

}