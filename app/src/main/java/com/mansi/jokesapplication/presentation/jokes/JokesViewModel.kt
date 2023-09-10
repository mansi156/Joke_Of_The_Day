package com.mansi.jokesapplication.presentation.jokes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mansi.jokesapplication.domain.model.ApiError
import com.mansi.jokesapplication.domain.model.Joke
import com.mansi.jokesapplication.domain.repository.RoomJokeRepository
import com.mansi.jokesapplication.usecase.GetJokesUseCase
import com.mansi.jokesapplication.usecase.base.UseCaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JokesViewModel(
    private val getJokesUseCase: GetJokesUseCase,
    private val jokeRepository: RoomJokeRepository
) : ViewModel() {

    val showProgressBar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()
    private val _jokeList = MutableLiveData<List<Joke>>()
    val jokeList: LiveData<List<Joke>> = _jokeList

    init {
        loadJokesFromLocal()
        viewModelScope.launch {
            while (true) {
                delay(6000) // Fetch new jokes every minute
                getJokes()
            }
        }
    }

    private fun loadJokesFromLocal() {
        viewModelScope.launch {
            val localJokes = withContext(Dispatchers.IO) {
                jokeRepository.getLocalJokes()
            }
            _jokeList.value = localJokes
        }
    }

    private fun insertJokeLocally(joke: Joke) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                jokeRepository.insertJoke(joke)
            }
        }
    }

    private fun getJokes() {
        showProgressBar.value = true
        getJokesUseCase.invoke(
            viewModelScope,
            null,
            object : UseCaseResponse<Joke> {
                override fun onSuccess(result: Joke) {
                    Log.i(TAG, "result: $result")
                    val currentList = _jokeList.value?.toMutableList() ?: mutableListOf()
                    currentList.add(0, result)

                    // Remove the oldest joke if the list size exceeds 10
                    if (currentList.size > 10) {
                        currentList.removeAt(currentList.size - 1)
                    }

                    _jokeList.value = currentList
                    insertJokeLocally(result) // Insert the new joke into the local Room database
                    showProgressBar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressBar.value = false
                }
            }
        )
    }

    companion object {
        private val TAG = JokesViewModel::class.java.name
    }
}