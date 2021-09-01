package com.jay.movies.model

import com.google.common.truth.Truth.assertThat
import com.jay.movies.data.remote.api.response.GetMoviesResponse
import org.junit.Test

class UiMovieModelTest {

    @Test
    fun `Given GetMoviesResponse Movie, When transfer ui model, Then UiMovieModel`() {
        // Given
        val movie = GetMoviesResponse.Movie(
            1,
            "/6KFrffYXYrgszW4FqhGvOEfg9P7.jpg",
            false,
            "some overview",
            "2021-07-28",
            listOf(1, 3),
            "my original title",
            "my original language",
            "my title",
            "/nprqOIEfiMMQx16lgKeLf3rmPrR.jpg",
            1f,
            1,
            false,
            4.5f,
        )
        val genresItems = listOf(
            UiGenreModel(1, "thriller"),
            UiGenreModel(2, "romance"),
            UiGenreModel(3, "comedy"),
            UiGenreModel(4, "action"),
        )

        // When
        val uiMovieModel = movie.asUiModel(genresItems)

        // Then
        uiMovieModel.run {
            assertThat(id).isEqualTo(1)
            assertThat(posterUrl).isEqualTo("https://image.tmdb.org/t/p/w780/6KFrffYXYrgszW4FqhGvOEfg9P7.jpg")
            assertThat(backgroundUrl).isEqualTo("https://image.tmdb.org/t/p/w780/nprqOIEfiMMQx16lgKeLf3rmPrR.jpg")
            assertThat(title).isEqualTo("my title")
            assertThat(genres.size).isEqualTo(2)
            assertThat(overview).isEqualTo("some overview")
            assertThat(releaseDate).isEqualTo("2021-07-28")
            assertThat(voteAverage).isEqualTo(4.5f)
        }
    }

}