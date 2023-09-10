package com.mansi.jokesapplication.data.remote

import com.mansi.jokesapplication.domain.model.Joke
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {
    @GET("api?format=json")
    suspend fun getJokes(): Joke
}
