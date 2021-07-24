package com.jay.movies.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.movie.MovieRepository
import com.jay.movies.model.Movie
import com.jay.movies.model.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()

    val trailerVideoItems: LiveData<List<Video>> = movieIdLiveData.switchMap { movieId ->
        liveData {
            val videoItems = movieRepository.fetchTrailers(movieId)
            emit(videoItems)
        }
    }

    private val _shareEvent = MutableLiveData<Event<String>>()
    val shareEvent: LiveData<Event<String>> = _shareEvent

    private val _videoItemEvent = MutableLiveData<Event<String>>()
    val videoItemEvent: LiveData<Event<String>> get() = _videoItemEvent

    fun getMovieTrailer(movieId: Int) {
        movieIdLiveData.value = movieId
    }

    fun onVideoItemClick(videoKey: String) {
        _videoItemEvent.value = Event(videoKey)
    }

    fun onClickShare(movie: Movie) {
        val movieRecommendation = StringBuilder().apply {
            append("✋Movie Recommendation!✋\n")
            append("Title: ${movie.title}\n")
            append("Release date: ${movie.release_date}\n")
            append("Vote average: ${movie.vote_average}")
        }
        _shareEvent.value = Event(movieRecommendation.toString())
    }

}