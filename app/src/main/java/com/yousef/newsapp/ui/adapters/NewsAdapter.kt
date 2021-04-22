package com.yousef.newsapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yousef.newsapp.databinding.ItemNewsBinding
import com.yousef.newsapp.models.News
import com.yousef.newsapp.ui.NewsDetailsActivity

/**
 * This adapter is used for the news coming from the database (i.e: Favorites)
 */
class NewsAdapter(
    private val context: Context?) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news: List<News> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class ViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val article = news[layoutPosition]
                openArticleDetailsActivity(article)

            }
        }

        fun bind(news: News) {
            binding.news = news
        }

        private fun openArticleDetailsActivity(news: News?) {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(NewsDetailsActivity.NEWS_EXTRA, news)
            context?.startActivity(intent)
        }
    }


    /**
     * Helper Method to Update the list of data and re-populate the data in the list
     */
    fun setArticles(news: List<News>) {
        Log.e("ArticlesAdapter","Received Data")
        this.news = news
        notifyDataSetChanged()
    }
}