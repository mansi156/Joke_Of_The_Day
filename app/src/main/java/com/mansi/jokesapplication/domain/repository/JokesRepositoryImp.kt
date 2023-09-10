package com.mansi.jokesapplication.domain.repository

import com.mansi.jokesapplication.data.remote.ApiService
import com.mansi.jokesapplication.domain.model.Joke

class JokesRepositoryImp(private val apiService: ApiService) : JokesRepository {
    override suspend fun getJokes(): Joke = apiService.getJokes()
}
