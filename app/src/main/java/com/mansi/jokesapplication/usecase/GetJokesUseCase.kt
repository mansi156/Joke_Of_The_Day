package com.mansi.jokesapplication.usecase

import com.mansi.jokesapplication.domain.model.Joke
import com.mansi.jokesapplication.domain.repository.JokesRepository
import com.mansi.jokesapplication.usecase.base.UseCase

class GetJokesUseCase constructor(
    private val jokesRepository: JokesRepository
) : UseCase<Joke, Any?>() {

    override suspend fun run(params: Any?): Joke {
        return jokesRepository.getJokes()
    }
}
