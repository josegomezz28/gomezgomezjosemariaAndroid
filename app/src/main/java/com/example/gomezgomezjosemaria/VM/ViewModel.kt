package com.example.gomezgomezjosemaria.VM

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.gomezgomezjosemaria.model.Jugador
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

    class ViewModel : ViewModel() {

        private val db = FirebaseFirestore.getInstance()
        private val jugadoresCollection = db.collection("Jugador")

        private val _jugadores = MutableStateFlow<List<Jugador>>(emptyList())

        val Jugador: StateFlow<List<Jugador>> = _jugadores

        private var listener: ListenerRegistration? = null

        init {
            listener = jugadoresCollection.addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("Firebase", "Error", e)
                    return@addSnapshotListener
                }
                if (snapshots == null) return@addSnapshotListener

                val lista = snapshots.documents.mapNotNull { doc ->
                    val nacionalidad = doc.getString("Nacionalidad") ?: ""
                    val nombre = doc.getString("Nombre") ?: return@mapNotNull null
                    val numero = doc.getDouble("Numero") ?: 0.0  // Queria ponerlo Integer pero no me dejaba opcion
                    val posicion = doc.getString("Posicion") ?: ""

                    Jugador(
                        id = doc.id,
                        nacionalidad = nacionalidad,
                        nombre = nombre,
                        numero = numero,
                        posicion = posicion
                    )
                }
                _jugadores.value = lista
            }
        }
        fun addJugador(
            nombre: String,
            nacionalidad:String,
            numero: Double,
            posicion: String
        ) {
            val data = hashMapOf(
                "nombre" to nombre,
                "nacionalidad" to nacionalidad,
                "numero" to numero,
                "posicion" to posicion
            )

            jugadoresCollection.add(data)
        }
    }