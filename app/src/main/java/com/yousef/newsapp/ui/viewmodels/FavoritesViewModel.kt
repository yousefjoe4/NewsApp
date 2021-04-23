package com.yousef.newsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yousef.newsapp.database.NewsDao

class FavoritesViewModel(database: NewsDao, application: Application) :
        AndroidViewModel(application) {

    val articles = database.getArticles()

}