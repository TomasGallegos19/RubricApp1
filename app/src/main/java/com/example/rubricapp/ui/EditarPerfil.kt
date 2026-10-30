package com.example.rubricapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(
    viewModel: AuthViewModel,
    onActualizacionExitosa: () -> Unit,
    onVolver: () -> Unit
) {
    val verdeInstitucional = Color(0xFF1B5E20)
    val verdeClaro = Color(0xFFE8F5E9)
    val scrollState = rememberScrollState()

    // Obtenemos el usuario actual del ViewModel de forma segura
    val usuarioActual = viewModel.usuarioActual

    // Definimos las variables de estado usando los datos actuales (o vacíos si es nulo)
    val rol = usuarioActual?.rol ?: "Docente"
    var nombre by remember { mutableStateOf(usuarioActual?.nombre ?: "") }
    var correo by remember { mutableStateOf(usuarioActual?.correo ?: "") }
    var tituloAcademico by remember { mutableStateOf(usuarioActual?.tituloAcademico ?: "") }
    var profesion by remember { mutableStateOf(usuarioActual?.profesion ?: "") }
    var telefono by remember { mutableStateOf(usuarioActual?.telefono ?: "") }

    var mensajeError by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Actualizar Mis Datos", color = Color.White) },
                navigationIcon = {
                    TextButton(onClick = onVolver) {
                        Text("← Volver", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = verdeInstitucional)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(verdeClaro, Color.White)))
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
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
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Perfil de $rol",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = verdeInstitucional
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre Completo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo Electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Campos dinámicos según el rol
                    if (rol == "Docente") {
                        OutlinedTextField(
                            value = tituloAcademico,
                            onValueChange = { tituloAcademico = it },
                            label = { Text("Título / Escalafón Académico") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    } else {
                        OutlinedTextField(
                            value = profesion,
                            onValueChange = { profesion = it },
                            label = { Text("Profesión") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = telefono,
                            onValueChange = { telefono = it },
                            label = { Text("Número de Teléfono") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                    }

                    if (mensajeError.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val validoComun = nombre.isNotBlank() && correo.isNotBlank()
                            val validoRol = if (rol == "Docente") tituloAcademico.isNotBlank() else (profesion.isNotBlank() && telefono.isNotBlank())

                            if (validoComun && validoRol) {
                                viewModel.actualizarDatosUsuario(
                                    nombre = nombre,
                                    correo = correo,
                                    tituloAcademico = tituloAcademico,
                                    profesion = profesion,
                                    telefono = telefono,
                                    onExito = { onActualizacionExitosa() }
                                )
                            } else {
                                mensajeError = "Por favor, complete todos los campos obligatorios."
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Guardar Cambios", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}