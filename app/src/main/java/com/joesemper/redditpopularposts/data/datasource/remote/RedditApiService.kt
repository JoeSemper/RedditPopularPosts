package com.joesemper.redditpopularposts.data.datasource.remote

import com.joesemper.redditpopularposts.data.entity.RedditPosts
import retrofit2.http.GET

interface RedditApiService {

    @GET("aww/hot.json")
    suspend fun getPosts(): RedditPosts
}