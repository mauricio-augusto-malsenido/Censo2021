package edu.bootcamp.censo2021.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.bootcamp.censo2021.DAO.DBHelper
import edu.bootcamp.censo2021.Model.Persona

class PersonaViewModel: ViewModel() {
    fun registrarPersona(apellidoNombre: String, tipoDocumento: String, numeroDocumento: Int, fechaNacimiento: String, edad: Int, sexo: String, direccion: String, telefono: String, ocupacion: String, ingresoMensual: Float, context: Context): Boolean
    {
        val db: DBHelper = DBHelper(context,null)

        val persona: Persona = Persona(0,apellidoNombre,tipoDocumento,numeroDocumento,fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual)
        return db.agregarPersona(persona)
    }

    fun eliminarPersona(idPersona: Int, context: Context): Boolean
    {
        val db:DBHelper = DBHelper(context,null)
        return db.eliminarPersona(idPersona)
    }

    fun modificarPersona(idPersona: Int, apellidoNombre: String, tipoDocumento: String, numeroDocumento: Int, fechaNacimiento: String, edad: Int, sexo: String, direccion: String, telefono: String, ocupacion: String, ingresoMensual: Float, context: Context): Boolean
    {
        val db:DBHelper = DBHelper(context,null)

        val persona: Persona = Persona(idPersona,apellidoNombre,tipoDocumento,numeroDocumento,fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual)
        return db.modificarPersona(persona)
    }

    fun obtenerPersonas(context: Context): ArrayList<Persona>
    {
        val db:DBHelper = DBHelper(context,null)
        return db.obtenerTodasLasPersonas()
    }

    fun obtenerPersonasPorSexo(sexo: String, context: Context): ArrayList<Persona>
    {
        val db:DBHelper = DBHelper(context,null)
        return db.obtenerTodasLasPersonasPorSexo(sexo)
    }

    fun obtenerPersonasMayoresDe18yDesocupadas(context: Context): ArrayList<Persona>
    {
        val db:DBHelper = DBHelper(context,null)
        return db.obtenerTodasLasPersonasMayoresDe18yDesocupadas()
    }

    fun obtenerPersonasMayoresDe18yDesocupadasPorSexo(sexo: String, context: Context): ArrayList<Persona>
    {
        val db:DBHelper = DBHelper(context,null)
        return db.obtenerTodasLasPersonasMayoresDe18yDesocupadasPorSexo(sexo)
    }

    fun obtenerPersonasPobres(context: Context): ArrayList<Persona>
    {
        val db:DBHelper = DBHelper(context,null)
        return db.obtenerTodasLasPersonasPobres()
    }

    fun obtenerPersonasPobresPorSexo(sexo: String, context: Context): ArrayList<Persona>
    {
        val db:DBHelper = DBHelper(context,null)
        return db.obtenerTodasLasPersonasPobresPorSexo(sexo)
    }
}