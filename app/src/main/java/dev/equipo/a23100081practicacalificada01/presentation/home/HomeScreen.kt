package dev.equipo.a23100081practicacalificada01.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val db = Firebase.firestore
    val auth = Firebase.auth

    // 🔹 Estado para las tasas obtenidas de Firestore
    var exchangeRates by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    var isLoading by remember { mutableStateOf(true) }

    // 🔹 Leer Firestore solo una vez (colección "rates")
    LaunchedEffect(Unit) {
        db.collection("rates")
            .document("base_USD")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.data != null) {
                    val data = document.data!!.mapValues {
                        (it.value as Number).toDouble()
                    }
                    exchangeRates = data
                    Toast.makeText(
                        context,
                        "Tasas cargadas: ${exchangeRates.size}",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(context, "No se encontraron tasas", Toast.LENGTH_SHORT).show()
                }
                isLoading = false
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error al leer Firestore", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
    }

    // 🔹 Estados para la UI
    var amount by remember { mutableStateOf("") }
    var fromCurrency by remember { mutableStateOf("USD") }
    var toCurrency by remember { mutableStateOf("EUR") }
    var result by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Conversor de Monedas", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        when {
            isLoading -> {
                // 🔹 Indicador de carga
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            exchangeRates.isEmpty() -> {
                // 🔹 Mensaje si no hay datos
                Text(
                    "No se pudieron cargar las tasas de cambio.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            else -> {
                val currencyList = exchangeRates.keys.toList()

                // Campo de texto del monto
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Monto") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Dropdown origen
                DropdownSelector("Moneda origen", currencyList, fromCurrency) { fromCurrency = it }

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown destino
                DropdownSelector("Moneda destino", currencyList, toCurrency) { toCurrency = it }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val value = amount.toDoubleOrNull()
                        if (value == null) {
                            Toast.makeText(
                                context,
                                "Ingresa un número válido",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val fromRate = exchangeRates[fromCurrency] ?: 1.0
                        val toRate = exchangeRates[toCurrency] ?: 1.0
                        val converted = value * (toRate / fromRate)
                        val resultText = String.format(
                            "%.2f %s equivalen a %.2f %s",
                            value,
                            fromCurrency,
                            converted,
                            toCurrency
                        )
                        result = resultText

                        // 🔹 Guardar conversión en Firestore
                        val user = auth.currentUser
                        if (user != null) {
                            val conversion = mapOf(
                                "uid" to user.uid,
                                "timestamp" to FieldValue.serverTimestamp(),
                                "amount" to value,
                                "fromCurrency" to fromCurrency,
                                "toCurrency" to toCurrency,
                                "result" to converted
                            )

                            db.collection("conversions")
                                .add(conversion)
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        context,
                                        "Conversión guardada",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(
                                        context,
                                        "Error al guardar conversión",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } else {
                            Toast.makeText(
                                context,
                                "Usuario no autenticado",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Convertir")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Mostrar resultado
                result?.let {
                    Text(it, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelector(
    label: String,
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { currency ->
                DropdownMenuItem(
                    text = { Text(currency) },
                    onClick = {
                        onSelected(currency)
                        expanded = false
                    }
                )
            }
        }
    }
}
