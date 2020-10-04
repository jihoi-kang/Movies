package com.jay.movies.api.client

import com.jay.movies.api.ApiResponse
import com.jay.movies.api.model.DiscoverMovieResponse
import com.jay.movies.api.service.DiscoverService
import com.jay.movies.api.transform

class DiscoverClient(private val service: DiscoverService) {

    fun fetchDiscoverMovie(
        page: Int,
        onResult: (response: ApiResponse<DiscoverMovieResponse>) -> Unit
    ) {
        this.service.fetchDiscoverMovie(page).transform(onResult)
    }

}