package com.example.themoviedb.ui.movies


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Page
import com.example.themoviedb.data.network.MovieApi
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val _moviePage = MutableLiveData<Page>()
    val moviePage: LiveData<Page> = _moviePage

    fun loadPage(pageNumber: Int = 1){
        viewModelScope.launch {
            try {
                val retrofit = MovieApi().retrofitService
                val page = retrofit.getPage(pageNumber)
                Log.d("GOOD", "loadMovies")
                _moviePage.value = page
            }catch (e: Exception){
                Log.d("RETROFIT", "Failure: ${e.message}")
            }
        }
    }
}