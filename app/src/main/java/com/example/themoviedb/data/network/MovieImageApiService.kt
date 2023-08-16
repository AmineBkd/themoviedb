package com.example.themoviedb.data.network

import android.graphics.Bitmap
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL = "https://image.tmdb.org/t/p/w500/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .build()

interface MovieImageApiService {
    @GET
    suspend fun getMovieImage(
        @Url posterPath: String
    ): ResponseBody
}

class MovieImageApi {
    val retrofitService: MovieImageApiService by lazy { retrofit.create(MovieImageApiService::class.java) }
}
