package com.jay.movies.ext

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.bindRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter("refresh")
fun SwipeRefreshLayout.bindRefreshListener(
    onRefreshListener: SwipeRefreshLayout.OnRefreshListener,
) = setOnRefreshListener(onRefreshListener)