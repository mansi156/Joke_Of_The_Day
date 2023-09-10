package com.mansi.jokesapplication.domain.repository

import com.mansi.jokesapplication.domain.model.Joke

interface JokesRepository {
    suspend fun getJokes(): Joke
}
