package com.jay.movies.ui.movie.detail

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.common.DataBindingAdapter
import com.jay.movies.model.UiVideoModel

class VideoAdapter(
    viewModel: MovieDetailViewModel,
) : DataBindingAdapter<UiVideoModel>(
    DiffCallback(), viewModel
) {

    class DiffCallback : DiffUtil.ItemCallback<UiVideoModel>() {
        override fun areItemsTheSame(
            oldItem: UiVideoModel,
            newItem: UiVideoModel,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UiVideoModel,
            newItem: UiVideoModel,
        ): Boolean = oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int =
        R.layout.item_video

}