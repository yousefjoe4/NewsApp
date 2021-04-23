package com.yousef.newsapp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yousef.newsapp.databinding.ItemNewsBinding
import com.yousef.newsapp.models.Article

class NewsPagingAdapter(val onArticleClicked: OnArticleClicked) :
    PagingDataAdapter<Article, NewsPagingAdapter.ViewHolder>(NEWS_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
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
                onArticleClicked.onClick(article)
            }
        }

        fun bind(article: Article) {
            // Pass the article reference to the View Object (xml item),
            // and let it handle binding the members
            binding.article = article
        }

    }

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.publishedAt == newItem.publishedAt
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

}