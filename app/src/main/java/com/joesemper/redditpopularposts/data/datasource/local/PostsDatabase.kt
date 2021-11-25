package com.joesemper.redditpopularposts.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joesemper.redditpopularposts.data.entity.PostInfo


@Database(entities = [PostInfo::class], version = 1)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
