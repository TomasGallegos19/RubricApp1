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

    fun registrar(
        nombre: String,
        correo: String,
        contrasena: String,
        rol: String,
        tituloAcademico: String,
        profesion: String,
        telefono: String,
        onExito: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val nuevoUsuario = Usuario(
                    nombre = nombre,
                    correo = correo,
                    contrasena = contrasena,
                    rol = rol,
                    tituloAcademico = if (rol == "Docente") tituloAcademico else null,
                    profesion = if (rol == "Instructor") profesion else null,
                    telefono = if (rol == "Instructor") telefono else null
                )

                dao.registrarUsuario(nuevoUsuario)
                onExito()
            } catch (e: Exception) {
                onError("Error al registrar: ${e.localizedMessage}")
            }
        }
    }

    fun actualizarDatosUsuario(
        nombre: String,
        correo: String,
        tituloAcademico: String,
        profesion: String,
        telefono: String,
        onExito: () -> Unit
    ) {
        val usuarioActualizado = usuarioActual?.copy(
            nombre = nombre,
            correo = correo,
            tituloAcademico = tituloAcademico,
            profesion = profesion,
            telefono = telefono
        )

        if (usuarioActualizado != null) {
            viewModelScope.launch {
                try {
                    db.evaluacionDao().actualizarUsuario(usuarioActualizado)
                    usuarioActual = usuarioActualizado
                    onExito()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    } // <-- Llave correcta que cierra actualizarDatosUsuario

    fun iniciarSesion(
        correo: String,
        contrasena: String,
        onExito: () -> Unit,
        onError: () -> Unit
    ) {
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
        val calificacion = calificacionStr.toDoubleOrNull()
        if (calificacion == null || calificacion < 1.0 || calificacion > 7.0) {
            onError("Por favor, ingrese una calificación válida (1.0 a 7.0).")
            return
        }

        viewModelScope.launch {
            try {
                val nuevoAlumno = Alumno(nombre = nombreAlumno)
                val alumnoId = db.evaluacionDao().registrarAlumno(nuevoAlumno)

                val nuevaEvaluacion = Evaluacion(
                    alumnoId = alumnoId.toInt(),
                    calificacion = calificacion,
                    comentarios = comentarios
                )
                db.evaluacionDao().registrarEvaluacion(nuevaEvaluacion)
                onExito()
            } catch (e: Exception) {
                onError("Error al guardar: ${e.localizedMessage}")
            }
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