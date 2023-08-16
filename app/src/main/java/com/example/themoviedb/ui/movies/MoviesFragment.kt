package com.example.themoviedb.ui.movies

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.FragmentMoviesBinding
import com.example.themoviedb.ui.movies.adapter.MovieAdapter

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = MovieAdapter(listOf<Movie>(), listOf<Bitmap>())
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPage()

        viewModel.moviePage.observe(viewLifecycleOwner){ (page, image) ->
            if(page.totalResult > 0){
                val movieAdapter = MovieAdapter(page.movies, image)
                binding.recyclerView.adapter = movieAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}