package com.example.jossemarapp.repository.network

import androidx.lifecycle.MutableLiveData
import com.example.jossemarapp.model.Post
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class PostNetwork {

    fun getPosts(): MutableLiveData<List<Post>>{
        val data: MutableLiveData<List<Post>> = MutableLiveData()
        postInterface().getPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                }
            }

        })
        return data
    }

    fun getPost(id: Int): MutableLiveData<Post>{
        val data: MutableLiveData<Post> = MutableLiveData()
        postInterface().getPost(id).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    data.postValue(response.body())
                }
            }

        })
        return data
    }

    companion object {
        private fun postInterface(): PostInterface {
            val spec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build()
            val client = OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create<PostInterface>(PostInterface::class.java)
        }
    }
}