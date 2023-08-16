package com.example.themoviedb.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.themoviedb.data.Image
import com.example.themoviedb.data.Movie
import com.example.themoviedb.data.datasource.MovieDataSource
import com.example.themoviedb.data.datasource.MovieImageDataSource
import com.example.themoviedb.data.network.MovieApi
import com.example.themoviedb.data.network.MovieImageApi


class MovieImageRepository() {
    private val apiService = MovieImageApi().retrofitService
    private val dataSource = MovieImageDataSource(apiService)

    private suspend fun getImage(path: String, movie: Movie): Image {
        return Image(
            movieId = movie.id,
            path=path,
            bitmapImage =  dataSource.getImage(path),
            movieName = movie.name
        )
    }

    suspend fun getImages(movies: List<Movie>): List<Image>{
        val images = mutableListOf<Image>()
        movies.forEach { movie: Movie ->
            if(movie.posterPath.isNullOrBlank()){
                val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                val image = Image(-1, "Err 404", bitmap, "")

                images.add(image)
                Log.d("DEBUG", "null Image")
            }else{
                val image = getImage(path = movie.posterPath, movie)
                images.add(image)
            }
        }

        return images
    }
}