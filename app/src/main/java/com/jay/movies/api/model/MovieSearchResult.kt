package com.jay.movies.api.model

import com.jay.movies.model.Movie
import java.lang.Exception

sealed class MovieSearchResult {
    data class Success(val data: List<Movie>) : MovieSearchResult()
    data class Error(val error: Exception) : MovieSearchResult()
}
