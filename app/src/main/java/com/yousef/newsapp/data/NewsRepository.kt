package com.yousef.newsapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.yousef.newsapp.api.NewsService
import com.yousef.newsapp.models.News

class NewsRepository(private val newsService: NewsService) {

    fun getSearchResult(query: String):LiveData<PagingData<News>> {
        val config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        );

        return Pager(
            config = config,
            pagingSourceFactory = { NewsPagingSource(newsService, query) }
        ).liveData
    }
}