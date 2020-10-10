package com.jay.movies.model

data class Filter(
    val name: String = "",
    val sortByName: String = getSortBy(name)
)

fun getSortBy(name: String) =
    when(name) {
        "New" -> "release_date.desc"
        "Vote average" -> "vote_average.desc"
        else -> "popularity.desc" // default
    }