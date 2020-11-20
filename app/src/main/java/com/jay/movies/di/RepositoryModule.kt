package com.jay.movies.di

import com.jay.movies.api.MovieService
import com.jay.movies.data.MovieRepository
import com.jay.movies.room.GenreDao
import com.jay.movies.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Reusable
    @ExperimentalCoroutinesApi
    fun provideMovieRepository(
        movieService: MovieService,
        movieDao: MovieDao,
        genreDao: GenreDao,
    ): MovieRepository = MovieRepository(
        movieService,
        movieDao,
        genreDao
    )

}