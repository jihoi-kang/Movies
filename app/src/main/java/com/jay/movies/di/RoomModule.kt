package com.jay.movies.di

import android.content.Context
import androidx.room.Room
import com.jay.movies.room.AppDatabase
import com.jay.movies.room.GenreDao
import com.jay.movies.room.MovieDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class RoomModule {

    @Provides
    @Reusable
    fun provideRoom(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "Movies.db")
        .allowMainThreadQueries()
        .build()

    @Provides
    @Reusable
    fun provideMovieDao(
        appDatabase: AppDatabase,
    ): MovieDao = appDatabase.movieDao()

    @Provides
    @Reusable
    fun provideGenreDao(
        appDatabase: AppDatabase,
    ): GenreDao = appDatabase.genreDao()

}