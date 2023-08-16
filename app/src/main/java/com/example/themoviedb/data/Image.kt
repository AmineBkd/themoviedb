package com.example.themoviedb.data

import android.graphics.Bitmap

data class Image(
    val movieId: Int,
    val movieName: String,
    val bitmapImage: Bitmap,
    val path: String?
)