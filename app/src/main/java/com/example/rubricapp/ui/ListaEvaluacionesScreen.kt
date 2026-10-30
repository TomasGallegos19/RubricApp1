package com.example.rubricapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rubricapp.data.Evaluacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaEvaluacionesScreen(
    viewModel: AuthViewModel,
    onVolver: () -> Unit
) {
    val verdeInstitucional = Color(0xFF1B5E20)
    val verdeClaro = Color(0xFFE8F5E9)

    // Cargamos las evaluaciones al iniciar la pantalla
    LaunchedEffect(Unit) {
        viewModel.cargarEvaluaciones()
    }

    val listaEvaluaciones = viewModel.listaEvaluaciones

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de Evaluaciones", color = Color.White) },
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
        ) {
            if (listaEvaluaciones.isEmpty()) {
                // Mensaje estético si no hay evaluaciones registradas
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay evaluaciones registradas aún.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                // Lista estetica con LazyColumn
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listaEvaluaciones) { evaluacion ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Estudiante ID: ${evaluacion.alumnoId}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = verdeInstitucional
                                    )

                                    // Insignia de Calificación
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = verdeClaro
                                    ) {
                                        Text(
                                            text = "Nota: ${evaluacion.calificacion}",
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = verdeInstitucional
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                                Divider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp)
                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "Aspectos a mejorar / Comentarios:",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = if (evaluacion.comentarios.isNotBlank()) evaluacion.comentarios else "Sin comentarios adicionales.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.DarkGray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}