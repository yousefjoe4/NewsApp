package com.yousef.newsapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yousef.newsapp.ui.adapters.NewsAdapter
import com.yousef.newsapp.database.AppDatabase
import com.yousef.newsapp.databinding.FragmentFavoritesBinding
import com.yousef.newsapp.models.Article
import com.yousef.newsapp.ui.adapters.OnArticleClicked
import com.yousef.newsapp.ui.viewmodels.FavoritesViewModel
import com.yousef.newsapp.ui.viewmodels.FavoritesViewModelFactory


class FavoritesFragment : Fragment() {
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)


        // Init the RecyclerView
        newsAdapter = NewsAdapter(object:OnArticleClicked{
            override fun onClick(article: Article?) {
                openArticleDetailsActivity(article)
            }
        })

        binding.recyclerview.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)

        }


        // Init the ViewModel
        val dataSource = AppDatabase.getInstance(this.requireContext()).newsDao()
        val viewModelFactory = FavoritesViewModelFactory(dataSource, this.requireActivity().application)
        val favoritesViewModel = ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)


        // Listen to the changes in the database to get the news whenever they change
        favoritesViewModel.articles.observe(this) { articles ->
            displayArticles(articles)
        }


        return binding.root
    }

    private fun displayArticles(articles: List<Article>) {
        newsAdapter.articles = articles
    }

    private fun openArticleDetailsActivity(article: Article?) {
        val intent = Intent(requireActivity(), NewsDetailsActivity::class.java)
        intent.putExtra(NewsDetailsActivity.NEWS_EXTRA, article)
        requireActivity().startActivity(intent)
    }

}