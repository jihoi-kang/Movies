package com.jay.movies.di

import com.jay.movies.api.MovieService
import com.jay.movies.data.movie.MovieRemoteDataSource
import com.jay.movies.data.movie.MovieRemoteDataSourceImpl
import com.jay.movies.data.movie.MovieRepository
import com.jay.movies.data.movie.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Reusable
    @ExperimentalCoroutinesApi
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
    ): MovieRepository = MovieRepositoryImpl(movieRemoteDataSource)

    @Provides
    @Reusable
    @ExperimentalCoroutinesApi
    fun provideMovieRemoteDataSource(
        movieService: MovieService,
    ): MovieRemoteDataSource = MovieRemoteDataSourceImpl(movieService)

}