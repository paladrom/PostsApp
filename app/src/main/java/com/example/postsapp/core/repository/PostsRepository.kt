package com.example.postsapp.core.repository

import com.example.postsapp.core.model.Post

interface PostsRepository {
    suspend fun getPosts(): Result<List<Post>>
}