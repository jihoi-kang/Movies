package com.jay.movies.common

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.jay.movies.BR

class DataBindingViewHolder<T>(
    val binding: ViewDataBinding,
    private val viewModel: ViewModel
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

}