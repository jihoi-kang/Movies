package com.jay.movies.di

import com.jay.movies.api.service.DiscoverService
import com.jay.movies.api.client.DiscoverClient
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Provides
    @Reusable
    fun provideDiscoverClient(
        discoverService: DiscoverService
    ): DiscoverClient {
        return DiscoverClient(discoverService)
    }

    @Provides
    @Reusable
    fun provideDiscoverService(): DiscoverService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiscoverService::class.java)
    }
}