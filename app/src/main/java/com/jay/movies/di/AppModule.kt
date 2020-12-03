package com.jay.movies.di

import android.content.Context
import android.content.SharedPreferences
import com.jay.movies.base.DefaultDispatcherProvider
import com.jay.movies.base.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Provides
    @Reusable
    fun provideDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Reusable
    fun providePreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            "",
            Context.MODE_PRIVATE
        )

}