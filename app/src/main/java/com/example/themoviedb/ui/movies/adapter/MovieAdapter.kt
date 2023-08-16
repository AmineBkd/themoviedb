package com.example.themoviedb.ui.movies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.ItemMovieBinding
import com.google.android.material.card.MaterialCardView

class MovieAdapter(
    private val moviesList: List<Movie>
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
        //val cardView = holder.itemView.findViewById<MaterialCardView>(R.id.cardView)
        val movie = moviesList[position]
        Log.d("DEBUG", movie.toString())
        holder.bind(movie)
    }

    override fun getItemCount(): Int = moviesList.count()
}