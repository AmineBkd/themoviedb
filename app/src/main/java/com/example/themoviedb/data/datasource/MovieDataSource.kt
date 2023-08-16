package com.example.themoviedb.data.datasource

import com.example.themoviedb.data.Page
import com.example.themoviedb.data.network.MovieApi
import com.example.themoviedb.data.network.MovieApiService

class MovieDataSource (private val api : MovieApiService) {
    suspend fun getPage(page: Int, query: String = "comedy"): Page {
        return api.getPage(page, query, false)
    }
}