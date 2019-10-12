package com.example.jossemarapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.jossemarapp.model.Post
import com.example.jossemarapp.repository.local.PostDatabase
import com.example.jossemarapp.repository.network.PostNetwork
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PostRepository(private var index: Int, context: Context) {

    private val netSource = PostNetwork()
    private val locSource = PostDatabase.getAppDataBase(context)

    fun getPosts(): LiveData<List<Post>>? {
        return when (index) {
            1 -> netSource.getPosts()
            else -> locSource?.postDao()?.getPosts()
        }
    }

    fun getPost(id: Int): LiveData<Post>? {
        return when (index) {
            1 -> netSource.getPost(id)
            else -> locSource?.postDao()?.getPost(id)
        }
    }

    fun setPost(post: Post?): LiveData<Post>? {
        Observable.just(locSource)
            .subscribeOn(Schedulers.io())
            .subscribe { locSource ->
                post?.let { locSource?.postDao()?.insertPost(it) }
            }
        return post?.id?.let { getPost(it) }
    }

}