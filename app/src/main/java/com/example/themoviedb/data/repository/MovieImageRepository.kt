package com.example.themoviedb.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.themoviedb.data.Movie
import com.example.themoviedb.data.datasource.MovieDataSource
import com.example.themoviedb.data.datasource.MovieImageDataSource
import com.example.themoviedb.data.network.MovieApi
import com.example.themoviedb.data.network.MovieImageApi


class MovieImageRepository() {
    private val apiService = MovieImageApi().retrofitService
    private val dataSource = MovieImageDataSource(apiService)
    private suspend fun getImage(id: String): Bitmap {
        return dataSource.getImage(id)
    }

    suspend fun getImages(movies: List<Movie>): List<Bitmap>{
        val images = mutableListOf<Bitmap>()
        movies.forEach { movie: Movie ->
            movie.posterPath?.let {
                val bitmap = getImage(it)
                images.add(bitmap)
            } ?: {
                //TODO("Add a default image")
                val bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                images.add(bitmap)
            }
        }

        return images
    }
}