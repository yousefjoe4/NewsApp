package com.yousef.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yousef.newsapp.database.NewsDao
import java.lang.IllegalArgumentException

class NewsDetailsViewModelFactory(private val database:NewsDao):ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsDetailsViewModel::class.java)){
            return NewsDetailsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}