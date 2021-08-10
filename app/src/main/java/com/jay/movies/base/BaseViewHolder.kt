package com.jay.movies.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jay.movies.BR

open class BaseViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context)
        .inflate(layoutResId, parent, false)
) {
    protected val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    fun onBind(item: Any?) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}