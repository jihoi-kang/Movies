package com.jay.movies.ui.movie.detail

import androidx.lifecycle.*
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
    state: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    val movieItem: LiveData<UiMovieModel> = state.getLiveData("movie")

    val trailerVideoItems: LiveData<List<UiVideoModel>> = movieItem.switchMap {
        liveData {
            movieRepository.getTrailers(it.id).map { video ->
                video.asUiModel()
            }
        }
    }

    private val _shareEvent = MutableLiveData<Event<String>>()
    val shareEvent: LiveData<Event<String>> = _shareEvent

    private val _showVideo = MutableLiveData<Event<String>>()
    val showVideo: LiveData<Event<String>> get() = _showVideo

    fun onVideoItemClick(videoUrl: String) {
        _showVideo.value = Event(videoUrl)
    }

    fun onClickShare() {
        val movieRecommendation = StringBuilder().apply {
            append("✋Movie Recommendation!✋\n")
            append("Title: ${movieItem.value?.title}\n")
            append("Release date: ${movieItem.value?.releaseDate}\n")
            append("Vote average: ${movieItem.value?.voteAverage}")
        }
        _shareEvent.value = Event(movieRecommendation.toString())
    }

}