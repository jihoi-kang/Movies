package com.jay.movies.ui.movie.detail

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.data.remote.api.response.Video
import com.jay.movies.common.DataBindingAdapter

class VideoAdapter(
    viewModel: MovieDetailViewModel,
) : DataBindingAdapter<Video>(
    DiffCallback(), viewModel
) {

    class DiffCallback : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(
            oldItem: Video,
            newItem: Video,
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Video,
            newItem: Video,
        ): Boolean = oldItem == newItem
    }

    override fun getItemViewType(position: Int): Int =
        R.layout.item_video

}