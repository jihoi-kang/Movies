package com.jay.movies.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jay.movies.R
import com.jay.movies.api.Api.getBackdropPath
import com.jay.movies.model.Genre
import com.jay.movies.ui.movie.GenreAdapter
import com.jay.movies.ui.movie.MovieViewModel

@BindingAdapter(value = ["isRefreshing"])
fun SwipeRefreshLayout.bindRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.bindRefreshListener(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) =
    setOnRefreshListener(onRefreshListener)

@BindingAdapter(value = ["floatText"])
fun TextView.bindSetText(value: Float) {
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

@BindingAdapter("allGenres", "genreIds")
fun RecyclerView.bindHome(allGenres: List<Genre>, genreIds: List<Int>?) {
    if (genreIds?.isNotEmpty() == true) {
        isVisible = true
        FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
        }.let {
            this.layoutManager = it
        }
        val genres = mutableListOf<Genre>()
        genreIds.forEach { id ->
            allGenres.forEach { genre ->
                if(genre.id == id) genres.add(genre)
            }
        }

        this.adapter = GenreAdapter()
        (this.adapter as GenreAdapter).run {
            submitList(genres)
        }
    } else {
        isGone = true
    }
}