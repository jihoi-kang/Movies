package com.jay.movies.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.common.DataBindingAdapter
import com.jay.movies.model.Movie

class MovieAdapter(
    viewModel: MovieViewModel,
) : DataBindingAdapter<Movie>(
    DiffCallback(), viewModel
) {

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int =
        R.layout.item_movie

}