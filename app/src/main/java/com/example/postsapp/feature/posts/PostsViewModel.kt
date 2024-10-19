package com.example.postsapp.feature.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postsapp.core.domain.GetFilteredPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getFilteredPostsUseCase: GetFilteredPostsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<PostsUiState>(PostsUiState.Loading)
    val state = _state.onStart {
        getPosts()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PostsUiState.Loading)

    fun getPosts() {
        _state.update { PostsUiState.Loading }
        viewModelScope.launch {
            val result = getFilteredPostsUseCase.invoke(MAX_ID)
            result.fold(
                onSuccess = { posts ->
                    _state.update {
                        PostsUiState.Success(posts = posts)
                    }
                },
                onFailure = { error ->
                    _state.update {
                        PostsUiState.Error(message = error.message)
                    }
                }
            )
        }
    }
}
private const val MAX_ID = 50