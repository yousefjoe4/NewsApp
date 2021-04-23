package com.yousef.newsapp.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yousef.newsapp.databinding.ItemNewsBinding
import com.yousef.newsapp.models.Article
import com.yousef.newsapp.ui.NewsDetailsActivity

/**
 * This adapter is used for the news coming from the database (i.e: Favorites)
 */
class NewsAdapter(val onArticleClicked: OnArticleClicked) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var articles = listOf<Article>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val article = articles[layoutPosition]
                onArticleClicked.onClick(article)
                openArticleDetailsActivity(article)

            }
        }

//        companion object{
//            fun from(parent: ViewGroup){
//                val inflater = LayoutInflater.from(parent.context)
//                val binding = ItemNewsBinding.inflate(inflater, parent, false)
//            }
//        }

        fun bind(article: Article) {
            binding.article = article
        }

        private fun openArticleDetailsActivity(article: Article?) {

            val intent = Intent(binding.root.context, NewsDetailsActivity::class.java)
            intent.putExtra(NewsDetailsActivity.NEWS_EXTRA, article)
            binding.root.context.startActivity(intent)
        }



    }

}