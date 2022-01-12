package com.edisiswanto.moviecatalogue.ui.detailMovie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.edisiswanto.moviecatalogue.databinding.ContentDetailMovieBinding
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.utils.Helper.setImageWithGlide
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    private lateinit var viewModel: DetailMovieViewModel
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = binding.detailContentMovie

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE) as MovieEntity

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this@DetailMovieActivity,
            factory
        )[DetailMovieViewModel::class.java]

        viewModel.setSelectedMovie(detail.id)

        viewModel.detailMovie.observe(this, {
            contentDetailMovieBinding.progressBar.visibility = View.INVISIBLE
            detailMovie(it)
        })

    }

    private fun detailMovie(detail: MovieEntity) {
        contentDetailMovieBinding.apply {
            tvTitle.text = detail.title
            tvOverview.text = detail.overview
            tvItemDate.text = resources.getString(
                R.string.release_date,
                detail.relesaseDate
            )
            tvItemScore.text = detail.voteAverage.toString()

            setImageWithGlide(
                this@DetailMovieActivity,
                MovieFragment.API_IMAGE_ENDPOINT + MovieFragment.ENDPOINT_POSTER_SIZE_W185 + detail.posterPath,
                imgPoster
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.detailMovie.observe(this, { detailMovie ->
            if (detailMovie != null) {
                setBookmarkState(detailMovie.bookmarked)
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            viewModel.setBookmark()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_bordered)
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}