package com.edisiswanto.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.page_favorite)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val sectionsPagerAdapter = SectionPagerFavorite(this, supportFragmentManager)
        activityFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
        activityFavoriteBinding.tabs.setupWithViewPager(activityFavoriteBinding.viewPager)
        supportActionBar?.elevation = 0f
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}