package com.edisiswanto.moviecatalogue.ui.detailMovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.edisiswanto.moviecatalogue.databinding.ContentDetailMovieBinding
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = binding.detailContentMovie

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detail = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE) as MovieEntity

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this@DetailMovieActivity,
            factory
        )[DetailMovieViewModel::class.java]

        viewModel.getMovieDetail(detail.id).observe(this, {

            contentDetailMovieBinding.progressBar.visibility = View.INVISIBLE
            detailMovie(it)
        })

    }

    private fun detailMovie(detail: MovieEntity) {
        contentDetailMovieBinding.tvTitle.text = detail.title
        contentDetailMovieBinding.tvOverview.text = detail.overview
        contentDetailMovieBinding.tvItemDate.text = resources.getString(
            R.string.release_date,
            detail.relesaseDate
        )
        contentDetailMovieBinding.tvItemScore.text = detail.voteAverage.toString()

        Glide.with(this).load(MovieFragment.API_IMAGE_ENDPOINT + MovieFragment.ENDPOINT_POSTER_SIZE_W185 + detail.posterPath).apply(
            RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                .transform(RoundedCorners(50))
        ).into(contentDetailMovieBinding.imgPoster)
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
}