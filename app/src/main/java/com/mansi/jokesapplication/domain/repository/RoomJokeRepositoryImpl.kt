package com.mansi.jokesapplication.domain.repository

import com.mansi.jokesapplication.data.dao.JokeDao
import com.mansi.jokesapplication.domain.model.Joke


class RoomJokeRepositoryImpl(private val jokeDao: JokeDao) : RoomJokeRepository {
    override suspend fun getLocalJokes(): List<Joke> {
        val jokeEntities = jokeDao.getRecentJokes()
        return jokeEntities.map { Joke(it.joke) }
    }

    override suspend fun insertJoke(joke: Joke) {
        val jokeEntity = Joke(joke = joke.joke)
        jokeDao.insertJoke(jokeEntity)
    }
}