package com.example.themoviedb.ui.home.adapter

import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.data.Image
import com.example.themoviedb.data.Movie
import com.example.themoviedb.databinding.ItemMovieBinding
import com.google.android.material.card.MaterialCardView

class MovieAdapter(
    private val movieList: List<Movie>,
    private val imageList: List<Image>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    lateinit var cardListener: ((Movie, Image) -> Unit)

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

        Log.d("DEBUG", "currentPosition: $position,imageCount: ${imageList.count()} ,movieCount: ${movieList.count()}")

        loadImages(holder.itemView.findViewById(R.id.movieImage), position)
        cardView.setOnClickListener {
            cardListener(movie, imageList[position])
        }

        holder.bind(movie)
    }

    override fun getItemCount(): Int = movieList.count()

    private fun loadImages(imageView:ImageView, position:Int){
        try{
            val movieImageView: ImageView = imageView
            val image = imageList[position]

            if(image.path.isNullOrEmpty()) {
                //placeHolder
                movieImageView.setImageResource(R.drawable.missing_image)
            }else{
                movieImageView.setImageDrawable(
                    BitmapDrawable(movieImageView.resources, image.bitmapImage)
                )
            }
        }catch (e: Exception){
            Log.e("Bitmap Error", "loadImages ${e.message}", )
        }
    }
}