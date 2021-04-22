package com.yousef.newsapp.ui.viewmodels

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.yousef.newsapp.api.NewsService
import com.yousef.newsapp.data.NewsRepository



class NewsViewModel: ViewModel() {

    companion object{
        const val DEFAULT_QUERY = "Besiktas"
    }

    private var repository: NewsRepository = NewsRepository(NewsService.create())

    // The query used to fetch the News
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    // The list of fetchedNews
    val news = currentQuery.switchMap { query->
        repository.getSearchResult(query).cachedIn(viewModelScope)
    }

    fun searchNewsAbout(query:String){
        currentQuery.value = query
    }

}