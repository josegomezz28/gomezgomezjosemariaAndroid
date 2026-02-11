package com.example.gomezgomezjosemaria.View

import android.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gomezgomezjosemaria.model.Jugador

@Composable
fun JugadoresCard(
    jugador: Jugador,
    onView: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(jugador.nombre, style = MaterialTheme.typography.titleMedium)
                //Int(jugador.numero, style = MaterialTheme.typography.bodyMedium)
                Text(jugador.posicion, style = MaterialTheme.typography.bodyMedium)
                Text(jugador.nacionalidad, style = MaterialTheme.typography.bodyMedium)

            }
        }
    }
}