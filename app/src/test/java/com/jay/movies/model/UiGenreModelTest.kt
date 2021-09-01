package com.jay.movies.model

import com.google.common.truth.Truth.assertThat
import com.jay.movies.data.remote.api.response.GetGenresResponse
import org.junit.Test

class UiGenreModelTest {

    @Test
    fun `Given GetGenresResponse Genre, When transfer ui model, Then UiGenreModel`() {
        // Given
        val genre = GetGenresResponse.Genre(1, "thriller")

        // When
        val uiGenreModel = genre.asUiModel()

        // Then
        uiGenreModel.run {
            assertThat(id).isEqualTo(1)
            assertThat(name).isEqualTo("thriller")
        }
    }

}