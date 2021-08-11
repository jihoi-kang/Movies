package com.jay.movies.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jay.movies.base.BaseViewModel
import com.jay.movies.common.Event
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.model.UiMovieModel
import com.jay.movies.model.UiVideoModel
import com.jay.movies.model.asUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    state: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : BaseViewModel() {

    private val movie: UiMovieModel = state.get<UiMovieModel>("movie")!!

    private val _trailerVideoItems = MutableLiveData<List<UiVideoModel>>(emptyList())
    val trailerVideoItems: LiveData<List<UiVideoModel>> get() = _trailerVideoItems

    private val _shareEvent = MutableLiveData<Event<String>>()
    val shareEvent: LiveData<Event<String>> = _shareEvent

    private val _showVideo = MutableLiveData<Event<String>>()
    val showVideo: LiveData<Event<String>> get() = _showVideo

    init {
        viewModelScope.launch {
            _trailerVideoItems.value = movieRepository.getTrailers(movie.id).map {
                it.asUiModel()
            }
        }
    }

    fun onVideoItemClick(videoUrl: String) {
        _showVideo.value = Event(videoUrl)
    }

    fun onClickShare() {
        val movieRecommendation = StringBuilder().apply {
            append("✋Movie Recommendation!✋\n")
            append("Title: ${movie.title}\n")
            append("Release date: ${movie.releaseDate}\n")
            append("Vote average: ${movie.voteAverage}")
        }
        _shareEvent.value = Event(movieRecommendation.toString())
    }

}