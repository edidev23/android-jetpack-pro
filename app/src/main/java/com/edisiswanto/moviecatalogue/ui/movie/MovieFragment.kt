package com.edisiswanto.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edisiswanto.moviecatalogue.databinding.FragmentMovieBinding
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            viewModel = ViewModelProvider(this,
                factory
            )[MovieViewModel::class.java]

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->

                binding.progressBar.visibility = View.INVISIBLE
                val movieAdapter = MovieAdapter()
                movieAdapter.setCourses(movies)
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

    companion object {
        const val API_IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/"
        const val ENDPOINT_POSTER_SIZE_W185 = "w185"
    }
}