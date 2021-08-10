package edu.bootcamp.censo2021.ApiParaDesarrollarAFuturo.Implementation

import edu.bootcamp.censo2021.ApiParaDesarrollarAFuturo.ApiCenso
import edu.bootcamp.censo2021.Model.Persona
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCensoImp {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("URL_SERVIDOR")
            .build()
    }

    fun getPersonaById(dni: Int): Call<Persona>
    {
        return getRetrofit().create(ApiCenso::class.java).obtenerPersona(dni)
    }

    fun getPersonas(dni: Int): Call<Persona>
    {
        return getRetrofit().create(ApiCenso::class.java).obtenerPersonas()
    }
}