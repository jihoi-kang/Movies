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
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

//    @Provides
//    @Reusable
//    @ExperimentalCoroutinesApi
//    fun provideMovieRepositoryTemp(
//        movieService: MovieService,
//        movieDao: MovieDao,
//        genreDao: GenreDao,
//    ): MovieRepositoryTemp = MovieRepositoryTemp(
//        movieService,
//        movieDao,
//        genreDao
//    )

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