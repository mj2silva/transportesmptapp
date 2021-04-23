package com.manuelsilva.transportesmptapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
  lateinit var btnRequestService: ImageButton
  lateinit var btnTrackRequestStatus: ImageButton
  lateinit var btnCheckLicenceStatus: ImageButton

  private fun showUnderConstructionAlert(context: Context) {
    AlertDialog.Builder(context)
      .setTitle("¡En construcción!")
      .setMessage("Esta opción estará disponible pronto")
      .setNeutralButton("Aceptar", null)
      .show()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    btnRequestService = findViewById(R.id.btnRequestService)
    btnTrackRequestStatus = findViewById(R.id.btnTrackRequestStatus)
    btnCheckLicenceStatus = findViewById(R.id.btnCheckLicenceStatus)

    btnRequestService.setOnClickListener { openRequestService() }
    btnTrackRequestStatus.setOnClickListener { openTrackRequestStatus() }
    btnCheckLicenceStatus.setOnClickListener { openCheckLicenceStatus() }
  }

  private fun openRequestService() {
    val intent = Intent(AppGeneral.CONTEXT, RequestServiceActivity::class.java)
    this.startActivity(intent)
  }

  private fun openTrackRequestStatus() {
    showUnderConstructionAlert(this)
  }

  private fun openCheckLicenceStatus() {
    showUnderConstructionAlert(this)
  }
}