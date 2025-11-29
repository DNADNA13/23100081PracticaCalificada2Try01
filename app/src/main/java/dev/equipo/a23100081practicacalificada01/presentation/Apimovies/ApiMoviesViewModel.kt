package dev.equipo.a23100081practicacalificada01.presentation.Apimovies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.equipo.a23100081practicacalificada01.data.remote.Apimovies.Movie
import dev.equipo.a23100081practicacalificada01.data.remote.Apimovies.RetrofitInstanceMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ApiMoviesViewModel : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitInstanceMovie.api.getPopularMovies()
                _movies.value = response.results
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar películas: ${e.message}"
                _movies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
