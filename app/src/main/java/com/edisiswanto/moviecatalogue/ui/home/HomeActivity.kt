package com.edisiswanto.moviecatalogue.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.databinding.ActivityHomeBinding
import com.edisiswanto.moviecatalogue.ui.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
        supportActionBar?.elevation = 0f

        activityHomeBinding.fabFavorite.setOnClickListener {
            val intent = Intent(
                this,
                FavoriteActivity::class.java
            )
            startActivity(intent)
        }
    }
}