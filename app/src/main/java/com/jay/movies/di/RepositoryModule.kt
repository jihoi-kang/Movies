package com.jay.movies.di

import com.jay.movies.api.DiscoverService
import com.jay.movies.data.DiscoverRepository
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
        discoverService: DiscoverService,
    ): DiscoverRepository {
        return DiscoverRepository(discoverService)
    }
}