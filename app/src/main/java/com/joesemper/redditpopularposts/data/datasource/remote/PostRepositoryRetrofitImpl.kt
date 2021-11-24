package com.joesemper.redditpopularposts.data.datasource.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.repository.PostsRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRepositoryRetrofitImpl: PostsRepository {

    companion object {
        private const val BASE_WEATHER_URL = "https://www.reddit.com/r/"
    }

    override suspend fun getPosts(page: Int): List<Children> {
        return getService().getPosts().data.children
    }

    private fun getService(): RedditApiService {
        return createRetrofit().create(RedditApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}