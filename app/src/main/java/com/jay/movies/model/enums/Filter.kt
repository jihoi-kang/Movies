package com.jay.movies.model.enums

enum class Filter(
    val displayName: String,
    val sortBy: String,
) {
    NEW(displayName = "New", sortBy = "release_date.desc"),
    VOTE_AVERAGE(displayName = "Vote average", sortBy = "vote_average.desc"),
    POPULARITY(displayName = "Popularity", sortBy = "popularity.desc"),
}