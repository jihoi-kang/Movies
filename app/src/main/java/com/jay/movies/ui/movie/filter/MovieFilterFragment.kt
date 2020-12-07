package com.jay.movies.ui.movie.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.databinding.FragmentMovieFilterBinding
import com.jay.movies.model.Filter
import com.jay.movies.ui.movie.MovieViewModel
import com.jay.movies.util.eventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFilterFragment : BaseFragment<MovieFilterViewModel, FragmentMovieFilterBinding>(
    R.layout.fragment_movie_filter,
    MovieFilterViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private val movieViewModel by activityViewModels<MovieViewModel>()
    private val cacheFilter: Filter by lazy {
        movieViewModel.currentFilter.value ?: DEFAULT_FILTER
    }

    private val allFilters: List<Filter> = listOf(
        Filter("Popularity"),
        Filter("New"),
        Filter("Vote average")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setupFilter(
            viewModel.currentFilter.value ?: cacheFilter
        )

        setupView()
        setupObserve()
    }

    private fun setupView() {
        allFilters.forEach { filter ->
            val chip = LayoutInflater.from(activity).inflate(R.layout.filter_chip, null) as Chip
            val id = View.generateViewId()
            chip.id = id
            chip.text = filter.name
            chip.setOnClickListener { viewModel.setupFilter(filter) }
            binding.filterChipGroup.addView(chip)
            if (viewModel.currentFilter.value!!.name == filter.name) {
                binding.filterChipGroup.check(id)
            }
        }
    }

    private fun setupObserve() {
        viewModel.submitEvent.eventObserve(viewLifecycleOwner) {
            val currentFilter = viewModel.currentFilter.value ?: return@eventObserve
            if (cacheFilter.name != currentFilter.name) {
                movieViewModel.setupFilter(currentFilter)
            }
            findNavController().navigateUp()
        }
    }

    companion object {
        val DEFAULT_FILTER = Filter("Popularity")
    }

}