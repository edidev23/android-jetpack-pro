package com.edisiswanto.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edisiswanto.moviecatalogue.databinding.FragmentMovieBinding
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory
import com.edisiswanto.moviecatalogue.vo.Status

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]

            val movieAdapter = MovieAdapter()

            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->

                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
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

    companion object {
        const val API_IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/"
        const val ENDPOINT_POSTER_SIZE_W185 = "w185"
    }

}