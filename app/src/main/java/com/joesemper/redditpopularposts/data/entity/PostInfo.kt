package com.joesemper.redditpopularposts.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostInfo(
    @PrimaryKey val id: String = "",
    val author: String = "",
    val num_comments: Int = 0,
    val title: String = "",
    val total_awards_received: Int = 0,
)