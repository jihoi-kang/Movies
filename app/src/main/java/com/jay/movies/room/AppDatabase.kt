package com.jay.movies.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jay.movies.model.Movie
import com.jay.movies.room.converter.IntegerListConverter

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [IntegerListConverter::class]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}