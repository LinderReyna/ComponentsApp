package com.example.jossemarapp.repository.network

import com.example.jossemarapp.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostInterface {

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>
}