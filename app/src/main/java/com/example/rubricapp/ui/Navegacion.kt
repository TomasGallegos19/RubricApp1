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
            PanelDocenteScreen(
                viewModel = authViewModel,
                onNavegarEvaluaciones = {
                    navController.navigate("gestion")
                },
                onNavegarEditarPerfil = {
                    navController.navigate("editar_perfil")
                },
                onCerrarSesion = {
                    navController.navigate("login") {
                        popUpTo("vista_principal") { inclusive = true }
                    }
                }
            )
        }

        composable(route = "gestion") {
            GestionDocumentosScreen(
                onNavegarEvaluacion = {
                    navController.navigate(route = "evaluacion")
                },
                onNavegarLista = {
                    navController.navigate(route = "lista_evaluaciones")
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = "editar_perfil") {
            EditarPerfilScreen(
                viewModel = authViewModel,
                onActualizacionExitosa = {
                    navController.popBackStack()
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "evaluacion") {
            EvaluacionScreen(
                viewModel = authViewModel,
                onGuardadoExitoso = {
                    navController.popBackStack()
                },
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = "lista_evaluaciones") {
            ListaEvaluacionesScreen(
                viewModel = authViewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}