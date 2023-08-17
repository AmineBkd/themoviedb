package com.example.themoviedb.ui.home


import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themoviedb.data.Image
import com.example.themoviedb.data.Movie
import com.example.themoviedb.data.Page
import com.example.themoviedb.data.repository.MovieImageRepository
import com.example.themoviedb.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val _loadingPage = MutableLiveData<Boolean>()
    val loadingPage = _loadingPage

    private val _searchPage = MutableLiveData< Pair<Page, List<Image>> >()
    val searchPage = _searchPage

    private val _moviePage = MutableLiveData< Pair<Page, List<Image>> >()
    val moviePage = _moviePage

    private var movieRepository: MovieRepository = MovieRepository()
    private var movieImageRepository: MovieImageRepository = MovieImageRepository()

    var currentPage: Int = 1
    var maxPage: Int = 2
    var searchValue: String = ""

    fun loadPage(pageNumber: Int = 1){
        if( (pageNumber > 0) and (pageNumber < maxPage) ){
            loadingPage.value = true
            viewModelScope.launch {
                try {
                    val page = movieRepository.getPage(pageNumber)
                    val movieImages = movieImageRepository.getImages(page.movies)

                    currentPage = pageNumber
                    maxPage = page.totalPages

                    _moviePage.value = Pair(page, movieImages)
                    loadingPage.value = false
                }catch (e: Exception){
                    Log.d("RETROFIT", "Failure: ${e.message}")
                }
            }
        }
    }

    fun searchMovie() {
        if(searchValue.isNotEmpty()){
            val movies = moviePage.value?.first?.movies?.filter {
                it.name.contains(searchValue, ignoreCase = true)
            }
            val movieIds = (movies?.map { it.id })!!

            val images: List<Image> = (moviePage.value?.second?.filter {
                it.movieId in movieIds
            })!!

            val page: Page = (moviePage.value?.first?.totalResult?.let {
                Page(currentPage, movies, totalPages = maxPage, totalResult = it)
            })!!

            _searchPage.value = Pair(page, images)
        }else{
            _searchPage.value = moviePage.value
        }
    }

    fun sortMovie(sortingType: String, availableSorts: ArrayAdapter<CharSequence>){
        var movies: List<Movie> = listOf<Movie>()
        var images: List<Image> = listOf<Image>()

        when(sortingType){
            availableSorts.getItem(0) -> {
                //Alphabetical
                movies = moviePage.value?.first?.movies?.sortedBy { it.name }!!
                images = (moviePage.value?.second?.sortedBy { it.movieName }!!)
            }
            availableSorts.getItem(1) -> {
                //Date
                movies = moviePage.value?.first?.movies?.sortedBy { it.firstAiringDate }!!
                val movieIndexMap = movies.mapIndexed { index, movie ->
                    movie.id to index
                }.toMap()
                images = (moviePage.value?.second?.sortedBy { movieIndexMap[it.movieId] }!!)
            }
        }

        val page: Page = (moviePage.value?.first?.totalResult?.let {
            Page(currentPage, movies, totalPages = maxPage, totalResult = it)
        })!!

        _searchPage.value = Pair(page, images)
    }

}