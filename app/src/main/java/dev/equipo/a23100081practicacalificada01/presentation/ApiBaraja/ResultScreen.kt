package dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


@Composable
fun ResultScreen(viewModel: BlackjackViewModel, onPlayAgain: () -> Unit) {
    val score = viewModel.calculateScore(viewModel.playerCards)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tus cartas:")
        Row {
            viewModel.playerCards.forEach { card ->
                Image(
                    painter = rememberAsyncImagePainter(card.image),
                    contentDescription = card.code,
                    modifier = Modifier.size(80.dp).padding(4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Tu puntaje: $score")
        Text("Puntaje del dealer: ${viewModel.dealerScore}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (score > viewModel.dealerScore) "¡Ganaste!" else "Perdiste",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onPlayAgain) { Text("Jugar otra vez") }
    }
}
