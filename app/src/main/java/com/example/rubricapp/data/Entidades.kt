package com.example.rubricapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val correo: String,
    val contrasena: String,
    val rol: String
)

@Entity(tableName = "alumnos")
data class Alumno(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombreFicticio: String,
    val rutFicticio: String
)

@Entity(tableName = "evaluaciones")
data class Evaluacion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val alumnoId: Int,
    val evaluadorId: Int,
    val calificacion: Double,
    val comentarios: String
)