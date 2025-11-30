package dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateDeckScreen(viewModel: BlackjackViewModel, onDeckCreated: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (viewModel.deckId.isEmpty()) {
            Button(onClick = { viewModel.createDeck() }) { Text("Crear Baraja") }
        } else {
            Text("Baraja creada con ID: ${viewModel.deckId}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onDeckCreated) { Text("Siguiente") }
        }
        if (viewModel.isLoading) CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        if (viewModel.error.isNotEmpty()) Text(viewModel.error, color = MaterialTheme.colorScheme.error)
    }
}
