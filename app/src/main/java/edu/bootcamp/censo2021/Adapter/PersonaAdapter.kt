package edu.bootcamp.censo2021.Adapter

import android.content.Intent
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import edu.bootcamp.censo2021.Model.Persona
import edu.bootcamp.censo2021.R
import edu.bootcamp.censo2021.View.Dialog.EliminarPersonaDialog
import edu.bootcamp.censo2021.View.MainActivity
import edu.bootcamp.censo2021.View.ModificarPersonaActivity

class PersonaAdapter(val lista: ArrayList<Persona>, main: MainActivity) :
    RecyclerView.Adapter<PersonaAdapter.ViewHolder>() {

    var mainActivity = main


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_persona,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt_apellido_nombre.text = lista[position].apellidoNombre
        holder.txt_tipo_documento.text = lista[position].tipoDocumento
        holder.txt_numero_documento.text = lista[position].numeroDocumento.toString()
        holder.txt_edad.text = lista[position].edad.toString() + " años"
        holder.txt_fecha_nacimiento.text = lista[position].fechaNacimiento
        holder.txt_sexo.text = lista[position].sexo
        holder.txt_direccion.text = lista[position].direccion
        holder.txt_telefono.text = lista[position].telefono
        holder.txt_ocupacion.text = lista[position].ocupacion
        holder.txt_ingreso_mensual.text = "$ " +lista[position].ingresoMensual.toString()

        holder.card_expandible.visibility = View.GONE // Cardview inicia contraida

        holder.card_persona.setOnClickListener(View.OnClickListener {
            if (holder.card_expandible.visibility == View.GONE){
                // Expandir Cardview
                TransitionManager.beginDelayedTransition(holder.card_persona as ViewGroup, AutoTransition())
                holder.card_expandible.visibility = View.VISIBLE
                holder.img_indicador.setImageResource(R.drawable.ic_arrow_up)
            }
            else
            {
                // Contraer Cardview
                TransitionManager.beginDelayedTransition(holder.card_persona as ViewGroup, AutoTransition())
                holder.card_expandible.visibility = View.GONE
                holder.img_indicador.setImageResource(R.drawable.ic_arrow_down)
            }
        })

        holder.btn_editar.setOnClickListener(View.OnClickListener {
            val intent: Intent = Intent(it.context,ModificarPersonaActivity::class.java)
            intent.putExtra("persona",lista[position])
            mainActivity.startActivityForResult(intent,200)
        })

        holder.btn_eliminar.setOnClickListener(View.OnClickListener {
            val eliminarPersonaDialog = EliminarPersonaDialog(lista[position],mainActivity)
            eliminarPersonaDialog.show(mainActivity.supportFragmentManager, "Confirmación")
        })
    }

    override fun getItemCount(): Int {
        return lista.size
    }
    
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var txt_apellido_nombre: TextView
        var txt_tipo_documento: TextView
        var txt_numero_documento: TextView
        var txt_edad: TextView
        var txt_fecha_nacimiento: TextView
        var txt_sexo: TextView
        var txt_direccion: TextView
        var txt_telefono: TextView
        var txt_ocupacion: TextView
        var txt_ingreso_mensual: TextView
        var btn_editar: Button
        var btn_eliminar: Button
        var card_persona: CardView
        var img_indicador: ImageView
        var card_expandible: LinearLayout

        init {
            txt_apellido_nombre = view.findViewById(R.id.txt_apellido_nombre)
            txt_tipo_documento = view.findViewById(R.id.txt_tipo_documento)
            txt_numero_documento = view.findViewById(R.id.txt_numero_documento)
            txt_edad = view.findViewById(R.id.txt_edad)
            txt_fecha_nacimiento = view.findViewById(R.id.txt_fecha_nacimiento)
            txt_sexo = view.findViewById(R.id.txt_sexo)
            txt_direccion = view.findViewById(R.id.txt_direccion)
            txt_telefono = view.findViewById(R.id.txt_telefono)
            txt_ocupacion = view.findViewById(R.id.txt_ocupacion)
            txt_ingreso_mensual = view.findViewById(R.id.txt_ingreso_mensual)
            btn_editar = view.findViewById(R.id.btn_editar)
            btn_eliminar = view.findViewById(R.id.btn_eliminar)
            card_persona = view.findViewById(R.id.card_persona)
            img_indicador = view.findViewById(R.id.img_indicador)
            card_expandible = view.findViewById(R.id.card_expandible)
        }
    }
}