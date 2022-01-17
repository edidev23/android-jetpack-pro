package com.edisiswanto.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.databinding.FragmentMovieBinding
import com.edisiswanto.moviecatalogue.utils.SortUtils
import com.edisiswanto.moviecatalogue.viewmodel.ViewModelFactory
import com.edisiswanto.moviecatalogue.vo.Status
import javax.inject.Inject

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var factory: ViewModelFactory

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

            factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(
                this,
                factory
            )[MovieViewModel::class.java]

            movieAdapter = MovieAdapter()

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

            binding.btnNews.setOnClickListener {
                viewModel.setSort(SortUtils.NEWEST)
                viewModel.movieSort.observe(viewLifecycleOwner, movieObserver)
            }

            binding.btnOldest.setOnClickListener {
                viewModel.setSort(SortUtils.OLDEST)
                viewModel.movieSort.observe(viewLifecycleOwner, movieObserver)
            }

            binding.btnRandom.setOnClickListener {
                viewModel.setSort(SortUtils.RANDOM)
                viewModel.movieSort.observe(viewLifecycleOwner, movieObserver)
            }
        }
    }

    private val movieObserver = Observer<PagedList<MovieEntity>> { movieList ->
        if (movieList != null) {
             movieAdapter.submitList(movieList)
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