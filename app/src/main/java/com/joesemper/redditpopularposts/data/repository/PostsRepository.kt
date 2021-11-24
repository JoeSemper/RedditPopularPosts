package com.joesemper.redditpopularposts.data.repository

import com.joesemper.redditpopularposts.data.entity.Children

interface PostsRepository {
    suspend fun getPosts(page: Int): List<Children>
}