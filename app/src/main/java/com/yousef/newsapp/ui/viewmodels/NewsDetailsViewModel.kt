package com.yousef.newsapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousef.newsapp.database.AppDatabase
import com.yousef.newsapp.database.NewsDao
import com.yousef.newsapp.models.News
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDetailsViewModel(val database: NewsDao) : ViewModel() {

    private val _markedAsFavorite = MutableLiveData<Boolean>()
    val markedAsFavorite: LiveData<Boolean>
        get() = _markedAsFavorite




    fun addNewsToFavorites(news: News) {
        viewModelScope.launch {
            insertNewsToDatabase(news)
            _markedAsFavorite.value = true
        }
    }


    fun removeNewsFromFavorites(news: News) {
        viewModelScope.launch {
            deleteNewsFromDatabase(news)
            _markedAsFavorite.value = false
        }
    }

    private suspend fun deleteNewsFromDatabase(news: News) {
        database.deleteNews(news)
    }

    private suspend fun insertNewsToDatabase(news: News) {
        database.insertNews(news)
    }


    // Check if the News ia already favorite and in the DB
    // If it is favorite reflect that on the UI
    fun checkIfNewsIsFavorite(publishedAt: String) {
        viewModelScope.launch {
            val foundNews = getNewsFromDatabase(publishedAt)
            _markedAsFavorite.value = foundNews != null
        }
    }

    private suspend fun getNewsFromDatabase(publishedAt: String): News? {
        return database.get(publishedAt)
    }


}