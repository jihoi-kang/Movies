package com.jay.movies.binding

import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jay.movies.R
import com.jay.movies.api.Api
import com.jay.movies.api.Api.getBackdropPath
import com.jay.movies.api.response.Genre
import com.jay.movies.api.response.Movie
import com.jay.movies.api.response.Video
import com.jay.movies.ui.movie.GenreAdapter
import com.jay.movies.ui.movie.MovieAdapter
import com.jay.movies.ui.movie.detail.VideoAdapter

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

@BindingAdapter("videoThumbnailImage")
fun ImageView.bindVideoThumbnailImage(key: String) {
    Glide.with(context)
        .load(Api.getYoutubeThumbnailPath(key))
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

@BindingAdapter("items")
fun RecyclerView.bindItems(items: List<Any>?) {
    items ?: return

    when (val adapter = adapter) {
        is MovieAdapter -> {
            adapter.submitList(items as List<Movie>)
            adapter.notifyDataSetChanged()
        }
        is VideoAdapter -> {
            adapter.submitList(items as List<Video>)
            adapter.notifyDataSetChanged()
        }
    }
}