package com.example.postsapp.core.domain

import com.example.postsapp.core.repository.PostsRepository
import javax.inject.Inject

class GetFilteredPostsUseCase @Inject constructor(private val postsRepository: PostsRepository) {
    suspend operator fun invoke(maxId: Int = 50) = postsRepository.getPosts().map { list ->
        list.filter { (it.id ?: 0) < maxId }
    }
}