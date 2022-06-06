package com.chucknorrisjokes.room

import androidx.room.*
import com.chucknorrisjokes.model.JokeDataClass

@Dao
interface JokeDao {
    @Query("SELECT * FROM joke")
    fun getAllJokes(): List<JokeDataClass>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(jokeEntity: JokeDataClass?)

    @Delete
    fun deleteJoke(jokeEntity: JokeDataClass?)

    @Query("DELETE FROM joke")
    fun deleteAll()
}