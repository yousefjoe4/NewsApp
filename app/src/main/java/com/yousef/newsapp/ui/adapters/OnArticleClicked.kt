package com.yousef.newsapp.ui.adapters

import com.yousef.newsapp.models.Article

interface OnArticleClicked {
    fun onClick(article: Article?)
}