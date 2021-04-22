package com.yousef.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yousef.newsapp.models.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT * FROM news where publishedAt=:publishedAt")
    suspend fun get(publishedAt: String): News?

    @Insert
    suspend fun insertNews(news: News)

    @Delete()
    suspend fun deleteNews(news: News)
}