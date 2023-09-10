package com.mansi.jokesapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mansi.jokesapplication.domain.model.Joke


@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJoke(joke: Joke)

    @Query("SELECT * FROM joke ORDER BY id DESC LIMIT 10")
    fun getRecentJokes(): List<Joke>
}