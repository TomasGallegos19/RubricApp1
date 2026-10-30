package com.example.rubricapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavegacionApp(authViewModel: AuthViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                onLoginExitoso = {
                    navController.navigate("vista_principal") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavegarRegistro = {
                    navController.navigate("registro")
                }
            )
        }

        composable("registro") {
            RegistroUsuarioScreen(
                viewModel = authViewModel,
                onRegistroExitoso = {
                    navController.popBackStack()
                }
            )
        }

        composable("vista_principal") {
            VistaPrincipalScreen(
                viewModel = authViewModel,
                onNavegarGestion = {
                    navController.navigate("gestion")
                },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo("vista_principal") { inclusive = true }
                    }
                }
            )
        }

        composable("gestion") {
            GestionDocumentosScreen(
                onNavegarEvaluar = {
                    navController.navigate("evaluar")
                },
                onNavegarVerEvaluaciones = {
                    navController.navigate("lista_evaluaciones")
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable("evaluar") {
            EvaluacionScreen(
                viewModel = authViewModel,
                onGuardarExitoso = {
                    navController.popBackStack()
                }
            )
        }

        composable("lista_evaluaciones") {
            ListaEvaluacionesScreen(
                viewModel = authViewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}