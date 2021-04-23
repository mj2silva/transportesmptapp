package com.manuelsilva.transportesmptapp.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Alerts {
  companion object {
    fun showUnderConstructionAlert(context: Context) {
      AlertDialog.Builder(context)
        .setTitle("¡En construcción!")
        .setMessage("Esta opción estará disponible pronto")
        .setNeutralButton("Aceptar", null)
        .show()
    }
    fun showToast(context: Context, message: String) {
      Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
  }
}