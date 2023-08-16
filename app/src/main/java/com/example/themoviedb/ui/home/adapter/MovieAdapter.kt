package com.example.themoviedb.ui.home.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.ItemMovieBinding
import com.google.android.material.card.MaterialCardView
import java.lang.Exception

class MovieAdapter(
    private val movieList: List<Movie>,
    private val imageList: List<Bitmap>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    lateinit var cardListener: ((Movie) -> Unit)

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
        val movie = movieList[position]
        val cardView = holder.itemView.findViewById<MaterialCardView>(R.id.cardView)

        cardView.setOnClickListener {
            cardListener(movie)
        }

        holder.bind(movie)
        loadingImages(holder.itemView.findViewById(R.id.movieImage), position)
    }

    override fun getItemCount(): Int = movieList.count()

    private fun loadingImages(imageView:ImageView, position:Int){
        try {
            val movieImageView: ImageView = imageView
            val bitmap = imageList[position]
            movieImageView.setImageDrawable(
                BitmapDrawable(movieImageView.resources, bitmap)
            )
        }catch (e: Exception){
            //silent error-handler
            Log.d("Crash", e.message.toString())
        }
    }
}