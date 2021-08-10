package edu.bootcamp.censo2021.Model

import java.io.Serializable

data class Persona (var id: Int = 0,
                    val apellidoNombre: String,
                    val tipoDocumento: String,
                    val numeroDocumento: Int,
                    val fechaNacimiento: String,
                    val edad: Int,
                    val sexo: String,
                    val direccion: String,
                    val telefono: String,
                    val ocupacion: String,
                    val ingresoMensual: Float) : Serializable