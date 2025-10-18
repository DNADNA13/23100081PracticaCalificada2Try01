package dev.equipo.a23100081practicacalificada01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.equipo.a23100081practicacalificada01.presentation.navigation.AppNavGraph
import dev.equipo.a23100081practicacalificada01.ui.theme._23100081PracticaCalificada01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _23100081PracticaCalificada01Theme {
                        AppNavGraph()

                }
            }
        }
    }

