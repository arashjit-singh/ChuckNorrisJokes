package com.chucknorrisjokes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chucknorrisjokes.model.JokeCategories
import com.chucknorrisjokes.model.JokeDataClass
import com.chucknorrisjokes.model.JokeSearch
import com.chucknorrisjokes.repository.MainRepository

class MainActivityViewModel : ViewModel() {
    private var mainRepo: MainRepository = MainRepository()

    fun getJoke(): LiveData<JokeDataClass> {
        return mainRepo.getRandomJoke()
    }

    fun getListOfCategories(): LiveData<JokeCategories> {
        return mainRepo.getJokeCategories()
    }

    fun getJokeFromCategory(category: String): LiveData<JokeDataClass> {
        return mainRepo.getJokeFromACategory(category)
    }

    fun searchForJoke(searchText: String): LiveData<JokeSearch> {
        return mainRepo.searchForJoke(searchText)
    }
}