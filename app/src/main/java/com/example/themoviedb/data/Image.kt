package com.example.themoviedb.data

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val movieId: Int,
    val movieName: String,
    val bitmapImage: Bitmap,
    val path: String?
) : Parcelable