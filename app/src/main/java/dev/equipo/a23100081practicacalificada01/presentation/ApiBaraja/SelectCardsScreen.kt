package dev.equipo.a23100081practicacalificada01.presentation.ApiBaraja

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectCardsScreen(viewModel: BlackjackViewModel, onCardsDrawn: () -> Unit) {
    var count by remember { mutableStateOf(2) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Elige cuántas cartas tomar (2-5)")
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
            value = count.toFloat(),
            onValueChange = { count = it.toInt() },
            valueRange = 2f..5f,
            steps = 2
        )
        Text("Cartas seleccionadas: $count")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.drawCards(count)
            onCardsDrawn()
        }) { Text("Tomar cartas") }
        if (viewModel.isLoading) CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        if (viewModel.error.isNotEmpty()) Text(viewModel.error, color = MaterialTheme.colorScheme.error)
    }
}
