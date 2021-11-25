package com.joesemper.redditpopularposts.data.datasource.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joesemper.redditpopularposts.data.entity.PostInfo

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PostInfo>)

    @Query("SELECT * FROM posts WHERE id LIKE '%'")
    fun pagingSource(): PagingSource<Int, PostInfo>

    @Query("DELETE FROM posts")
    suspend fun clearAll()
}

