package com.example.postsapp.data.posts.di

import com.example.postsapp.core.repository.PostsRepository
import com.example.postsapp.data.posts.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindRepository(impl: PostsRepositoryImpl): PostsRepository

}