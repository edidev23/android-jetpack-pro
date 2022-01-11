package com.edisiswanto.moviecatalogue.ui.detailTV

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.data.TvEntity
import com.edisiswanto.moviecatalogue.databinding.ActivityDetailTvBinding
import com.edisiswanto.moviecatalogue.databinding.ContentDetailTvBinding
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.utils.Helper
import com.edisiswanto.moviecatalogue.utils.Helper.setImageWithGlide
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class DetailTvActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailTvBinding
    private lateinit var contentDetailTvBinding: ContentDetailTvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        contentDetailTvBinding = binding.detailContentTv

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<MovieEntity>(EXTRA_TV) as TvEntity

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this@DetailTvActivity,
            factory
        )[DetailTvViewModel::class.java]

        viewModel.getTvShowDetail(detail.id).observe(this, {

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

    companion object {
        const val EXTRA_TV = "extra_tv"
    }
}