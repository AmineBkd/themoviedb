package com.example.themoviedb.data.datasource

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.themoviedb.data.network.MovieImageApiService


class MovieImageDataSource (private val api : MovieImageApiService) {
    suspend fun getImage(id: String): Bitmap {
        val byteArray = api.getMovieImage(id).bytes()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}