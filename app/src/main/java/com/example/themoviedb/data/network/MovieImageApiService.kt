package com.example.themoviedb.data.network

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://image.tmdb.org/t/p/w500/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface MovieImageApiService {
    @GET("{posterPath}")
    suspend fun getMovieImage(
        @Path("posterPath") posterPath: String
    ): ResponseBody
}

class MovieImageApi {
    val retrofitService: MovieImageApiService by lazy { retrofit.create(MovieImageApiService::class.java) }
}
