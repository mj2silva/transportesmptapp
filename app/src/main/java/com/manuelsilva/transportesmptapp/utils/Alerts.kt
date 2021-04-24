package com.manuelsilva.transportesmptapp.utils

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.manuelsilva.transportesmptapp.R
import java.util.*

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
    fun showLoadingAlert(context: Context): AlertDialog {
      val inflater = LayoutInflater.from(context)
      return AlertDialog.Builder(context)
        .setView(inflater.inflate(R.layout.loading_dialog, null))
        .setCancelable(false)
        .create()
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getPickerDialog(context: Context): DatePickerDialog {
      val calendar = Calendar.getInstance()
      val year = calendar.get(Calendar.YEAR)
      val month = calendar.get(Calendar.MONTH)
      val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
      val dialog = DatePickerDialog(context)
      dialog.updateDate(year, month, dayOfMonth)
      return dialog
    }
  }
}