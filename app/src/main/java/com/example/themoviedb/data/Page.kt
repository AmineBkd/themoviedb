package com.example.themoviedb.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Page(
    val page: Int,
    @Json(name = "results") val movies: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResult: Int,
) : Parcelable