package com.edisiswanto.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.databinding.ItemsTvBinding
import com.edisiswanto.moviecatalogue.ui.detailTV.DetailTvActivity
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment
import com.edisiswanto.moviecatalogue.utils.Helper.setImageWithGlide

class TvAdapter: PagedListAdapter<TvEntity, TvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvEntity>() {
            override fun areItemsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvEntity, newItem: TvEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsTvBinding =
            ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class TvViewHolder(private val binding: ItemsTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvEntity) {
            with(binding) {
                tvItemTitle.text = tvShow.name
                tvItemDate.text = itemView.resources.getString(
                    R.string.release_date,
                    tvShow.firstAirDate
                )

                setImageWithGlide(
                    itemView.context,
                    MovieFragment.API_IMAGE_ENDPOINT + MovieFragment.ENDPOINT_POSTER_SIZE_W185 + tvShow.posterPath,
                    imgPoster
                )

                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        DetailTvActivity::class.java
                    )
                    intent.putExtra(
                        DetailTvActivity.EXTRA_TV,
                        tvShow
                    )
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvEntity? = getItem(swipedPosition)
}
