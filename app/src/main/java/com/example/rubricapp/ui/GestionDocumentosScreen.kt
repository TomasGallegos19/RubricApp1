package com.example.rubricapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GestionDocumentosScreen(
    onNavegarEvaluar: () -> Unit,
    onNavegarVerEvaluaciones: () -> Unit,
    onVolver: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Gestion de Documentos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onNavegarEvaluar() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Realizar Evaluacion")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onNavegarVerEvaluaciones() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver evaluaciones guardadas")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = { onVolver() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Panel")
        }
    }
}