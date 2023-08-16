package com.example.themoviedb.data.repository


import com.example.themoviedb.data.Page
import com.example.themoviedb.data.datasource.MovieDataSource
import com.example.themoviedb.data.network.MovieApi
import com.example.themoviedb.data.network.MovieApiService

class MovieRepository {
    private val apiService = MovieApi().retrofitService
    private val dataSource: MovieDataSource = MovieDataSource(apiService)

    suspend fun getPage(page: Int, query: String = "comedy"): Page {
        return dataSource.getPage(page, query)
    }
}