package dev.equipo.a23100081practicacalificada01.presentation.Apimovies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun ApiMoviesScreen(viewModel: ApiMoviesViewModel = viewModel()) {

    val movies by viewModel.movies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "Películas Populares",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(movies) { movie ->
                        Row(modifier = Modifier.fillMaxWidth()) {

                            Image(
                                painter = rememberAsyncImagePainter(
                                    "https://image.tmdb.org/t/p/w500${movie.poster_path}"
                                ),
                                contentDescription = movie.title,
                                modifier = Modifier.size(80.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column {
                                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                                Text(
                                    movie.release_date ?: "Sin fecha",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
