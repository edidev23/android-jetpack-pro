package com.edisiswanto.moviecatalogue.ui.detailTV

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.databinding.ActivityDetailTvBinding
import com.edisiswanto.moviecatalogue.databinding.ContentDetailTvBinding
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.utils.Helper.setImageWithGlide
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory
import javax.inject.Inject

class DetailTvActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailTvBinding
    private lateinit var contentDetailTvBinding: ContentDetailTvBinding

    private lateinit var viewModel : DetailTvViewModel
    private var menu: Menu? = null

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        contentDetailTvBinding = binding.detailContentTv

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<MovieEntity>(EXTRA_TV) as TvEntity

        factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this@DetailTvActivity,
            factory
        )[DetailTvViewModel::class.java]

        viewModel.setSelectedTv(detail.id)

        viewModel.detailTv.observe(this, {
            contentDetailTvBinding.progressBar.visibility = View.INVISIBLE
            detailTv(it)
        })
    }

    private fun detailTv(detail: TvEntity) {
        contentDetailTvBinding.apply {
            tvTitleTv.text = detail.name
            tvOverviewTv.text = detail.overview
            tvItemDateTv.text = resources.getString(
                R.string.show_date,
                detail.firstAirDate
            )
            tvItemScoreTv.text = detail.voteAverage.toString()

            setImageWithGlide(
                this@DetailTvActivity,
                MovieFragment.API_IMAGE_ENDPOINT + MovieFragment.ENDPOINT_POSTER_SIZE_W185 + detail.posterPath,
                imgPosterTv
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        viewModel.detailTv.observe(this, { detailMovie ->
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
        const val EXTRA_TV = "extra_tv"
    }
}