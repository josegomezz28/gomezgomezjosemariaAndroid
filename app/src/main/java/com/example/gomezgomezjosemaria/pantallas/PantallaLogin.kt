package com.example.gomezgomezjosemaria.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.Image
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PantallaLogin(
    auth: FirebaseAuth,
    onLoginSuccess: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cargando by remember { mutableStateOf(false) }
    var mensajeerror by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(18.dp))

        Text("Incia sesion", fontSize = 50.sp)

        Spacer(Modifier.height(18.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true
        )

        Spacer(Modifier.height(18.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")
            },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(Modifier.height(18.dp))

        if (mensajeerror != null) {
            Text(
                text = mensajeerror!!
            )
            Spacer(Modifier.height(10.dp))
        }

        Button(
            modifier = Modifier.width(220.dp),
            enabled = !cargando,
            onClick = {
                mensajeerror = null

                val e = email.trim()
                val p = password

                if (e.isBlank() || p.isBlank()) {
                    mensajeerror = "Introduce el email y contraseña"
                    return@Button
                }

                cargando = true
                auth.signInWithEmailAndPassword(e, p)
                    .addOnCompleteListener { task ->
                        cargando = false
                        if (task.isSuccessful) {
                            onLoginSuccess(e)
                        } else {
                            mensajeerror = task.exception?.localizedMessage ?: "Ha ocurrido un error al iniciar sesión"
                        }
                    }
            }
        ) {
            if (cargando) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(15.dp))
                Text("Accediendo...")
            } else {
                Text("Acceder")
            }
        }

    }
}