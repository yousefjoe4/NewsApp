package com.yousef.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.yousef.newsapp.R
import com.yousef.newsapp.databinding.ActivityNewsSourceBinding

/**
 * The activity used to show News in a WebView
 */
class NewsSourceActivity : AppCompatActivity() {
    companion object {
        const val NEWS_URL_EXTRA = "newsUrlExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityNewsSourceBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_news_source)

        val url = intent.getStringExtra(NEWS_URL_EXTRA)
        if (url != null)
            binding.webview.apply {
                loadUrl(url)
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // Use the onBackPressed functionality to avoid re-creating the previous activity
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}