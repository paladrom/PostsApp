@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.postsapp.feature.posts


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.postsapp.core.designsystem.theme.ComposeTheme
import com.example.postsapp.core.model.Post

@Composable
fun PostsScreen(viewModel: PostsViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PostsScreenContent(state, viewModel::getPosts)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreenContent(
    postsUIState: PostsUiState,
    refresh: () -> Unit = {}
) {
    val pullRefreshState = rememberPullToRefreshState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Posts") })
        }
    ) { padding ->
        PullToRefreshBox(
            state = pullRefreshState,
            isRefreshing = postsUIState is PostsUiState.Loading,
            onRefresh = refresh,
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {
            when (postsUIState) {
                is PostsUiState.Success -> PostsList(posts = postsUIState.posts)
                is PostsUiState.Loading -> Text(text = "Loading")
                is PostsUiState.Error -> Text(text = "Error")
            }
        }
    }
}

@Composable
private fun PostsList(posts: List<Post>) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        items(posts){ post ->
            Text(text = post.id.toString())
            Text(text = post.title.toString())
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
        }
    }
}

@Composable
@Preview
private fun PostsScreenPreview() {
    val list = listOf(
        Post(
            userId = 1,
            id = 1,
            title = "title",
            body = "body"
        )
    )
    ComposeTheme {
        PostsScreenContent(PostsUiState.Success(list))
    }
}

@Composable
@Preview
private fun PostsScreenLoadingPreview() {
    ComposeTheme {
        PostsScreenContent(PostsUiState.Loading)
    }
}

@Composable
@Preview
private fun PostsScreenErrorPreview() {
    ComposeTheme {
        PostsScreenContent(PostsUiState.Error("Error"))
    }
}

