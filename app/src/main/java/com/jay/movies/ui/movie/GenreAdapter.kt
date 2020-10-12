package com.jay.movies.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.common.DataBindingAdapter
import com.jay.movies.model.Genre

class GenreAdapter : DataBindingAdapter<Genre>(DiffCallback(), GenreViewModel()) {

    class DiffCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_movie_genre
}