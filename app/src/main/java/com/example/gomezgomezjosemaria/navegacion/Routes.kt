package com.example.gomezgomezjosemaria.navegacion

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {

    @Serializable
    data object Login: Routes()

    @Serializable
    data class Home(val email: String) : Routes()

    @Serializable
    data class  Agregar(val id: String, val nombre:String, val numero:Int, val nacionalidad:String,
                           val posicion: String)





}