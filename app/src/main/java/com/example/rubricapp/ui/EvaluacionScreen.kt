package com.example.rubricapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EvaluacionScreen(
    viewModel: AuthViewModel,
    onGuardadoExitoso: () -> Unit,
    onVolver: () -> Unit
) {
    val verdeInstitucional = Color(0xFF1B5E20)
    val verdeClaro = Color(0xFFE8F5E9)
    val scrollState = rememberScrollState()

    var nombreAlumno by remember { mutableStateOf("") }
    var calificacion by remember { mutableStateOf("") }
    var aspectosAMejorar by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Evaluación Académica (1.0 - 7.0)", color = Color.White) },
                navigationIcon = {
                    TextButton(onClick = onVolver) {
                        Text("← Volver", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = verdeInstitucional)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(verdeClaro, Color.White)))
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Registro de Calificación",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = verdeInstitucional
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = nombreAlumno,
                        onValueChange = { nombreAlumno = it },
                        label = { Text("Nombre del Alumno") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = verdeInstitucional,
                            focusedLabelColor = verdeInstitucional
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = calificacion,
                        onValueChange = { calificacion = it },
                        label = { Text("Ingrese Calificación (ej: 5.5)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = verdeInstitucional,
                            focusedLabelColor = verdeInstitucional
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = aspectosAMejorar,
                        onValueChange = { aspectosAMejorar = it },
                        label = { Text("Aspectos a mejorar / Comentarios") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp),
                        maxLines = 4,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = verdeInstitucional,
                            focusedLabelColor = verdeInstitucional
                        )
                    )

                    if (mensajeError.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (nombreAlumno.isNotBlank() && calificacion.isNotBlank()) {
                                viewModel.guardarEvaluacion(
                                    nombreAlumno = nombreAlumno,
                                    calificacionStr = calificacion,
                                    comentarios = aspectosAMejorar,
                                    onExito = { onGuardadoExitoso() },
                                    onError = { mensajeError = it }
                                )
                            } else {
                                mensajeError = "Complete el nombre y la calificación."
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Guardar y Volver", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}