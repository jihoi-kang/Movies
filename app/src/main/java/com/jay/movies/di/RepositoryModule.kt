package com.jay.movies.di

import com.jay.movies.data.remote.api.MovieService
import com.jay.movies.data.remote.MovieRemoteDataSource
import com.jay.movies.data.remote.MovieRemoteDataSourceImpl
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.data.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Reusable
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
    ): MovieRepository = MovieRepositoryImpl(movieRemoteDataSource)

    @Provides
    @Reusable
    fun provideMovieRemoteDataSource(
        movieService: MovieService,
    ): MovieRemoteDataSource = MovieRemoteDataSourceImpl(movieService)

}