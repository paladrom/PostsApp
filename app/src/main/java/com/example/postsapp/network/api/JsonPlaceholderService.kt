package com.example.postsapp.network.api

import com.example.postsapp.core.model.Post
import retrofit2.Call
import retrofit2.http.GET


interface JsonPlaceholderService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}