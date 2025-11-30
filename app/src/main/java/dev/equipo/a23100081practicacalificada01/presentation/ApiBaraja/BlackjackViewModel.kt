package dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.equipo.a23100081practicacalificada01.data.model.Card
import dev.equipo.a23100081practicacalificada01.data.remote.ApiBaraja.DeckService

import kotlinx.coroutines.launch

class BlackjackViewModel : ViewModel() {
    var deckId by mutableStateOf("")
    var playerCards by mutableStateOf(listOf<Card>())
    var dealerScore by mutableStateOf(0)
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")

    fun createDeck() {
        viewModelScope.launch {
            try {
                isLoading = true
                val response = DeckService.api.createDeck()
                deckId = response.deck_id
            } catch (e: Exception) {
                error = "Error al crear baraja: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun drawCards(count: Int) {
        viewModelScope.launch {
            try {
                isLoading = true
                val response = DeckService.api.drawCards(deckId, count)
                playerCards = response.cards
                dealerScore = (16..21).random()
            } catch (e: Exception) {
                error = "Error al tomar cartas: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun calculateScore(cards: List<Card>): Int {
        return cards.sumOf { card ->
            when (card.value) {
                "J", "Q", "K" -> 10
                "A" -> 11
                else -> card.value.toIntOrNull() ?: 0
            }
        }
    }
}
