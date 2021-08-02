package com.jay.movies.ext

import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jay.movies.R
import com.jay.movies.model.UiGenreModel
import com.jay.movies.model.UiMovieModel
import com.jay.movies.model.UiVideoModel
import com.jay.movies.ui.movie.GenreAdapter
import com.jay.movies.ui.movie.MovieAdapter
import com.jay.movies.ui.movie.detail.VideoAdapter

@BindingAdapter("allGenres", "genreIds")
fun RecyclerView.bindHome(allGenres: List<UiGenreModel>, genreIds: List<Int>?) {
    if (genreIds?.isNotEmpty() == true) {
        isVisible = true
        FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
        }.let {
            this.layoutManager = it
        }
        val genres = mutableListOf<UiGenreModel>()
        genreIds.forEach { id ->
            allGenres.forEach { genre ->
                if (genre.id == id) {
                    genres.add(genre)
                }
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
            adapter.submitList(items as List<UiMovieModel>)
            adapter.notifyDataSetChanged()
        }
        is VideoAdapter -> {
            adapter.submitList(items as List<UiVideoModel>)
            adapter.notifyDataSetChanged()
        }
    }
}