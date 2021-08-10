package com.jay.movies.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiVideoModel(
    val id: String,
    val name: String,
    val site: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val size: Int,
    val type: String,
) : Parcelable {

    companion object : DiffUtil.ItemCallback<UiVideoModel>() {
        override fun areItemsTheSame(oldItem: UiVideoModel, newItem: UiVideoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiVideoModel, newItem: UiVideoModel): Boolean {
            return oldItem == newItem
        }
    }

}