package com.jay.movies.ui.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.data.MovieRepository
import com.jay.movies.model.Movie
import kotlinx.coroutines.flow.flowOn

class MovieDetailViewModel @ViewModelInject constructor(
    dispatcherProvider: DispatcherProvider,
    movieRepository: MovieRepository
) : BaseViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()

    val movieContents: LiveData<Movie> = _id.switchMap { movieId ->
        liveData {
            val movie = movieRepository.getById(movieId)

            emitSource(movie.flowOn(dispatcherProvider.default()).asLiveData())
        }
    }

    fun getMovie(movieId: Int) {
        _id.value = movieId
    }

    fun onClickShare() {
        // contents
    }

}