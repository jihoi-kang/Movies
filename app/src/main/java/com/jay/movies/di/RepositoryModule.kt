package com.jay.movies.di

import com.jay.movies.api.client.DiscoverClient
import com.jay.movies.repository.DiscoverRepository
import com.jay.movies.room.MovieDao
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
    fun provideDiscoverRepository(
        discoverClient: DiscoverClient,
        movieDao: MovieDao
    ): DiscoverRepository {
        return DiscoverRepository(discoverClient, movieDao)
    }
}