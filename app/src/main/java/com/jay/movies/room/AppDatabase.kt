package com.jay.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jay.movies.model.Genre
import com.jay.movies.model.Movie
import com.jay.movies.room.converter.IntegerListConverter
import com.jay.movies.room.converter.VideoListConverter

@Database(
    entities = [Movie::class, Genre::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    value = [
        IntegerListConverter::class,
        VideoListConverter::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao

}