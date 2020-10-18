package com.jay.movies.room

import androidx.room.*
import com.jay.movies.model.Genre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreList(genres: List<Genre>)

    @Query("SELECT * FROM Genre")
    fun getGenreList(): List<Genre>

}