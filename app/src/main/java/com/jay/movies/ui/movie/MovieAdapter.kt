package com.jay.movies.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.common.DataBindingAdapter
import com.jay.movies.ui.model.UiPosterModel

class MovieAdapter(
    viewModel: MovieViewModel
) : DataBindingAdapter<UiPosterModel>(
    DiffCallback(), viewModel
) {

    class DiffCallback : DiffUtil.ItemCallback<UiPosterModel>() {
        override fun areItemsTheSame(oldItem: UiPosterModel, newItem: UiPosterModel): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: UiPosterModel, newItem: UiPosterModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemViewType(position: Int): Int =
        R.layout.item_poster

}