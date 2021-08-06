package com.jay.movies.ext

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.jay.movies.common.DataBindingAdapter
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
//            submitList(genres)
        }
    } else {
        isGone = true
    }
}