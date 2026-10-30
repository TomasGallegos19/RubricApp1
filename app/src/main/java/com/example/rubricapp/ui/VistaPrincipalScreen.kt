package com.example.rubricapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VistaPrincipalScreen(
    viewModel: AuthViewModel,
    onNavegarGestion: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    // Obtenemos el rol del usuario actual (por defecto Docente si hubiera algún detalle)
    val rolUsuario = viewModel.usuarioActual?.rol ?: "Docente"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Panel de $rolUsuario",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sesión iniciada como: ${viewModel.usuarioActual?.correo ?: ""}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { onNavegarGestion() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Gestión de Evaluaciones")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { onCerrarSesion() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}