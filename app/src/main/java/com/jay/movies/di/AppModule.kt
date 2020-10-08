package com.jay.movies.di

import com.jay.movies.base.DefaultDispatcherProvider
import com.jay.movies.base.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

}