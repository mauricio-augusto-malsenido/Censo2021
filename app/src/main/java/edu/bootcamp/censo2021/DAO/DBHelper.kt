package edu.bootcamp.censo2021.DAO

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.bootcamp.censo2021.Model.Persona

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION) {

    companion object{
        private val DATABASE_NAME = "Censo.db"
        private val DATABASE_VERSION = 1

        val TABLE_PERSONA = "Persona"
        val COLUMN_ID = "id"
        val COLUMN_APELLIDO_NOMBRE = "apellido_nombre"
        val COLUMN_TIPO_DOCUMENTO = "tipo_documento"
        val COLUMN_NUMERO_DOCUMENTO = "numero_documento"
        val COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento"
        val COLUMN_EDAD = "edad"
        val COLUMN_SEXO = "sexo"
        val COLUMN_DIRECCION = "direccion"
        val COLUMN_TELEFONO = "telefono"
        val COLUMN_OCUPACION = "ocupacion"
        val COLUMN_INGRESO_MENSUAL = "ingreso_economico_mensual"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePersona = ("CREATE TABLE $TABLE_PERSONA (" +
                "$COLUMN_ID INTEGER PRIMARY KEY, " +
                "$COLUMN_APELLIDO_NOMBRE TEXT NOT NULL, " +
                "$COLUMN_TIPO_DOCUMENTO TEXT NOT NULL, " +
                "$COLUMN_NUMERO_DOCUMENTO INTEGER NOT NULL, " +
                "$COLUMN_FECHA_NACIMIENTO TEXT NOT NULL, " +
                "$COLUMN_EDAD TEXT NOT NULL, " +
                "$COLUMN_SEXO TEXT NOT NULL, " +
                "$COLUMN_DIRECCION TEXT, " +
                "$COLUMN_TELEFONO TEXT, " +
                "$COLUMN_OCUPACION TEXT, " +
                "$COLUMN_INGRESO_MENSUAL REAL)")

        db?.execSQL(createTablePersona)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TALBE IF EXISTS " + TABLE_PERSONA)
        onCreate(db)
    }

    fun agregarPersona(persona: Persona): Boolean
    {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_APELLIDO_NOMBRE,persona.apellidoNombre)
            values.put(COLUMN_TIPO_DOCUMENTO,persona.tipoDocumento)
            values.put(COLUMN_NUMERO_DOCUMENTO,persona.numeroDocumento)
            values.put(COLUMN_FECHA_NACIMIENTO,persona.fechaNacimiento)
            values.put(COLUMN_EDAD,persona.edad)
            values.put(COLUMN_SEXO,persona.sexo)
            values.put(COLUMN_DIRECCION,persona.direccion)
            values.put(COLUMN_TELEFONO,persona.telefono)
            values.put(COLUMN_OCUPACION,persona.ocupacion)
            values.put(COLUMN_INGRESO_MENSUAL,persona.ingresoMensual)

            db.insert(TABLE_PERSONA,null,values)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun eliminarPersona(id: Int): Boolean
    {
        try {
            val db = this.writableDatabase

            val whereclause = "$COLUMN_ID=?"
            val whereargs = arrayOf(id.toString())

            db.delete(TABLE_PERSONA,whereclause,whereargs)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE: ",e.message.toString())
            return false
        }
    }

    fun modificarPersona(persona: Persona): Boolean
    {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_APELLIDO_NOMBRE,persona.apellidoNombre)
            values.put(COLUMN_TIPO_DOCUMENTO,persona.tipoDocumento)
            values.put(COLUMN_NUMERO_DOCUMENTO,persona.numeroDocumento)
            values.put(COLUMN_FECHA_NACIMIENTO,persona.fechaNacimiento)
            values.put(COLUMN_EDAD,persona.edad)
            values.put(COLUMN_SEXO,persona.sexo)
            values.put(COLUMN_DIRECCION,persona.direccion)
            values.put(COLUMN_TELEFONO,persona.telefono)
            values.put(COLUMN_OCUPACION,persona.ocupacion)
            values.put(COLUMN_INGRESO_MENSUAL,persona.ingresoMensual)

            val whereclause = "$COLUMN_ID=?"
            val whereargs = arrayOf(persona.id.toString())

            db.update(TABLE_PERSONA,values,whereclause,whereargs)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun obtenerTodasLasPersonas(): ArrayList<Persona>
    {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList()

        val query = "SELECT * FROM $TABLE_PERSONA ORDER BY $COLUMN_APELLIDO_NOMBRE ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val apellidoNombre = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_NOMBRE))
                val tipoDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_DOCUMENTO))
                val numeroDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_NUMERO_DOCUMENTO))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresoMensual = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO_MENSUAL))

                val persona: Persona = Persona(id,apellidoNombre,tipoDocumento,numeroDocumento.toInt(),fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual.toFloat())
                listaPersonas.add(persona)
            }while (cursor.moveToNext())
        }

        return listaPersonas
    }

    fun obtenerTodasLasPersonasPorSexo(sexo: String): ArrayList<Persona>
    {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList()

        val query = "SELECT * FROM $TABLE_PERSONA WHERE $COLUMN_SEXO LIKE '$sexo' ORDER BY $COLUMN_APELLIDO_NOMBRE ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val apellidoNombre = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_NOMBRE))
                val tipoDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_DOCUMENTO))
                val numeroDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_NUMERO_DOCUMENTO))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresoMensual = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO_MENSUAL))

                val persona: Persona = Persona(id,apellidoNombre,tipoDocumento,numeroDocumento.toInt(),fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual.toFloat())
                listaPersonas.add(persona)
            }while (cursor.moveToNext())
        }

        return listaPersonas
    }

    fun obtenerTodasLasPersonasMayoresDe18yDesocupadas(): ArrayList<Persona>
    {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList()

        val query = "SELECT * FROM $TABLE_PERSONA WHERE $COLUMN_EDAD >= 18 AND $COLUMN_OCUPACION = '' ORDER BY $COLUMN_APELLIDO_NOMBRE ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val apellidoNombre = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_NOMBRE))
                val tipoDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_DOCUMENTO))
                val numeroDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_NUMERO_DOCUMENTO))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresoMensual = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO_MENSUAL))

                val persona: Persona = Persona(id,apellidoNombre,tipoDocumento,numeroDocumento.toInt(),fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual.toFloat())
                listaPersonas.add(persona)
            }while (cursor.moveToNext())
        }

        return listaPersonas
    }

    fun obtenerTodasLasPersonasMayoresDe18yDesocupadasPorSexo(sexo: String): ArrayList<Persona>
    {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList()

        val query = "SELECT * FROM $TABLE_PERSONA WHERE $COLUMN_EDAD >= 18 AND $COLUMN_OCUPACION = '' AND $COLUMN_SEXO LIKE '$sexo' ORDER BY $COLUMN_APELLIDO_NOMBRE ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val apellidoNombre = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_NOMBRE))
                val tipoDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_DOCUMENTO))
                val numeroDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_NUMERO_DOCUMENTO))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresoMensual = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO_MENSUAL))

                val persona: Persona = Persona(id,apellidoNombre,tipoDocumento,numeroDocumento.toInt(),fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual.toFloat())
                listaPersonas.add(persona)
            }while (cursor.moveToNext())
        }

        return listaPersonas
    }

    fun obtenerTodasLasPersonasPobres(): ArrayList<Persona>
    {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList()

        val query = "SELECT * FROM $TABLE_PERSONA WHERE $COLUMN_INGRESO_MENSUAL < 5000 ORDER BY $COLUMN_APELLIDO_NOMBRE ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val apellidoNombre = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_NOMBRE))
                val tipoDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_DOCUMENTO))
                val numeroDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_NUMERO_DOCUMENTO))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresoMensual = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO_MENSUAL))

                val persona: Persona = Persona(id,apellidoNombre,tipoDocumento,numeroDocumento.toInt(),fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual.toFloat())
                listaPersonas.add(persona)
            }while (cursor.moveToNext())
        }

        return listaPersonas
    }

    fun obtenerTodasLasPersonasPobresPorSexo(sexo: String): ArrayList<Persona>
    {
        val db = this.readableDatabase
        val listaPersonas: ArrayList<Persona> = ArrayList()

        val query = "SELECT * FROM $TABLE_PERSONA WHERE $COLUMN_INGRESO_MENSUAL < 5000 AND $COLUMN_SEXO LIKE '$sexo' ORDER BY $COLUMN_APELLIDO_NOMBRE ASC"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst()){
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val apellidoNombre = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO_NOMBRE))
                val tipoDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_DOCUMENTO))
                val numeroDocumento = cursor.getString(cursor.getColumnIndex(COLUMN_NUMERO_DOCUMENTO))
                val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
                val edad = cursor.getInt(cursor.getColumnIndex(COLUMN_EDAD))
                val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
                val direccion = cursor.getString(cursor.getColumnIndex(COLUMN_DIRECCION))
                val telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO))
                val ocupacion = cursor.getString(cursor.getColumnIndex(COLUMN_OCUPACION))
                val ingresoMensual = cursor.getInt(cursor.getColumnIndex(COLUMN_INGRESO_MENSUAL))

                val persona: Persona = Persona(id,apellidoNombre,tipoDocumento,numeroDocumento.toInt(),fechaNacimiento,edad,sexo,direccion,telefono,ocupacion,ingresoMensual.toFloat())
                listaPersonas.add(persona)
            }while (cursor.moveToNext())
        }

        return listaPersonas
    }
}