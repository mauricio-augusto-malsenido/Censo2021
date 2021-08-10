package edu.bootcamp.censo2021.View

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.bootcamp.censo2021.Adapter.PersonaAdapter
import edu.bootcamp.censo2021.Model.Persona
import edu.bootcamp.censo2021.R
import edu.bootcamp.censo2021.View.Dialog.SalirAplicacionDialog
import edu.bootcamp.censo2021.ViewModel.PersonaViewModel

class MainActivity : AppCompatActivity() {

    lateinit var sp_filtro_personas: Spinner
    lateinit var txt_cantidad_hombres: TextView
    lateinit var txt_cantidad_mujeres: TextView
    lateinit var rv_personas: RecyclerView
    lateinit var fab_nueva_persona: FloatingActionButton

    lateinit var personaVM: PersonaViewModel

    var cantidadHombres: Int = 0
    var cantidadMujeres: Int = 0

    val filtros = arrayOf("Todas","Mayores de 18 años desocupadas","Por debajo de la línea de pobreza")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarComponentes()
        inicializarSpFiltroPersonas()

        personaVM = ViewModelProvider(this).get(PersonaViewModel::class.java)

        rv_personas.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        rv_personas.adapter = PersonaAdapter(personaVM.obtenerPersonas(this),this)

        cantidadHombres = personaVM.obtenerPersonasPorSexo("Masculino",this).size
        cantidadMujeres = personaVM.obtenerPersonasPorSexo("Femenino",this).size

        txt_cantidad_hombres.setText(cantidadHombres.toString())
        txt_cantidad_mujeres.setText(cantidadMujeres.toString())

        sp_filtro_personas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filtrar()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        fab_nueva_persona.setOnClickListener(View.OnClickListener {
            val intentNuevaMascota: Intent = Intent(this,NuevaPersonaActivity::class.java)
            startActivityForResult(intentNuevaMascota,100)
        })
    }

    private fun inicializarComponentes()
    {
        sp_filtro_personas = findViewById(R.id.sp_filtro_personas)
        txt_cantidad_hombres = findViewById(R.id.txt_cantidad_hombres)
        txt_cantidad_mujeres = findViewById(R.id.txt_cantidad_mujeres)
        rv_personas = findViewById(R.id.rv_personas)
        fab_nueva_persona = findViewById(R.id.fab_nueva_persona)
    }

    private fun inicializarSpFiltroPersonas()
    {
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,filtros)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_filtro_personas.adapter = adapter
    }

    private fun filtrar()
    {
        val filtro: String = sp_filtro_personas.selectedItem.toString()
        var personas: ArrayList<Persona> = ArrayList()

        if (filtro.contentEquals("Todas")){
            personas = personaVM.obtenerPersonas(this)
            cantidadHombres = personaVM.obtenerPersonasPorSexo("Masculino",this).size
            cantidadMujeres = personaVM.obtenerPersonasPorSexo("Femenino",this).size
        }
        if (filtro.contentEquals("Mayores de 18 años desocupadas")){
            personas = personaVM.obtenerPersonasMayoresDe18yDesocupadas(this)
            cantidadHombres = personaVM.obtenerPersonasMayoresDe18yDesocupadasPorSexo("Masculino",this).size
            cantidadMujeres = personaVM.obtenerPersonasMayoresDe18yDesocupadasPorSexo("Femenino",this).size
        }
        if (filtro.contentEquals("Por debajo de la línea de pobreza")){
            personas = personaVM.obtenerPersonasPobres(this)
            cantidadHombres = personaVM.obtenerPersonasPobresPorSexo("Masculino",this).size
            cantidadMujeres = personaVM.obtenerPersonasPobresPorSexo("Femenino",this).size
        }

        rv_personas.adapter = PersonaAdapter(personas,this)

        txt_cantidad_hombres.setText(cantidadHombres.toString())
        txt_cantidad_mujeres.setText(cantidadMujeres.toString())
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    // Inflamos la vista del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return true
    }

    // Evento al presionar el botón salir del toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.opSalir)
        {
            val salirAplicacionDialog = SalirAplicacionDialog()
            salirAplicacionDialog.show(supportFragmentManager, "Confirmación")
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            sp_filtro_personas.setSelection(0)
            rv_personas.adapter = PersonaAdapter(personaVM.obtenerPersonas(this),this)

            cantidadHombres = personaVM.obtenerPersonasPorSexo("Masculino",this).size
            cantidadMujeres = personaVM.obtenerPersonasPorSexo("Femenino",this).size

            txt_cantidad_hombres.setText(cantidadHombres.toString())
            txt_cantidad_mujeres.setText(cantidadMujeres.toString())
        }
    }
}