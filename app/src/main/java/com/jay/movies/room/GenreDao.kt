package com.jay.movies.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.movies.model.Genre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreList(genres: List<Genre>)

    @Query("SELECT * FROM Genre")
    fun getGenreList(): List<Genre>

}