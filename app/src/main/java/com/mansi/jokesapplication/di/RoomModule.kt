package com.mansi.jokesapplication.di

import android.app.Application
import androidx.room.Room
import com.mansi.jokesapplication.data.dao.JokeDao
import com.mansi.jokesapplication.data.database.JokeDatabase
import com.mansi.jokesapplication.domain.repository.RoomJokeRepository
import com.mansi.jokesapplication.domain.repository.RoomJokeRepositoryImpl
import org.koin.dsl.module


fun providesDatabase(application: Application):JokeDatabase =
    Room.databaseBuilder(application,JokeDatabase::class.java,"jokedatabase")
        .build()

fun providesDao(db:JokeDatabase):JokeDao = db.jokeDao()


fun createRoomJokeRepository(jokeDao: JokeDao): RoomJokeRepository {
    return RoomJokeRepositoryImpl(jokeDao)
}

val RoomModule = module {

    single { providesDatabase(get()) }
    single { providesDao(get()) }
    single { createRoomJokeRepository(get()) }
}