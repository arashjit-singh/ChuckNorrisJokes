package com.chucknorrisjokes.network

import com.chucknorrisjokes.model.JokeCategories
import com.chucknorrisjokes.model.JokeDataClass
import com.chucknorrisjokes.model.JokeSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    fun getRandomJoke()
            : Call<JokeDataClass>

    @GET("categories")
    fun getJokeCategories()
            : Call<JokeCategories>

    @GET("random")
    fun getJokeFromACategory(
        @Query("category") category: String
    ): Call<JokeDataClass>

    @GET("search")
    fun searchForJoke(
        @Query("query") query: String
    ): Call<JokeSearch>

}
