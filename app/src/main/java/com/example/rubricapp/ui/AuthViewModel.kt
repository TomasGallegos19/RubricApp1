package com.example.rubricapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.rubricapp.data.AppDatabase
import com.example.rubricapp.data.Usuario
import com.example.rubricapp.data.Alumno
import com.example.rubricapp.data.Evaluacion
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.obtenerBaseDatos(application)
    private val dao = db.evaluacionDao()

    var listaEvaluaciones by mutableStateOf<List<Evaluacion>>(emptyList())
        private set

    // Variable para almacenar el usuario que ha iniciado sesión
    var usuarioActual by mutableStateOf<Usuario?>(null)
        private set

    fun registrar(correo: String, contrasena: String, rol: String, onExito: () -> Unit) {
        viewModelScope.launch {
            val usuario = Usuario(correo = correo, contrasena = contrasena, rol = rol)
            dao.registrarUsuario(usuario)
            onExito()
        }
    }

    fun iniciarSesion(correo: String, contrasena: String, onExito: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            val usuario = dao.login(correo, contrasena)
            if (usuario != null) {
                usuarioActual = usuario
                onExito()
            } else {
                onError()
            }
        }
    }

    fun guardarEvaluacion(
        nombreAlumno: String,
        calificacionStr: String,
        comentarios: String,
        onExito: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val nota = calificacionStr.toDoubleOrNull()
            if (nota == null || nota < 1.0 || nota > 7.0) {
                onError("La calificacion debe ser un numero entre 1.00 y 7.00")
                return@launch
            }

            val alumno = Alumno(nombreFicticio = nombreAlumno, rutFicticio = "12.345.678-9")
            val alumnoId = dao.registrarAlumno(alumno).toInt()

            val evaluacion = Evaluacion(
                alumnoId = alumnoId,
                evaluadorId = 1,
                calificacion = nota,
                comentarios = comentarios
            )
            dao.registrarEvaluacion(evaluacion)
            onExito()
        }
    }

    fun cargarEvaluaciones() {
        viewModelScope.launch {
            listaEvaluaciones = dao.obtenerTodasEvaluaciones()
        }
    }

    fun eliminarEvaluacion(evaluacion: Evaluacion) {
        viewModelScope.launch {
            dao.eliminarEvaluacion(evaluacion)
            cargarEvaluaciones()
        }
    }
}