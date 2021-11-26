package com.joesemper.redditpopularposts.data.datasource.remote

import androidx.paging.*
import androidx.room.withTransaction
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.joesemper.redditpopularposts.data.datasource.local.PostsDatabase
import com.joesemper.redditpopularposts.data.datasource.mediator.PostsRemoteMediator
import com.joesemper.redditpopularposts.data.entity.Children
import com.joesemper.redditpopularposts.data.entity.PostInfo
import com.joesemper.redditpopularposts.data.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class PostRepositoryRetrofitImpl(private val database: PostsDatabase) : PostsRepository {

    companion object {
        private const val BASE_WEATHER_URL = "https://www.reddit.com/r/"
    }

    override suspend fun getPosts(page: Int): List<Children> {
        return getService().getPosts().data.children
    }

    private fun getService(): RedditApiService {
        return createRetrofit().create(RedditApiService::class.java)
    }

    @ExperimentalPagingApi
    override fun getAllPosts(): Flow<PagingData<PostInfo>> {
        val postsDao = database.postDao()
        return Pager(
            config = PagingConfig(pageSize = 25),
            remoteMediator = PostsRemoteMediator("", database, getService())
        ) {
            postsDao.pagingSource()
        }.flow

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

class PostsPagingDataSource(private val service: RedditApiService) :
    PagingSource<Int, Children>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Children> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getPosts()
            val pagedResponse = response.data
            val data = pagedResponse.children

            val nextKey =
                if (data.isEmpty()) {
                    null
                } else {
                    pageNumber + (params.loadSize / 25)
                }

            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Children>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
