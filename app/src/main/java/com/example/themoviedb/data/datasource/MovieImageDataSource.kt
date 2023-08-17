package com.example.themoviedb.data.datasource

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.themoviedb.data.network.MovieImageApiService


class MovieImageDataSource (private val api : MovieImageApiService) {
    //TODO(use a dependency such as picasso instead of handling it with Bitmap)
    suspend fun getImage(id: String): Bitmap {
        val byteArray = api.getMovieImage(id).bytes()
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}