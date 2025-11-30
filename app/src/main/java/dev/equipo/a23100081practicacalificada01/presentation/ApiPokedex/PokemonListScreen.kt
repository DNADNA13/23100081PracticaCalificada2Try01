package dev.equipo.a23100081practicacalificada01.presentation.ApiPokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun PokemonListScreen(navController: NavController, viewModel: PokemonViewModel = viewModel()) {

    val pokemons = viewModel.pokemonList

    LazyColumn {
        items(pokemons) { pokemon ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("detail/${pokemon.name}")
                    }
            ) {
                Row(Modifier.padding(16.dp)) {

                    val imageUrl =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${extractId(pokemon.url)}.png"

                    Image(
                        painter = rememberAsyncImagePainter(imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(64.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(pokemon.name.uppercase())
                }
            }
        }
    }
}

fun extractId(url: String): String {
    return url.trimEnd('/').split("/").last()
}
