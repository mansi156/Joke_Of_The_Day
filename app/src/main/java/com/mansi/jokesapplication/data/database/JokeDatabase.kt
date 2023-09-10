package com.mansi.jokesapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mansi.jokesapplication.data.dao.JokeDao
import com.mansi.jokesapplication.domain.model.Joke

@Database(entities = [Joke::class],version = 1,exportSchema = false)
abstract class JokeDatabase : RoomDatabase(){
    abstract fun jokeDao():JokeDao
}