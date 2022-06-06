package com.chucknorrisjokes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "joke")
data class JokeDataClass(
    val created_at: String,
    val icon_url: String,
    @PrimaryKey
    val id: String,
    val updated_at: String,
    val url: String,
    @ColumnInfo(name = "value")
    val value: String
) : Serializable