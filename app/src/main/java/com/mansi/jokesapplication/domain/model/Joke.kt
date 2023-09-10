package com.mansi.jokesapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "joke")
@JsonClass(generateAdapter = true)
data class Joke(
    val joke:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}