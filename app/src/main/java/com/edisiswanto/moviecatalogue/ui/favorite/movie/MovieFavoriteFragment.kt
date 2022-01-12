package com.edisiswanto.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edisiswanto.moviecatalogue.databinding.FragmentMovieFavoriteBinding
import com.edisiswanto.moviecatalogue.ui.movie.MovieAdapter
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment() {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieFavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[MovieFavoriteViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getMoviesBookmark().observe(viewLifecycleOwner, { movies ->

                if (movies != null) {
                    movieAdapter.submitList(movies)
                }
                with(binding.rvMovie) {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = movieAdapter
                }
            })

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}