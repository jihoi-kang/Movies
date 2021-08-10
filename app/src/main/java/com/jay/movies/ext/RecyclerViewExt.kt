package com.jay.movies.ext

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.movies.base.BaseListAdapter

@BindingAdapter("items", "itemLayout", "diffCallback")
fun RecyclerView.bindItems(
    items: List<Any>,
    @LayoutRes layoutResId: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
) {
    val baseListAdapter = this.adapter as? BaseListAdapter
        ?: BaseListAdapter(layoutResId, diffCallback).also {
            this.adapter = it
        }

    baseListAdapter.submitList(items)
}
