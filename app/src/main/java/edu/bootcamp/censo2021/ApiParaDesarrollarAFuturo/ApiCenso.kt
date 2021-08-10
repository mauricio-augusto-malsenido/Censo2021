package edu.bootcamp.censo2021.ApiParaDesarrollarAFuturo

import edu.bootcamp.censo2021.Model.Persona
import retrofit2.Call
import retrofit2.http.*


interface ApiCenso {
    @GET("/persona/{dni}")
    fun obtenerPersona(@Path("dni") dni: Int): Call<Persona>

    @GET("/persona")
    fun obtenerPersonas(): Call<Persona>
}