package com.jay.movies.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.common.DataBindingAdapter
import com.jay.movies.model.UiMovieModel

class MovieAdapter(
    viewModel: MovieViewModel,
) : DataBindingAdapter<UiMovieModel>(
    DiffCallback(), viewModel
) {

    class DiffCallback : DiffUtil.ItemCallback<UiMovieModel>() {
        override fun areItemsTheSame(
            oldItem: UiMovieModel,
            newItem: UiMovieModel,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UiMovieModel,
            newItem: UiMovieModel,
        ): Boolean = oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int =
        R.layout.item_movie

}