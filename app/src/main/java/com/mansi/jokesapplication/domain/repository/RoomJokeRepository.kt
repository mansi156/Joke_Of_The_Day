package com.mansi.jokesapplication.domain.repository

import com.mansi.jokesapplication.domain.model.Joke

interface RoomJokeRepository {
    suspend fun getLocalJokes(): List<Joke>
    suspend fun insertJoke(joke: Joke)
}