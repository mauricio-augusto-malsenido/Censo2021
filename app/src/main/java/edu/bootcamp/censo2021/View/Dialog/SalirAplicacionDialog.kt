package edu.bootcamp.censo2021.View.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SalirAplicacionDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("¿Desea salir de la aplicación?")
            .setTitle("Confirmación")
            .setIcon(android.R.drawable.ic_dialog_info)
            .setPositiveButton("Si") { dialog, which ->
                dialog.dismiss()
                requireActivity().finishAndRemoveTask(); // Finalizar la actividad y matar al proceso
            }
            .setNegativeButton("No") {
                    dialog, which -> dialog.cancel()
            }

        return builder.create()
    }
}