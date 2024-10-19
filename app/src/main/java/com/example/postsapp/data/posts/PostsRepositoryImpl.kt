package com.example.postsapp.data.posts

import com.example.postsapp.network.api.JsonPlaceholderService
import com.example.postsapp.core.model.Post
import com.example.postsapp.core.repository.PostsRepository
import javax.inject.Inject
import com.example.postsapp.core.dispatchers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException


class PostsRepositoryImpl @Inject constructor(
    private val client: JsonPlaceholderService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): PostsRepository {
    override suspend fun getPosts(): Result<List<Post>> = withContext(dispatcher){
        try {
            val result = client.getPosts().execute()
            if(result.isSuccessful){
                Result.success(result.body().orEmpty())
            } else {
                Result.failure(IOException("${result.message()}"))
            }
        } catch (e: IOException){
            Result.failure(e)
        }
    }
}