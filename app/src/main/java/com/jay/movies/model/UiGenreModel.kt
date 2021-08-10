package com.jay.movies.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiGenreModel(
    val id: Int,
    val name: String,
) : Parcelable {

    companion object : DiffUtil.ItemCallback<UiGenreModel>() {
        override fun areItemsTheSame(oldItem: UiGenreModel, newItem: UiGenreModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiGenreModel, newItem: UiGenreModel): Boolean {
            return oldItem == newItem
        }
    }

}