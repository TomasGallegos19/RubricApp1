package com.example.rubricapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import kotlin.jvm.JvmSuppressWildcards

@Dao
@JvmSuppressWildcards
interface EvaluacionDao {
    @Query("SELECT * FROM usuarios WHERE correo = :correo AND contrasena = :contrasena")
    suspend fun login(correo: String, contrasena: String): Usuario?

    @Insert
    suspend fun registrarUsuario(usuario: Usuario): Long

    @Update
    suspend fun actualizarUsuario(usuario: Usuario): Int // <-- Agrega esta función para el perfil

    @Insert
    suspend fun registrarAlumno(alumno: Alumno): Long

    @Insert
    suspend fun registrarEvaluacion(evaluacion: Evaluacion): Long

    @Query("SELECT * FROM evaluaciones")
    suspend fun obtenerTodasEvaluaciones(): List<Evaluacion>

    @Delete
    suspend fun eliminarEvaluacion(evaluacion: Evaluacion): Int
}