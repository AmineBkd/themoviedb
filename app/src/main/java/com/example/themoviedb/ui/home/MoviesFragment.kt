package com.example.themoviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.data.Image
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.FragmentMoviesBinding
import com.example.themoviedb.ui.home.adapter.MovieAdapter

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = MovieAdapter(listOf<Movie>(), listOf<Image>())
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPage(viewModel.currentPage)

        viewModel.moviePage.observe(viewLifecycleOwner){ (page, image) ->
            if(page.totalResult > 0){
                loadingPageUI(page.movies, image)
            }
        }

        viewModel.loadingPage.observe(viewLifecycleOwner){ loading ->
            when(loading){
                true -> binding.loadingOverlay.visibility = View.VISIBLE
                false -> binding.loadingOverlay.visibility = View.GONE
            }
        }

        viewModel.searchPage.observe(viewLifecycleOwner){ (page, image) ->
            loadingPageUI(page.movies, image)
        }

        binding.searchField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.searchMovie(text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun paginationUI(){
        var isPreviousButtonClickable = false
        var isNextButtonClickable = false

        binding.paginationView.visibility = View.VISIBLE
        binding.paginationCurrentPageTextView.text = viewModel.currentPage.toString()

        if(viewModel.currentPage > 0) isPreviousButtonClickable = true
        if(viewModel.currentPage < viewModel.maxPage) isNextButtonClickable = true

        binding.paginationPreviousButton.isClickable = isPreviousButtonClickable
        binding.paginationNextButton.isClickable = isNextButtonClickable

        binding.paginationPreviousButton.setOnClickListener {
            if(it.isClickable) viewModel.loadPage(viewModel.currentPage - 1)
            binding.searchField.editText?.editableText?.clear()
        }

        binding.paginationNextButton.setOnClickListener {
            if(it.isClickable) viewModel.loadPage(viewModel.currentPage + 1)
            binding.searchField.editText?.editableText?.clear()
        }
    }

    fun loadingPageUI(movies: List<Movie>, images:List<Image>){
        val movieAdapter = MovieAdapter(movies, images)
        binding.recyclerView.adapter = movieAdapter
        paginationUI()

        movieAdapter.cardListener = { movie, movieImage ->
            val action =
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
                    movie, movieImage
                )
            findNavController().navigate(action)
        }
    }
}