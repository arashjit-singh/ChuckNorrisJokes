package com.chucknorrisjokes.model

data class JokeSearch(
    val result: List<Result>,
    val total: Int
)