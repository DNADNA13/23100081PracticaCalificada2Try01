package dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja

import androidx.compose.runtime.*


enum class Screen { CREATE_DECK, SELECT_CARDS, RESULT }

@Composable
fun BlackjackApp() {
    val viewModel = remember { BlackjackViewModel() }
    var currentScreen by remember { mutableStateOf(Screen.CREATE_DECK) }

    when(currentScreen) {
        Screen.CREATE_DECK -> CreateDeckScreen(viewModel) { currentScreen = Screen.SELECT_CARDS }
        Screen.SELECT_CARDS -> SelectCardsScreen(viewModel) { currentScreen = Screen.RESULT }
        Screen.RESULT -> ResultScreen(viewModel) {
            currentScreen = Screen.CREATE_DECK
            viewModel.playerCards = listOf()
            viewModel.dealerScore = 0
        }
    }
}
