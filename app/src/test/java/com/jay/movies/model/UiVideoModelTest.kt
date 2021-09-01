package com.jay.movies.model

import com.google.common.truth.Truth.assertThat
import com.jay.movies.data.remote.api.response.GetVideosResponse
import org.junit.Test

class UiVideoModelTest {

    @Test
    fun `Given GetVideosResponse Video, When transfer ui model, Then UiVideoModel`() {
        val video = GetVideosResponse.Video(
            "1",
            "some name",
            "Youtube",
            "LRMTr2VZcr8",
            1080,
            "Trailer"
        )

        val uiVideoModel = video.asUiModel()

        uiVideoModel.run {
            assertThat(id).isEqualTo("1")
            assertThat(name).isEqualTo("some name")
            assertThat(site).isEqualTo("Youtube")
            assertThat(thumbnailUrl).isEqualTo("https://img.youtube.com/vi/LRMTr2VZcr8/default.jpg")
            assertThat(videoUrl).isEqualTo("https://www.youtube.com/watch?v=LRMTr2VZcr8")
            assertThat(size).isEqualTo(1080)
            assertThat(type).isEqualTo("Trailer")
        }
    }

}