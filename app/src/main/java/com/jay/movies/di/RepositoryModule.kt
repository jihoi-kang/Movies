package com.jay.movies.di

import com.jay.movies.data.remote.MovieRemoteDataSource
import com.jay.movies.data.remote.MovieRemoteDataSourceImpl
import com.jay.movies.data.repository.MovieRepository
import com.jay.movies.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @Reusable
    abstract fun provideMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

}