package edu.bootcamp.censo2021.View.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import edu.bootcamp.censo2021.Adapter.PersonaAdapter
import edu.bootcamp.censo2021.Model.Persona
import edu.bootcamp.censo2021.R
import edu.bootcamp.censo2021.View.MainActivity
import edu.bootcamp.censo2021.ViewModel.PersonaViewModel

class EliminarPersonaDialog(val persona: Persona, main: MainActivity): DialogFragment() {

    var mainActivity = main

    var personaVM: PersonaViewModel = ViewModelProvider(mainActivity).get(PersonaViewModel::class.java)

    var rv_personas: RecyclerView = mainActivity.findViewById(R.id.rv_personas)
    var sp_filtro_personas: Spinner = mainActivity.findViewById(R.id.sp_filtro_personas)
    var txt_cantidad_hombres: TextView = mainActivity.findViewById(R.id.txt_cantidad_hombres)
    var txt_cantidad_mujeres: TextView = mainActivity.findViewById(R.id.txt_cantidad_mujeres)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mainActivity)

        builder.setMessage("Esta persona va a ser eliminada de los registros.\n\n¿Está seguro de continuar?")
            .setTitle("Atención")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Si") { dialog, which ->
                if(personaVM.eliminarPersona(persona.id,mainActivity))
                {
                    Toast.makeText(mainActivity, "Persona eliminada!", Toast.LENGTH_SHORT).show()

                    sp_filtro_personas.setSelection(0)
                    rv_personas.adapter = PersonaAdapter(personaVM.obtenerPersonas(mainActivity),mainActivity)

                    val cantidadHombres: Int = personaVM.obtenerPersonasPorSexo("Masculino",mainActivity).size
                    val cantidadMujeres: Int = personaVM.obtenerPersonasPorSexo("Femenino",mainActivity).size

                    txt_cantidad_hombres.setText(cantidadHombres.toString())
                    txt_cantidad_mujeres.setText(cantidadMujeres.toString())
                }
                else
                    Toast.makeText(mainActivity,"Error al borrar la persona", Toast.LENGTH_SHORT).show()

                dialog.dismiss()
            }
            .setNegativeButton("No") {
                    dialog, which -> dialog.cancel()
            }

        return builder.create()
    }
}