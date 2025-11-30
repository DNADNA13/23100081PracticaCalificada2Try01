package dev.equipo.a23100081practicacalificada01.presentation.ApiPokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dev.equipo.a23100081practicacalificada01.data.remote.ApiPokedex.PokemonDetailResponse
import dev.equipo.a23100081practicacalificada01.data.remote.ApiPokedex.RetrofitPoke

@Composable
fun PokemonDetailScreen(name: String) {

    var pokemon by remember { mutableStateOf<PokemonDetailResponse?>(null) }

    LaunchedEffect(Unit) {
        pokemon = RetrofitPoke.api.getPokemonDetail(name)
    }

    pokemon?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = rememberAsyncImagePainter(it.sprites.front_default),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Text(it.name.uppercase(), style = MaterialTheme.typography.headlineMedium)
            Text("Altura: ${it.height}")
            Text("Peso: ${it.weight}")
            Text("Tipo: ${it.types.first().type.name}")
        }
    }
}
