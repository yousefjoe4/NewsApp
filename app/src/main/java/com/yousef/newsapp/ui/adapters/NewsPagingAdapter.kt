package com.yousef.newsapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yousef.newsapp.databinding.ItemNewsBinding
import com.yousef.newsapp.models.News
import com.yousef.newsapp.ui.NewsDetailsActivity

class NewsPagingAdapter(val context: Context?) :
    PagingDataAdapter<News, NewsPagingAdapter.ViewHolder>(NEWS_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    inner class ViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val article = getItem(layoutPosition)
                openArticleDetailsActivity(article)

            }
        }

        fun bind(news: News) {
            // Pass the article reference to the View Object (xml item),
            // and let it handle binding the members
            binding.news = news
        }

        private fun openArticleDetailsActivity(news: News?) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(NewsDetailsActivity.NEWS_EXTRA, news)
            context?.startActivity(intent)
        }
    }

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.publishedAt == newItem.publishedAt
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }

}