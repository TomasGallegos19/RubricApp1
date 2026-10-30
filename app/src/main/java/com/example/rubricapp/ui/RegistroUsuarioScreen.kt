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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroUsuarioScreen(
    viewModel: AuthViewModel,
    onRegistroExitoso: () -> Unit
) {
    // Definición de colores institucionales
    val verdeInstitucional = Color(0xFF1B5E20)
    val verdeClaro = Color(0xFFE8F5E9)
    val verdeOscuro = Color(0xFF0D3B12)

    var rol by remember { mutableStateOf("Docente") }

    // Campos comunes
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    // Específico Docente
    var tituloAcademico by remember { mutableStateOf("") }

    // Específicos Instructor
    var profesion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    var mensajeError by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro Institucional", color = Color.White) },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seleccione su Perfil",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = verdeInstitucional
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Botones de selección de rol estéticos
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { rol = "Docente" },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (rol == "Docente") verdeInstitucional else Color.LightGray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Docente", color = if (rol == "Docente") Color.White else Color.DarkGray, fontWeight = FontWeight.Bold)
                        }

                        Button(
                            onClick = { rol = "Instructor" },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (rol == "Instructor") verdeInstitucional else Color.LightGray
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Instructor", color = if (rol == "Instructor") Color.White else Color.DarkGray, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre Completo") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = verdeInstitucional,
                            focusedLabelColor = verdeInstitucional
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo Electrónico") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = verdeInstitucional,
                            focusedLabelColor = verdeInstitucional
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = contrasena,
                        onValueChange = { contrasena = it },
                        label = { Text("Contraseña") },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = verdeInstitucional,
                            focusedLabelColor = verdeInstitucional
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Campos dinámicos limpios según el rol
                    if (rol == "Docente") {
                        OutlinedTextField(
                            value = tituloAcademico,
                            onValueChange = { tituloAcademico = it },
                            label = { Text("Título / Escalafón Académico") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = verdeInstitucional,
                                focusedLabelColor = verdeInstitucional
                            )
                        )
                    } else {
                        OutlinedTextField(
                            value = profesion,
                            onValueChange = { profesion = it },
                            label = { Text("Profesión") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = verdeInstitucional,
                                focusedLabelColor = verdeInstitucional
                            )
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        OutlinedTextField(
                            value = telefono,
                            onValueChange = { telefono = it },
                            label = { Text("Número de Teléfono") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = verdeInstitucional,
                                focusedLabelColor = verdeInstitucional
                            )
                        )
                    }

                    if (mensajeError.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = mensajeError, color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val camposComunesValidos = nombre.isNotBlank() && correo.isNotBlank() && contrasena.isNotBlank()
                            val camposRolValidos = if (rol == "Docente") {
                                tituloAcademico.isNotBlank()
                            } else {
                                profesion.isNotBlank() && telefono.isNotBlank()
                            }

                            if (camposComunesValidos && camposRolValidos) {
                                viewModel.registrar(
                                    nombre = nombre,
                                    correo = correo,
                                    contrasena = contrasena,
                                    rol = rol,
                                    tituloAcademico = if (rol == "Docente") tituloAcademico else "",
                                    profesion = if (rol == "Instructor") profesion else "",
                                    telefono = if (rol == "Instructor") telefono else "",
                                    onExito = { onRegistroExitoso() },
                                    onError = { mensajeError = it }
                                )
                            } else {
                                mensajeError = "Por favor, complete todos los campos requeridos."
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Registrarse como $rol", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}