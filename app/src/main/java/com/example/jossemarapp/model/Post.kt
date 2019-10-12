package com.example.jossemarapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class Post(
    @field:SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    @field:SerializedName("title")
    var title: String,
    @field:SerializedName("body")
    var body: String,
    @field:SerializedName("userId")
    var userId: Int
) {

}