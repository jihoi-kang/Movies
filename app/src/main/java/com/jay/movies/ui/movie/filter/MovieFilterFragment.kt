package com.jay.movies.ui.movie.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.jay.movies.R
import com.jay.movies.base.BaseFragment
import com.jay.movies.common.eventObserve
import com.jay.movies.databinding.FragmentMovieFilterBinding
import com.jay.movies.model.enums.Filter
import com.jay.movies.ui.movie.MovieSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFilterFragment : BaseFragment<MovieFilterViewModel, FragmentMovieFilterBinding>(
    R.layout.fragment_movie_filter,
    MovieFilterViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private val sharedViewModel by activityViewModels<MovieSharedViewModel>()
    private val cacheFilter: Filter by lazy {
        sharedViewModel.currentFilter.value?.peekContent() ?: Filter.POPULARITY
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupObserve()
    }

    private fun setupUi() {
        viewModel.setupFilter(cacheFilter)
        // set all filters
        var checkedChipId = 0
        ALL_FILTERS.forEach { filter ->
            val chip =
                (LayoutInflater.from(context).inflate(R.layout.filter_chip, null) as Chip).apply {
                    id = View.generateViewId()
                    text = filter.displayName
                    setOnClickListener { viewModel.setupFilter(filter) }
                }
            binding.filterChipGroup.addView(chip)
            if (cacheFilter.displayName == filter.displayName) {
                checkedChipId = chip.id
            }
        }
        binding.filterChipGroup.check(checkedChipId)
    }

    private fun setupObserve() {
        viewModel.submitEvent.eventObserve(viewLifecycleOwner) {
            val currentFilter = viewModel.currentFilter.value ?: return@eventObserve
            if (cacheFilter.displayName != currentFilter.displayName) {
                sharedViewModel.setupFilter(currentFilter)
            }
            findNavController().navigateUp()
        }
    }

    companion object {
        private val ALL_FILTERS: List<Filter> = listOf(
            Filter.POPULARITY,
            Filter.NEW,
            Filter.VOTE_AVERAGE
        )
    }

}