package edu.bootcamp.censo2021.View

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import edu.bootcamp.censo2021.R
import edu.bootcamp.censo2021.View.Dialog.CancelarOperacionDialog
import edu.bootcamp.censo2021.View.Dialog.DatePickerFragment
import edu.bootcamp.censo2021.ViewModel.PersonaViewModel
import java.text.DecimalFormat
import java.text.NumberFormat


class NuevaPersonaActivity : AppCompatActivity() {

    lateinit var fl_form_nuevo: FrameLayout

    lateinit var til_nombre: TextInputLayout
    lateinit var rg_tipo_documento: RadioGroup
    lateinit var til_numero_documento: TextInputLayout
    lateinit var til_fecha_nacimiento: TextInputLayout
    lateinit var til_edad: TextInputLayout
    lateinit var rg_sexo: RadioGroup
    lateinit var til_direccion: TextInputLayout
    lateinit var til_telefono: TextInputLayout
    lateinit var til_ocupacion: TextInputLayout
    lateinit var til_ingreso_mensual: TextInputLayout
    lateinit var btn_guardar_persona: Button
    lateinit var btn_cancelar: Button

    lateinit var personaVM: PersonaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_persona)

        personaVM = ViewModelProvider(this).get(PersonaViewModel::class.java)

        fl_form_nuevo = findViewById(R.id.fl_form_nuevo)
        cargarFormulario()
    }

    private fun cargarFormulario()
    {
        val view = LayoutInflater.from(this).inflate(R.layout.form_persona,null,false)

        til_nombre = view.findViewById(R.id.til_nombre)
        rg_tipo_documento = view.findViewById(R.id.rg_tipo_documento)
        til_numero_documento = view.findViewById(R.id.til_numero_documento)
        til_fecha_nacimiento = view.findViewById(R.id.til_fecha_nacimiento)
        til_edad = view.findViewById(R.id.til_edad)
        rg_sexo = view.findViewById(R.id.rg_sexo)
        til_direccion = view.findViewById(R.id.til_direccion)
        til_telefono = view.findViewById(R.id.til_telefono)
        til_ocupacion = view.findViewById(R.id.til_ocupacion)
        til_ingreso_mensual = view.findViewById(R.id.til_ingreso_mensual)
        btn_guardar_persona = view.findViewById(R.id.btn_guardar_persona)
        btn_cancelar = view.findViewById(R.id.btn_cancelar)

        til_fecha_nacimiento.editText?.setOnClickListener(View.OnClickListener {
            mostrarDatePickerDialog()
        })

        btn_guardar_persona.setOnClickListener(View.OnClickListener {
            guardarPersona()
        })

        btn_cancelar.setOnClickListener(View.OnClickListener {
            cancelarRegistro()
        })

        fl_form_nuevo.addView(view)
    }

    private fun guardarPersona()
    {
        if (!comprobarCamposVacios())
        {
            val apellidoNombre: String = til_nombre.editText?.text.toString()
            val tipoDocumento: String = obtenerTipoDocumentoSeleccionado()
            val numeroDocumento: Int = til_numero_documento.editText?.text.toString().toInt()
            val fechaNacimiento: String = til_fecha_nacimiento.editText?.text.toString()
            val edad: Int = til_edad.editText?.text.toString().toInt()
            val sexo: String = obtenerSexoSeleccionado()
            val direccion: String = til_direccion.editText?.text.toString()
            val telefono: String = til_telefono.editText?.text.toString()
            val ocupacion: String = til_ocupacion.editText?.text.toString()
            val ingresoMensual: Float = til_ingreso_mensual.editText?.text.toString().toFloat()

            if(personaVM.registrarPersona(apellidoNombre,tipoDocumento,numeroDocumento,fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual,this))
            {
                Toast.makeText(this, "Persona guardada!", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            }
            else
                Toast.makeText(this,"Error al guardar la persona", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cancelarRegistro()
    {
        val cancelarOperacionDialog = CancelarOperacionDialog()
        cancelarOperacionDialog.show(supportFragmentManager, "Confirmación")
    }

    private fun mostrarDatePickerDialog()
    {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->

            // Definimos formato de numero para dia y mes
            val nf: NumberFormat = DecimalFormat("00")

            // +1 porque enero es cero
            val selectedDate = nf.format(day) + " / " + nf.format(month + 1) + " / " + year
            til_fecha_nacimiento.editText?.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun comprobarCamposVacios(): Boolean
    {
        til_nombre.isErrorEnabled = false
        til_numero_documento.isErrorEnabled = false
        til_fecha_nacimiento.isErrorEnabled = false
        til_edad.isErrorEnabled = false
        til_ingreso_mensual.isErrorEnabled = false

        var b: Boolean = false

        if (til_ingreso_mensual.editText?.text!!.isEmpty()){
            til_ingreso_mensual.error = "El campo ingreso económico mensual es obligatorio"
            til_ingreso_mensual.isErrorEnabled = true
            til_ingreso_mensual.isFocusableInTouchMode = true
            til_ingreso_mensual.requestFocus()
            b = true
        }
        if (til_edad.editText?.text!!.isEmpty()){
            til_edad.error = "El campo edad es obligatorio"
            til_edad.isErrorEnabled = true
            til_edad.isFocusableInTouchMode = true
            til_edad.requestFocus()
            b = true
        }
        if (til_fecha_nacimiento.editText?.text!!.isEmpty()){
            til_fecha_nacimiento.error = "El campo fecha de nacimiento es obligatorio"
            til_fecha_nacimiento.isErrorEnabled = true
            til_fecha_nacimiento.isFocusableInTouchMode = true
            til_fecha_nacimiento.requestFocus()
            b = true
        }
        if (til_numero_documento.editText?.text!!.isEmpty()){
            til_numero_documento.error = "El campo número de documento es obligatorio"
            til_numero_documento.isErrorEnabled = true
            til_numero_documento.isFocusableInTouchMode = true
            til_numero_documento.requestFocus()
            b = true
        }
        if (til_nombre.editText?.text!!.isEmpty()){
            til_nombre.error = "El campo apellido y nombre es obligatorio"
            til_nombre.isErrorEnabled = true
            til_nombre.isFocusableInTouchMode = true
            til_nombre.requestFocus()
            b = true
        }

        return b
    }

    private fun obtenerTipoDocumentoSeleccionado(): String
    {
        val selected: Int = rg_tipo_documento.checkedRadioButtonId
        val rb_selected: RadioButton = findViewById(selected)
        return rb_selected.text.toString()
    }

    private fun obtenerSexoSeleccionado(): String
    {
        val selected: Int = rg_sexo.checkedRadioButtonId
        val rb_selected: RadioButton = findViewById(selected)
        return rb_selected.text.toString()
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        val cancelarOperacionDialog = CancelarOperacionDialog()
        cancelarOperacionDialog.show(supportFragmentManager, "Confirmación")
    }
}