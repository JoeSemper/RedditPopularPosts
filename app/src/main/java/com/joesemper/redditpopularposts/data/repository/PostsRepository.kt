package com.joesemper.redditpopularposts.data.repository

import androidx.paging.PagingData
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.entity.PostInfo
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(page: Int): List<Children>
    fun getAllPosts(): Flow<PagingData<PostInfo>>
}