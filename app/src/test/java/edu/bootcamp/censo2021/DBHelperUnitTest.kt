package edu.bootcamp.censo2021

import edu.bootcamp.censo2021.DAO.DBHelper
import edu.bootcamp.censo2021.Model.Persona
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class DBHelperUnitTest {

    lateinit var db: DBHelper

    @Before
    fun initializeComponents() {
        db = DBHelper(RuntimeEnvironment.application,null)
    }

    @Test
    fun agregarPersona(){
        val persona: Persona = Persona(0,"Lopez Paulo Rodrigo","DNI",28789898,"27/08/1979",42,"Masculino","Pje. Quintana 123","+54 381 4567677","Albañil",20000.0f)
        assertEquals(db.agregarPersona(persona),true)
    }

    @Test
    fun eliminarPersona(){
        assertEquals(db.eliminarPersona(2),true)
    }

    @Test
    fun modificarPersona(){
        val personaMod: Persona = Persona(9,"Martino Jose Augusto","DNI",28223454,"27/08/1979",42,"Masculino","Pje. Eliseo Cantón 123","","",4000.0f)
        assertEquals(db.modificarPersona(personaMod),true)
    }

    @Test
    fun obtenerPersonas(){
        assertEquals(db.obtenerTodasLasPersonas(), ArrayList<Persona>())
    }

    @Test
    fun obtenerPersonasPorSexo(){
        assertEquals(db.obtenerTodasLasPersonasPorSexo("Masculino"), ArrayList<Persona>())
        assertEquals(db.obtenerTodasLasPersonasPorSexo("Femenino"), ArrayList<Persona>())
    }

    @Test
    fun obtenerPersonasMayoresDe18yDesocupadas(){
        assertEquals(db.obtenerTodasLasPersonasMayoresDe18yDesocupadas(), ArrayList<Persona>())
    }

    @Test
    fun obtenerPersonasMayoresDe18yDesocupadasPorSexo(){
        assertEquals(db.obtenerTodasLasPersonasMayoresDe18yDesocupadasPorSexo("Masculino"), ArrayList<Persona>())
        assertEquals(db.obtenerTodasLasPersonasMayoresDe18yDesocupadasPorSexo("Femenino"), ArrayList<Persona>())
    }

    @Test
    fun obtenerPersonasPobres(){
        assertEquals(db.obtenerTodasLasPersonasPobres(), ArrayList<Persona>())
    }

    @Test
    fun obtenerPersonasPobresPorSexo(){
        assertEquals(db.obtenerTodasLasPersonasPobresPorSexo("Masculino"), ArrayList<Persona>())
        assertEquals(db.obtenerTodasLasPersonasPobresPorSexo("Femenino"), ArrayList<Persona>())
    }
}