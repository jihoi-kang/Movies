package com.jay.movies.ext

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.movies.base.BaseListAdapter
import com.jay.movies.base.BaseViewModel

@BindingAdapter(
    "items",
    "itemLayout",
    "diffCallback",
    "viewModel",
    requireAll = false
)
fun RecyclerView.bindSetAdapter(
    items: List<Any>? = null,
    @LayoutRes layoutResId: Int? = null,
    diffCallback: DiffUtil.ItemCallback<Any>? = null,
    viewModel: BaseViewModel? = null,
) {
    // required parameter check
    items ?: error("items is null")
    layoutResId ?: error("layoutResId is null")
    diffCallback ?: error("diffCallback is null")

    // set adapter
    val baseListAdapter = this.adapter as? BaseListAdapter
        ?: BaseListAdapter(layoutResId, diffCallback).also {
            it.viewModel = viewModel
            this.adapter = it
        }

    baseListAdapter.submitList(items)
}
