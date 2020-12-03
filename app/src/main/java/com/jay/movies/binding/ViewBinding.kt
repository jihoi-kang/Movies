package com.jay.movies.binding

import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
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

@BindingAdapter("isRefreshing")
fun SwipeRefreshLayout.bindRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter("onRefresh")
fun SwipeRefreshLayout.bindRefreshListener(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) =
    setOnRefreshListener(onRefreshListener)

@BindingAdapter("moviePostImage")
fun ImageView.bindPosterImage(posterPath: String?) {
    Glide.with(this)
        .load(getBackdropPath(posterPath))
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_error_outline_grey)
        ).into(this)
}

@BindingAdapter("movieBackDropImage")
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
                if (genre.id == id) genres.add(genre)
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

@BindingAdapter("themeText")
fun TextView.bindThemeText(theme: Int) {
    setText(when (theme) {
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> R.string.theme_use_device
        AppCompatDelegate.MODE_NIGHT_NO -> R.string.theme_light
        else -> R.string.theme_dark
    })
}

@BindingAdapter("check")
fun RadioGroup.bindCheck(theme: Int) {
    check(when (theme) {
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM -> R.id.rb_use_device
        AppCompatDelegate.MODE_NIGHT_NO -> R.id.rb_light
        AppCompatDelegate.MODE_NIGHT_YES -> R.id.rb_dark
        else -> -1
    })
}