package dev.equipo.a23100081practicacalificada01.data.model

data class DeckResponse(
    val success: Boolean,
    val deck_id: String,
    val remaining: Int,
    val shuffled: Boolean
)

data class DrawResponse(
    val success: Boolean,
    val cards: List<Card>,
    val remaining: Int
)

data class Card(
    val code: String,
    val image: String,
    val value: String,
    val suit: String
)
