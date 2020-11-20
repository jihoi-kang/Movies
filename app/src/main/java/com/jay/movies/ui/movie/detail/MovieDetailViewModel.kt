package com.jay.movies.ui.movie.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jay.movies.base.BaseViewModel
import com.jay.movies.base.DispatcherProvider
import com.jay.movies.common.Event
import com.jay.movies.data.MovieRepository
import com.jay.movies.model.Movie
import kotlinx.coroutines.flow.flowOn

class MovieDetailViewModel @ViewModelInject constructor(
    dispatcherProvider: DispatcherProvider,
    movieRepository: MovieRepository,
) : BaseViewModel() {

    private val _id: MutableLiveData<Int> = MutableLiveData()

    private val _shareEvent = MutableLiveData<Event<String>>()
    val shareEvent: LiveData<Event<String>> = _shareEvent

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
        movieContents.value?.let { movie ->
            val movieRecommendation = StringBuilder().apply {
                append("✋Movie Recommendation!✋\n")
                append("Title: ${movie.title}\n")
                append("Release date: ${movie.release_date}\n")
                append("Vote average: ${movie.vote_average}")
            }
            _shareEvent.value = Event(movieRecommendation.toString())
        }
    }

    private val _itemEvent = MutableLiveData<Event<String>>()
    val itemEvent: LiveData<Event<String>> get() = _itemEvent

    fun onClickItem(videoKey: String) {
        _itemEvent.value = Event(videoKey)
    }

}