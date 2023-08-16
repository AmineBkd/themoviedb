package com.example.themoviedb.ui.home


import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Page
import com.example.themoviedb.data.repository.MovieImageRepository
import com.example.themoviedb.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val _moviePage = MutableLiveData< Pair<Page, List<Bitmap>> >()
    val moviePage = _moviePage

    private var movieRepository: MovieRepository = MovieRepository()
    private var movieImageRepository: MovieImageRepository = MovieImageRepository()

    fun loadPage(pageNumber: Int = 1){
        viewModelScope.launch {
            try {
                val page = movieRepository.getPage(pageNumber)
                val movieImages = movieImageRepository.getImages(page.movies)

                _moviePage.value = Pair(page, movieImages)
            }catch (e: Exception){
                Log.d("RETROFIT", "Failure: ${e.message}")
            }
        }
    }
}