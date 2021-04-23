package com.yousef.newsapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yousef.newsapp.models.Article

@Dao
interface NewsDao {
    @Query("SELECT * FROM article")
    fun getArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM article where publishedAt=:publishedAt")
    suspend fun get(publishedAt: String): Article?

    @Insert
    suspend fun insertNews(article: Article)

    @Delete()
    suspend fun deleteNews(article: Article)
}