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
import com.jay.movies.ui.movie.MovieEmptyViewModel
import com.jay.movies.ui.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFilterFragment : BaseFragment<MovieEmptyViewModel, FragmentMovieFilterBinding>(
    R.layout.fragment_movie_filter,
    MovieEmptyViewModel::class.java
) {
    private val TAG = this::class.java.simpleName

    private val movieViewModel by activityViewModels<MovieViewModel>()
    private var _allFilters = listOf<Filter>()
    private lateinit var _selectedFilter: Filter
    private lateinit var cacheFilter: Filter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _allFilters = movieViewModel.allFilters
        _selectedFilter = movieViewModel.selectedFilter
        cacheFilter = movieViewModel.selectedFilter

        initView()
    }

    private fun initView() {
        _allFilters.forEach { filter ->
            val chip = LayoutInflater.from(activity).inflate(R.layout.filter_chip, null) as Chip
            val id = View.generateViewId()
            chip.id = id
            chip.text = filter.name
            chip.setOnClickListener { _selectedFilter = filter }
            binding.filterChipGroup.addView(chip)
            if(_selectedFilter.name == filter.name) {
                binding.filterChipGroup.check(id)
            }
        }

        binding.fabSubmit.setOnClickListener {
            if(cacheFilter.name != _selectedFilter.name) {
                movieViewModel.selectedFilter = _selectedFilter
                movieViewModel.onClickSubmit()
            }
            findNavController().navigateUp()
        }
    }
}