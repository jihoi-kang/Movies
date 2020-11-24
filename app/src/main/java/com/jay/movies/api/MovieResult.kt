package com.jay.movies.api

import com.jay.movies.model.Movie

sealed class MovieResult {
    data class Success(val data: List<Movie>) : MovieResult()
    data class Error(val error: Exception) : MovieResult()
}
