package com.yousef.newsapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yousef.newsapp.database.NewsDao
import com.yousef.newsapp.models.Article
import kotlinx.coroutines.launch

class NewsDetailsViewModel(val database: NewsDao) : ViewModel() {

    private val _markedAsFavorite = MutableLiveData<Boolean>()
    val markedAsFavorite: LiveData<Boolean>
        get() = _markedAsFavorite




    fun addNewsToFavorites(article: Article) {
        viewModelScope.launch {
            insertNewsToDatabase(article)
            _markedAsFavorite.value = true
        }
    }


    fun removeNewsFromFavorites(article: Article) {
        viewModelScope.launch {
            deleteNewsFromDatabase(article)
            _markedAsFavorite.value = false
        }
    }

    private suspend fun deleteNewsFromDatabase(article: Article) {
        database.deleteNews(article)
    }

    private suspend fun insertNewsToDatabase(article: Article) {
        database.insertNews(article)
    }


    // Check if the News ia already favorite and in the DB
    // If it is favorite reflect that on the UI
    fun checkIfNewsIsFavorite(publishedAt: String) {
        viewModelScope.launch {
            val foundNews = getNewsFromDatabase(publishedAt)
            _markedAsFavorite.value = foundNews != null
        }
    }

    private suspend fun getNewsFromDatabase(publishedAt: String): Article? {
        return database.get(publishedAt)
    }


}