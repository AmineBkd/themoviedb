package com.example.themoviedb.data.datasource

import android.graphics.Bitmap
import androidx.core.content.res.ResourcesCompat
import com.example.themoviedb.R
import com.example.themoviedb.data.network.MovieImageApiService


class MovieImageDataSource (private val api : MovieImageApiService) {
    suspend fun getImage(id: String): Bitmap {
        val image = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)
        return image
    }
}