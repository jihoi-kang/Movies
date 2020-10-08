package com.jay.movies.di

import com.jay.movies.api.Api.BASE_TMDB_API_PATH
import com.jay.movies.api.DiscoverService
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
    fun provideDiscoverService(): DiscoverService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_TMDB_API_PATH)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiscoverService::class.java)
    }
}