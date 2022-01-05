package com.edisiswanto.moviecatalogue.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.data.TvEntity
import com.edisiswanto.moviecatalogue.databinding.ItemsTvBinding
import com.edisiswanto.moviecatalogue.ui.detailTV.DetailTvActivity
import com.edisiswanto.moviecatalogue.ui.movie.MovieFragment

class TvAdapter: RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private val listTv = ArrayList<TvEntity>()

    fun setTv(data: List<TvEntity>?) {
        if (data == null) return
        this.listTv.clear()
        this.listTv.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemTvBinding = ItemsTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemTvBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val data = listTv[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listTv.size

    inner class TvViewHolder(private val binding: ItemsTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvEntity) {
            with(binding) {
                tvItemTitle.text = data.name
                tvItemDate.text = itemView.resources.getString(R.string.show_date, data.firstAirDate)

                Glide.with(itemView.context).load(MovieFragment.API_IMAGE_ENDPOINT + MovieFragment.ENDPOINT_POSTER_SIZE_W185 + data.posterPath).apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                        .transform(RoundedCorners(20))
                ).into(imgPoster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailTvActivity::class.java)
                    intent.putExtra(DetailTvActivity.EXTRA_TV, data)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
