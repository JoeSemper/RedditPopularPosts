package com.joesemper.redditpopularposts.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.entity.PostInfo
import com.joesemper.redditpopularposts.data.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostsRepository) : ViewModel() {

    fun getHotPosts(): Flow<PagingData<PostInfo>> {
        return repository.getAllPosts().cachedIn(viewModelScope)
    }

}