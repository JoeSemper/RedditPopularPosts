package com.joesemper.redditpopularposts.data.datasource.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.joesemper.redditpopularposts.data.datasource.local.PostDao
import com.joesemper.redditpopularposts.data.datasource.local.PostsDatabase
import com.joesemper.redditpopularposts.data.datasource.remote.RedditApiService
import com.joesemper.redditpopularposts.data.entity.PostInfo
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PostsRemoteMediator(
    private val query: String,
    private val database: PostsDatabase,
    private val networkService: RedditApiService
) : RemoteMediator<Int, PostInfo>() {
    private val postDao: PostDao = database.postDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostInfo>
    ): MediatorResult {
        return try {
            val response = networkService.getPosts()

            database.withTransaction {
                postDao.insertAll(response.data.children.map { it.data })
            }

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}