package com.example.gomezgomezjosemaria.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.gomezgomezjosemaria.pantallas.PantallaLogin
import com.google.firebase.auth.FirebaseAuth
import kotlin.text.clear

@Composable
fun GestionNavegacion(auth: FirebaseAuth) {

    val emailActual = auth.currentUser?.email

    val pilaNavegacion = rememberNavBackStack(
         if (emailActual != null) Routes.Home(emailActual) else Routes.Login
    )
        NavDisplay(
            backStack = pilaNavegacion,
            onBack = { pilaNavegacion.removeLastOrNull() },
            entryProvider = { route ->
                when (route) {
                    Routes.Login -> NavEntry(route) {
                        PantallaLogin(
                            auth = auth,
                            onLoginSuccess = { email ->
                                pilaNavegacion.clear()
                                pilaNavegacion.add(Routes.Home(email))
                            }
                        )
                    }
                    is Routes.Home -> NavEntry(route) {
                        PantallaHome(
                            email = route.email,
                            onLogout = {
                                auth.signOut()
                                pilaNavegacion.clear()
                                pilaNavegacion.add(Routes.Login)
                            },


                        )
                    }

        )}
}