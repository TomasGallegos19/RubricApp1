package com.example.rubricapp.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EvaluacionScreen(
    viewModel: AuthViewModel,
    onGuardarExitoso: () -> Unit
) {
    var nombreAlumno by remember { mutableStateOf("") }
    var calificacion by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }
    var archivoUri by remember { mutableStateOf<Uri?>(null) }
    var mensajeError by remember { mutableStateOf("") }

    // Selector nativo de archivos e imagenes
    val selectorArchivos = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        archivoUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Evaluar Alumno",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombreAlumno,
            onValueChange = { nombreAlumno = it },
            label = { Text("Nombre del alumno") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = calificacion,
            onValueChange = { calificacion = it },
            label = { Text("Calificacion (Ej: 5.50)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = comentarios,
            onValueChange = { comentarios = it },
            label = { Text("Retroalimentacion manual") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Boton para abrir archivos o galeria
        OutlinedButton(
            onClick = { selectorArchivos.launch("*/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (archivoUri == null) "Adjuntar archivo o imagen" else "Archivo adjuntado correctamente")
        }

        if (mensajeError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nombreAlumno.isNotEmpty() && calificacion.isNotEmpty() && comentarios.isNotEmpty()) {
                    viewModel.guardarEvaluacion(
                        nombreAlumno = nombreAlumno,
                        calificacionStr = calificacion,
                        comentarios = comentarios,
                        onExito = { onGuardarExitoso() },
                        onError = { error -> mensajeError = error }
                    )
                } else {
                    mensajeError = "Completa todos los campos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Evaluacion")
        }
    }
}