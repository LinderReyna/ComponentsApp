package com.example.jossemarapp.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jossemarapp.model.Post

@Dao
interface PostInterface {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Query("SELECT * FROM posts WHERE id == :id")
    fun getPost(id: Int): LiveData<Post>

    @Query("SELECT * FROM posts")
    fun getPosts(): LiveData<List<Post>>
}