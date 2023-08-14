package com.example.themoviedb.data.network

import com.example.themoviedb.data.Page
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/search/"
private const val API_KEY = "8d6a13d4ff986513574e73180f498ed9"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {
    @GET("tv")
    suspend fun getPage(
        @Query("page") page: Int = 1,
        @Query("query") query: String = "comedy",
        @Query("include_adult") adultContent: Boolean = false,
        @Query("api_key") apiKey: String = API_KEY
    ): Page
}

class MovieApi {
    val retrofitService: MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
}
