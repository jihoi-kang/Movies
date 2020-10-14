package com.jay.movies.di

import com.jay.movies.api.MovieService
import com.jay.movies.data.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Reusable
    fun provideMovieRepository(
        movieService: MovieService,
    ): MovieRepository {
        return MovieRepository(movieService)
    }
}