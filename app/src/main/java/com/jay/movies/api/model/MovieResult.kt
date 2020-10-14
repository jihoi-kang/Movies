package com.jay.movies.api.model

import com.jay.movies.model.Movie
import java.lang.Exception

sealed class MovieResult {
    data class Success(val data: List<Movie>) : MovieResult()
    data class Error(val error: Exception) : MovieResult()
}
