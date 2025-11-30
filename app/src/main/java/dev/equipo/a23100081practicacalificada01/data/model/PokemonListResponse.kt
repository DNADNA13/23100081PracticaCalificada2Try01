package dev.equipo.a23100081practicacalificada01.data.model

data class PokemonListResponse(
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)