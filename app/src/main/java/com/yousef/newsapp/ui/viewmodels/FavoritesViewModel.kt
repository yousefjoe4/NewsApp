package com.yousef.newsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yousef.newsapp.database.NewsDao

class FavoritesViewModel(private val database: NewsDao, application: Application) :
        AndroidViewModel(application) {

    val news = database.getAllNews()

}