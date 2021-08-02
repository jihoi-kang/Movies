package com.jay.movies.di

import android.content.Context
import android.content.SharedPreferences
import com.jay.movies.base.DefaultDispatcherProvider
import com.jay.movies.base.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    private const val NAME = "Movies"

    @Provides
    @Reusable
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Reusable
    fun providePreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

}