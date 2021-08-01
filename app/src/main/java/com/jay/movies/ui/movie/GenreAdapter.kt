package com.jay.movies.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.data.remote.api.response.Genre
import com.jay.movies.common.DataBindingAdapter

class GenreAdapter : DataBindingAdapter<Genre>(DiffCallback(), GenreViewModel()) {

    class DiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(
            oldItem: Genre,
            newItem: Genre,
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Genre,
            newItem: Genre,
        ): Boolean = oldItem == newItem
    }

    override fun getItemViewType(position: Int) = R.layout.item_movie_genre
}