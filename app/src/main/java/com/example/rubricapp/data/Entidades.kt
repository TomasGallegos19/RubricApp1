package com.example.rubricapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val rol: String,
    val tituloAcademico: String? = null, // Nuevo
    val profesion: String? = null,       // Nuevo
    val telefono: String? = null         // Nuevo
)

@Entity(tableName = "alumnos")
data class Alumno(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String
)

@Entity(tableName = "evaluaciones")
data class Evaluacion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombreAlumno: String,
    val calificacion: Double,
    val comentarios: String
)