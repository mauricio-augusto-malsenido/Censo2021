package edu.bootcamp.censo2021.View.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class CancelarOperacionDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("¿Desea cancelar la operación?")
            .setTitle("Confirmación")
            .setIcon(android.R.drawable.ic_dialog_info)
            .setPositiveButton("Si") { dialog, which ->
                requireActivity().setResult(Activity.RESULT_CANCELED)
                requireActivity().finish()
                dialog.dismiss()
            }
            .setNegativeButton("No") {
                    dialog, which -> dialog.cancel()
            }

        return builder.create()
    }
}