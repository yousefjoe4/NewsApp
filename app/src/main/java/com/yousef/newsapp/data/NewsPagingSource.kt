package com.yousef.newsapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yousef.newsapp.api.NewsService
import com.yousef.newsapp.models.Article
import retrofit2.HttpException
import java.io.IOException

const val NEWS_STARTING_PAGE_INDEX = 1

class NewsPagingSource(private val newsService: NewsService, private val query: String) :
    PagingSource<Int, Article>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: NEWS_STARTING_PAGE_INDEX
        return try {
            val response = newsService.getNews(query, position)
            val news = response.articles
            LoadResult.Page(
                data = news,
                prevKey = if (position == NEWS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (news.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

}