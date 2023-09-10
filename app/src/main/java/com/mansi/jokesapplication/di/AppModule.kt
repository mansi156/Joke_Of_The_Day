package com.mansi.jokesapplication.di

import com.mansi.jokesapplication.presentation.jokes.JokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { JokesViewModel(get(), get()) }

    single { createGetJokesUseCase(get()) }

    single { createJokeRepository(get()) }

    single { createRoomJokeRepository(get()) }
}