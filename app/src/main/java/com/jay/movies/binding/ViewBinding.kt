package com.jay.movies.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jay.movies.R
import com.jay.movies.api.Api.getBackdropPath

@BindingAdapter(value = ["isRefreshing"])
fun SwipeRefreshLayout.bindRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.bindRefreshListener(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) =
    setOnRefreshListener(onRefreshListener)

@BindingAdapter(value = ["text"])
fun TextView.bindSetText(value: Any) {
    text = value.toString()
}

@BindingAdapter(value = ["moviePostImage"])
fun ImageView.bindPosterImage(posterPath: String?) {
    Glide.with(this)
        .load(getBackdropPath(posterPath))
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_error_outline_grey)
        ).into(this)
}

@BindingAdapter(value = ["movieBackDropImage"])
fun ImageView.bindBackDropImage(backDropPath: String?) {
    Glide.with(this)
        .load(getBackdropPath(backDropPath))
        .into(this)
}