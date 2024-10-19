package com.example.postsapp.network.di


import com.example.postsapp.network.api.JsonPlaceholderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideJsonPlaceholderService(client: Retrofit): JsonPlaceholderService {
        return client.create(JsonPlaceholderService::class.java)
    }

    @Provides
    @Singleton
    fun provideClient(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}
private const val BASE_URL = "https://jsonplaceholder.typicode.com"
