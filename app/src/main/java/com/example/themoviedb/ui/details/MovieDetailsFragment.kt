package com.example.themoviedb.ui.details

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.themoviedb.R
import com.example.themoviedb.data.Image
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.FragmentMovieDetailsBinding
import com.example.themoviedb.databinding.FragmentMoviesBinding
import com.example.themoviedb.ui.home.adapter.MovieAdapter

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!


    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie

        binding.movieName.text = movie.name
        binding.movieDescription.text = movie.overview
        bindLoadImage()
        fillEmpty()
    }

    private fun bindLoadImage(){
        val image = args.image
        if(image.movieId == -1) {
            //placeHolder
            binding.detailsMovieImageView.setImageResource(R.drawable.missing_image)
        }else{
            binding.detailsMovieImageView.setImageDrawable(
                BitmapDrawable(binding.detailsMovieImageView.resources, image.bitmapImage)
            )
        }
    }

    private fun fillEmpty() {
        if(binding.movieDescription.text.isEmpty()) binding.movieDescription.text = "No Description"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}