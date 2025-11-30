package dev.equipo.a23100081practicacalificada01.data.remote.ApiPokedex

data class PokemonDetailResponse(
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<PokemonTypeSlot>,
    val sprites: PokemonSprites
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val name: String
)

data class PokemonSprites(
    val front_default: String
)
