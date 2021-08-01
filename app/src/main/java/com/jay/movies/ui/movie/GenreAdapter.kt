package com.jay.movies.ui.movie

import androidx.recyclerview.widget.DiffUtil
import com.jay.movies.R
import com.jay.movies.common.DataBindingAdapter
import com.jay.movies.model.UiGenreModel

class GenreAdapter : DataBindingAdapter<UiGenreModel>(DiffCallback(), GenreViewModel()) {

    class DiffCallback : DiffUtil.ItemCallback<UiGenreModel>() {
        override fun areItemsTheSame(
            oldItem: UiGenreModel,
            newItem: UiGenreModel,
        ): Boolean = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: UiGenreModel,
            newItem: UiGenreModel,
        ): Boolean = oldItem == newItem
    }

    override fun getItemViewType(position: Int) = R.layout.item_movie_genre
}