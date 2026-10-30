package com.example.rubricapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestionDocumentosScreen(
    onNavegarEvaluacion: () -> Unit,
    onNavegarLista: () -> Unit,
    onVolver: () -> Unit
) {
    val verdeInstitucional = Color(0xFF1B5E20)
    val verdeClaro = Color(0xFFE8F5E9)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gestión de Evaluaciones", color = Color.White) },
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
                    Text(
                        text = "Panel de Control",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = verdeInstitucional
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onNavegarEvaluacion,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Realizar Evaluación", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onNavegarLista,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = verdeInstitucional),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Ver Evaluaciones Guardadas", color = Color.White, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedButton(
                        onClick = onVolver,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = verdeInstitucional)
                    ) {
                        Text("Volver al Panel", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}