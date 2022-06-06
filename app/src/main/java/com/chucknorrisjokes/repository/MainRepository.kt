package com.chucknorrisjokes.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chucknorrisjokes.model.JokeCategories
import com.chucknorrisjokes.model.JokeDataClass
import com.chucknorrisjokes.model.JokeSearch
import com.chucknorrisjokes.network.ApiService
import com.chucknorrisjokes.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private var apiService: ApiService =
        RetrofitClient.getRetrofitInstance().create(ApiService::class.java)

    fun getRandomJoke(): LiveData<JokeDataClass> {
        val data = MutableLiveData<JokeDataClass>()
        val call = apiService.getRandomJoke()

        call.enqueue(object : Callback<JokeDataClass> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<JokeDataClass>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<JokeDataClass>,
                response: Response<JokeDataClass>
            ) {
                data.value =
                    if (response.body() != null) response.body() else null
            }
        })

        return data
    }

    fun getJokeCategories(): LiveData<JokeCategories> {
        val data = MutableLiveData<JokeCategories>()
        val call = apiService.getJokeCategories()

        call.enqueue(object : Callback<JokeCategories> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<JokeCategories>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<JokeCategories>,
                response: Response<JokeCategories>
            ) {
                data.value =
                    if (response.body() != null) response.body() else null
            }
        })

        return data
    }

    fun getJokeFromACategory(category: String): LiveData<JokeDataClass> {
        val data = MutableLiveData<JokeDataClass>()
        val call = apiService.getJokeFromACategory(category)

        call.enqueue(object : Callback<JokeDataClass> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<JokeDataClass>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<JokeDataClass>,
                response: Response<JokeDataClass>
            ) {
                data.value =
                    if (response.body() != null) response.body() else null
            }
        })

        return data
    }

    fun searchForJoke(searchText: String): LiveData<JokeSearch> {
        val data = MutableLiveData<JokeSearch>()
        val call = apiService.searchForJoke(searchText)

        call.enqueue(object : Callback<JokeSearch> {
            @SuppressLint("NullSafeMutableLiveData")
            override fun onFailure(call: Call<JokeSearch>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<JokeSearch>,
                response: Response<JokeSearch>
            ) {
                data.value =
                    if (response.body() != null) response.body() else null
            }
        })

        return data
    }
}

