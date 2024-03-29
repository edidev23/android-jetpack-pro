package com.edisiswanto.moviecatalogue.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.databinding.FragmentTvBinding
import com.edisiswanto.moviecatalogue.utils.SortUtils
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory
import com.edisiswanto.moviecatalogue.vo.Status

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TvViewModel
    private lateinit var tvAdapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[TvViewModel::class.java]

            tvAdapter = TvAdapter()

            viewModel.getTvShow().observe(viewLifecycleOwner, { tvShow ->

                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            tvAdapter.submitList(tvShow.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                with(binding.rvTv) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = tvAdapter
                }
            })

            binding.btnNews.setOnClickListener {
                viewModel.setSort(SortUtils.NEWEST)
                viewModel.tvSort.observe(viewLifecycleOwner, tvObserver)
            }

            binding.btnOldest.setOnClickListener {
                viewModel.setSort(SortUtils.OLDEST)
                viewModel.tvSort.observe(viewLifecycleOwner, tvObserver)
            }

            binding.btnRandom.setOnClickListener {
                viewModel.setSort(SortUtils.RANDOM)
                viewModel.tvSort.observe(viewLifecycleOwner, tvObserver)
            }
        }
    }

    private val tvObserver = Observer<PagedList<TvEntity>> { tvList ->
        if (tvList != null) {
            tvAdapter.submitList(tvList)
        }
    }
}
