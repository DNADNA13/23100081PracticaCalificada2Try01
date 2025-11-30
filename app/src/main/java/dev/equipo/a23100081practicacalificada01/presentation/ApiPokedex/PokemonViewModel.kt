package dev.equipo.a23100081practicacalificada01.presentation.ApiPokedex

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.equipo.a23100081practicacalificada01.data.model.PokemonBasic
import dev.equipo.a23100081practicacalificada01.data.remote.ApiPokedex.RetrofitPoke
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    var pokemonList by mutableStateOf<List<PokemonBasic>>(emptyList())
        private set

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            try {
                val response = RetrofitPoke.api.getPokemonList()
                pokemonList = response.results.map { result ->
                    PokemonBasic(
                        name = result.name,
                        url = result.url
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
