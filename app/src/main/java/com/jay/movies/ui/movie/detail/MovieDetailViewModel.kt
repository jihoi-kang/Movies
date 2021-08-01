package com.jay.movies.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.model.UiMovieModel
import com.jay.movies.model.UiVideoModel
import com.jay.movies.model.asUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()

    val trailerVideoItems: LiveData<List<UiVideoModel>> =
        movieIdLiveData.switchMap { movieId ->
            liveData {
                val videoItems = movieRepository.getTrailers(movieId).map {
                    it.asUiModel()
                }
                emit(videoItems)
            }
        }

    private val _shareEvent = MutableLiveData<Event<String>>()
    val shareEvent: LiveData<Event<String>> = _shareEvent

    private val _showVideo = MutableLiveData<Event<String>>()
    val showVideo: LiveData<Event<String>> get() = _showVideo

    fun getMovieTrailer(movieId: Int) {
        movieIdLiveData.value = movieId
    }

    fun onVideoItemClick(videoUrl: String) {
        _showVideo.value = Event(videoUrl)
    }

    fun onClickShare(movie: UiMovieModel) {
        val movieRecommendation = StringBuilder().apply {
            append("✋Movie Recommendation!✋\n")
            append("Title: ${movie.title}\n")
            append("Release date: ${movie.releaseDate}\n")
            append("Vote average: ${movie.voteAverage}")
        }
        _shareEvent.value = Event(movieRecommendation.toString())
    }

}