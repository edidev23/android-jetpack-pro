package com.edisiswanto.moviecatalogue.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edisiswanto.moviecatalogue.databinding.FragmentMovieBinding
import com.edisiswanto.moviecatalogue.databinding.FragmentTvBinding
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class TvFragment : Fragment() {

    private var _binding: FragmentTvBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TvViewModel

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

            val factory = ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this,
                factory
            )[TvViewModel::class.java]

            viewModel.getTv().observe(viewLifecycleOwner, { tvShow ->

                binding.progressBar.visibility = View.INVISIBLE
                val adapter = TvAdapter()
                adapter.setTv(tvShow)
                with(binding.rvTv) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    this.adapter = adapter
                }
            })
        }
    }

}
