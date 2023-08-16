package com.example.themoviedb.ui.movies.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.allViews
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.ItemMovieBinding
import java.lang.Exception

class MovieAdapter(
    private val movieList: List<Movie>,
    private val imageList: List<Bitmap>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemMovieBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_movie, parent, false)

        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        try {
            val movieImageView: ImageView = holder.itemView.findViewById<ImageView>(R.id.movieImage)
            val bitmap = imageList[position]
            movieImageView.setImageDrawable(
                BitmapDrawable(movieImageView.resources, bitmap)
            )
        }catch (e: Exception){
            Log.d("Crash", e.message.toString())
        }

        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.count()
}