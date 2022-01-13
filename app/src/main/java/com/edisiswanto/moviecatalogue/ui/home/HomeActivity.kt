package com.edisiswanto.moviecatalogue.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.databinding.ActivityHomeBinding
import com.edisiswanto.moviecatalogue.ui.favorite.FavoriteActivity
import com.edisiswanto.moviecatalogue.utils.SortUtils
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
        supportActionBar?.elevation = 0f

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[HomeViewModel::class.java]

        activityHomeBinding.fabFavorite.setOnClickListener {
            val intent = Intent(
                this,
                FavoriteActivity::class.java
            )
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_oldest -> sort = SortUtils.OLDEST
            R.id.action_random -> sort = SortUtils.RANDOM
        }
        viewModel.getMovieSort(sort).observe(this, movieObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private val movieObserver = Observer<PagedList<MovieEntity>> { movieList ->
        if (movieList != null) {
            // adapter.submitList(movieList)
            Log.e("sort", movieList.toString())
        }
    }
}