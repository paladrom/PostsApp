package com.example.postsapp.feature.posts

import com.example.postsapp.core.model.Post

sealed class PostsUiState {
    data object Loading : PostsUiState()
    data class Success(val posts: List<Post>) : PostsUiState()
    data class Error(val message: String?) : PostsUiState()
}