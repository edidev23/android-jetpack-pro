package com.edisiswanto.moviecatalogue.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.databinding.ItemsMovieBinding
import com.edisiswanto.moviecatalogue.ui.detailMovie.DetailMovieActivity
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment.Companion.API_IMAGE_ENDPOINT
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment.Companion.ENDPOINT_POSTER_SIZE_W185
import com.edisiswanto.moviecatalogue.utils.Helper.setImageWithGlide

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null) {
            holder.bind(course)
        }
    }

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
                    val intent = Intent(
                        itemView.context,
                        DetailMovieActivity::class.java
                    )
                    intent.putExtra(
                        DetailMovieActivity.EXTRA_MOVIE,
                        movie
                    )
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

}