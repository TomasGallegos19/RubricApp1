package com.example.rubricapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanelDocenteScreen(
    viewModel: AuthViewModel,
    onNavegarEvaluaciones: () -> Unit,
    onNavegarEditarPerfil: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val verdeInstitucional = Color(0xFF1B5E20)
    val verdeClaro = Color(0xFFE8F5E9)
    val scrollState = rememberScrollState()

    // Obtenemos los datos del usuario logueado de forma segura
    val usuarioActual = viewModel.usuarioActual
    val rol = usuarioActual?.rol ?: "Docente"
    val nombreUsuario = usuarioActual?.nombre?.ifBlank { null } ?: usuarioActual?.correo ?: "Usuario"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Panel Institucional", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = verdeInstitucional)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(verdeClaro, Color.White)))
                .padding(paddingValues)
                .padding(24.dp),
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
                    // Título dinámico del panel
                    Text(
                        text = "Panel de $rol",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = verdeInstitucional
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Saludo personalizado estético
                    Text(
                        text = "Bienvenido, $rol: \n$nombreUsuario",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    // Botón de Gestión de Evaluaciones
                    Button(
                        onClick = onNavegarEvaluaciones,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Gestión de Evaluaciones", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Botón de Actualizar Datos
                    Button(
                        onClick = onNavegarEditarPerfil,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Actualizar mis Datos", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botón de Cerrar Sesión (con borde o tono neutro formal)
                    OutlinedButton(
                        onClick = onCerrarSesion,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                    ) {
                        Text("Cerrar Sesión", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}