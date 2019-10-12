package com.example.jossemarapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.jossemarapp.MainActivity
import com.example.jossemarapp.model.Post
import com.example.jossemarapp.repository.PostRepository

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    private val result: LiveData<PostRepository> = Transformations.map(_index) {
        PostRepository(it, MainActivity.context)
    }

    fun getPosts(): LiveData<List<Post>> {
        return Transformations.switchMap(result){
            it.getPosts()
        }
    }

    fun getPost(id: Int): LiveData<Post> {
        return Transformations.switchMap(result){
            it.getPost(id)
        }
    }

    fun setIndex(index: Int) {
        _index.value = index
    }

    fun setPost(post: Post?): LiveData<Post>? {
        return Transformations.switchMap(result){
            it.setPost(post)
        }
    }
}