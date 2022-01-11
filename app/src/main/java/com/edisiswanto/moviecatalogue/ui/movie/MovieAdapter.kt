package com.edisiswanto.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.MovieEntity
import com.edisiswanto.moviecatalogue.databinding.ItemsMovieBinding
import com.edisiswanto.moviecatalogue.ui.detailMovie.DetailMovieActivity
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment.Companion.API_IMAGE_ENDPOINT
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment.Companion.ENDPOINT_POSTER_SIZE_W185
import com.edisiswanto.moviecatalogue.utils.Helper.setImageWithGlide

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()

    fun setCourses(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text = itemView.resources.getString(
                    R.string.release_date,
                    movie.relesaseDate
                )

                setImageWithGlide(
                    itemView.context,
                    API_IMAGE_ENDPOINT + ENDPOINT_POSTER_SIZE_W185 + movie.posterPath,
                    imgPoster
                )

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }


}