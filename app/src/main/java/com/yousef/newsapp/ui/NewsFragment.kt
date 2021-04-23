package com.yousef.newsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yousef.newsapp.ui.adapters.NewsPagingAdapter
import com.yousef.newsapp.databinding.FragmentNewsBinding
import com.yousef.newsapp.models.Article
import com.yousef.newsapp.ui.adapters.OnArticleClicked
import com.yousef.newsapp.ui.viewmodels.NewsViewModel


class NewsFragment : Fragment() {
    lateinit var newsAdapter: NewsPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentNewsBinding.inflate(layoutInflater, container, false)

        // Init RecyclerView
        newsAdapter = NewsPagingAdapter(object:OnArticleClicked{
            override fun onClick(article: Article?) {
                openArticleDetailsActivity(article)
            }
        })
        binding.recyclerview.apply{
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }


        // Init ViewModel
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        // Observe data changes to the list of articles and display the new ones
        viewModel.news.observe(viewLifecycleOwner){
            newsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        // Set a listener when the user hits the search button to get news
        binding.btnSearch.setOnClickListener {
            val keyword = binding.etSearchBar.text.trim().toString()
            if(keyword.isNotEmpty()){
                viewModel.searchNewsAbout(keyword)
            }
        }



        return binding.root
    }

    private fun openArticleDetailsActivity(article: Article?) {
        val intent = Intent(requireActivity(), NewsDetailsActivity::class.java)
        intent.putExtra(NewsDetailsActivity.NEWS_EXTRA, article)
        requireActivity().startActivity(intent)
    }

}

