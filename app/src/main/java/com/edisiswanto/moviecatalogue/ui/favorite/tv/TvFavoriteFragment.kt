package com.edisiswanto.moviecatalogue.ui.favorite.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edisiswanto.moviecatalogue.R
import com.edisiswanto.moviecatalogue.databinding.FragmentTvFavoriteBinding
import com.edisiswanto.moviecatalogue.ui.tv.TvAdapter
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class TvFavoriteFragment : Fragment() {
    private var _binding: FragmentTvFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TvFavoriteViewModel
    private lateinit var tvAdapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[TvFavoriteViewModel::class.java]

            tvAdapter = TvAdapter()

            itemTouchHelper.attachToRecyclerView(binding.rvTv)

            viewModel.getBookmarkedTv().observe(viewLifecycleOwner, { tvShow ->

                if (tvShow != null) {
                    tvAdapter.submitList(tvShow)
                }
                with(binding.rvTv) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                }
            })

        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = tvAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setBookmark(it) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setBookmark(it) }
                }
                snackbar.show()
            }
        }
    })

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}