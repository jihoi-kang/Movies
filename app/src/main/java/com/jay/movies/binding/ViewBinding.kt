package com.jay.movies.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jay.movies.R

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
        .load("https://image.tmdb.org/t/p/w780$posterPath")
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_movie_black)
        ).into(this)
}