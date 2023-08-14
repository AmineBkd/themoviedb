package com.example.themoviedb.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name="genre_ids") val genreIds: List<Int>,
    val id: Int,
    @Json(name="origin_country") val originalCountry: List<String>,
    @Json(name="original_language") val originalLanguage: String,
    @Json(name="original_name") val originalName: String,
    val overview: String,
    val popularity: Double,
    @Json(name="poster_path") val posterPath: String?,
    @Json(name="first_air_date") val firstAiringDate: String,
    val name: String,
    @Json(name="vote_average") val voteAverage: Double,
    @Json(name="vote_count") val voteCount: Int,
) : Parcelable