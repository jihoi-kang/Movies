package com.jay.movies.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

open class BaseListAdapter(
    @LayoutRes private val layoutResId: Int?,
    diffCallback: DiffUtil.ItemCallback<Any>,
) : ListAdapter<Any, BaseViewHolder>(diffCallback) {
    var viewModel: BaseViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        layoutResId ?: error("layoutResId is null. It cannot be null!")
        return BaseViewHolder(layoutResId = layoutResId, parent = parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(getItem(position), viewModel)
    }

}