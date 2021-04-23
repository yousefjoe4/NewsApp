package com.yousef.newsapp.api


import com.yousef.newsapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val NEWS_API_KEY = BuildConfig.API_KEY
const val NEWS_API_BASE_URL = "https://newsapi.org/v2/"

interface NewsService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") key: String = NEWS_API_KEY
    ): NewsResponse

    companion object {
        fun create(): NewsService {
            return Retrofit.Builder()
                .baseUrl(NEWS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(NewsService::class.java)
        }
    }
}