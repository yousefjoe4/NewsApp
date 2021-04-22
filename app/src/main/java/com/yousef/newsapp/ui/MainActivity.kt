package com.yousef.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yousef.newsapp.R
import com.yousef.newsapp.databinding.ActivityMainBinding
import com.yousef.newsapp.utilities.Logs


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logs.logEvent(this,"App was installed")

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)

        // Set the listener of the bottomNavigationView
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(getBottomNavigationListener())

        // check if this is the first time to open the activity to avoid creating the fragment again
        if (savedInstanceState == null) {
            // Show the News Fragment once the app starts,
            showNewsFragment()
        }

    }


    /**
     *
     * A helper method to initialize the listener of the bottom navigationView
     *
     * **/
    private fun getBottomNavigationListener(): BottomNavigationView.OnNavigationItemSelectedListener {
        return BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.news -> {
                    showNewsFragment()
                    true
                }
                R.id.favorites -> {
                    showFavoritesFragment()
                    true
                }
                else -> false
            }

        }
    }


    private fun showNewsFragment() {
        showFragment(NewsFragment())

    }


    private fun showFavoritesFragment() {
        showFragment(FavoritesFragment())
    }

    /**
     *
     * A helper method to show the passed @fragment instead of the placeholder/container
     *
     * **/

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
    }
}