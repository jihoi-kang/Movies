package com.jay.movies.api.model

import com.jay.movies.model.Genre
import java.lang.Exception

sealed class GenreResult {
    data class Success(val data: List<Genre>) : GenreResult()
    data class Error(val error: Exception) : GenreResult()
}
