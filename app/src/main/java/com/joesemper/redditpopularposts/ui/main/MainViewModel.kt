package com.joesemper.redditpopularposts.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.repository.PostsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostsRepository) : ViewModel() {

    val currentPosts = MutableStateFlow<List<Children>>(listOf())

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            currentPosts.value = repository.getPosts(0)
        }
    }

}