package com.jay.movies.data

import com.google.common.truth.Truth.assertThat
import com.jay.movies.data.remote.MovieRemoteDataSource
import com.jay.movies.data.remote.api.response.GetGenresResponse
import com.jay.movies.data.remote.api.response.GetVideosResponse
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.data.repository.MovieRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MovieRepositoryTest {

    private val movieRemoteDataSource: MovieRemoteDataSource = mockk(relaxed = true)
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource)
    }

    @Test
    fun `When get Genres, Then success`() = runBlocking {
        val mockGenres: List<GetGenresResponse.Genre> = mockk()
        coEvery {
            movieRemoteDataSource.getGenres()
        } returns mockGenres

        val result = movieRepository.getGenres()
        assertThat(mockGenres).isEqualTo(result)
    }

    @Test
    fun `When get Trailers, Then success`() = runBlocking {
        val mockVideos: List<GetVideosResponse.Video> = mockk()
        coEvery {
            movieRemoteDataSource.getTrailers(any())
        } returns mockVideos

        val result = movieRepository.getTrailers(1)
        assertThat(mockVideos).isEqualTo(result)
    }

}