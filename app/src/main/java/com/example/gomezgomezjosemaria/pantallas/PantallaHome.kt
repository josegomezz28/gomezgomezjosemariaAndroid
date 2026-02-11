package com.example.gomezgomezjosemaria.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gomezgomezjosemaria.VM.ViewModel
import com.example.gomezgomezjosemaria.View.JugadoresCard

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaHome(
        email: String,
        onLogout: () -> Unit,
        onViewProducto: (id: String, nombre: String, numero: Double,nacionalidad: String,posicion:String) -> Unit,
        viewModel: ViewModel = viewModel()
    ) {

    val jugadores by viewModel.Jugador.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var nacionalidad by remember { mutableStateOf("") }
    var posicion by remember { mutableStateOf("") }
    var editandoId by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("Plantilla Temporada 25/26") },
        )
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(jugadores) { jug ->
                JugadoresCard(
                    jugadores = jug,
                    onView = {
                        onViewJugador(jug.id, jug.nombre, jug.numero,jug.nacionalidad,jug.posicion)
                    },
                )
                Spacer(Modifier.height(8.dp))
            }
        }

    }
}