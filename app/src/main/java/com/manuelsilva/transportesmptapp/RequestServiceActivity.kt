package com.manuelsilva.transportesmptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.manuelsilva.transportesmptapp.utils.Alerts

class RequestServiceActivity : AppCompatActivity() {
  lateinit var btnRequestNewLicence: ImageButton
  lateinit var btnRequestLicenceDuplicate: ImageButton
  lateinit var btnRequestLicenceRevalidation: ImageButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_service)

    btnRequestNewLicence = findViewById(R.id.btnRequestNewLicence)
    btnRequestLicenceDuplicate = findViewById(R.id.btnRequestLicenceDuplicate)
    btnRequestLicenceRevalidation = findViewById(R.id.btnRequestLicenceRevalidation)

    btnRequestNewLicence.setOnClickListener { openRequestNewLicence() }
    btnRequestLicenceDuplicate.setOnClickListener { openRequestLicenceDuplicate() }
    btnRequestLicenceRevalidation.setOnClickListener { openRequestLicenceRevalidation() }
  }

  private fun openRequestNewLicence() {
    val intent = Intent(AppGeneral.CONTEXT, RequestNewLicenseActivity::class.java)
    this.startActivity(intent)
  }
  private fun openRequestLicenceDuplicate() {
    Alerts.showUnderConstructionAlert(this)
  }
  private fun openRequestLicenceRevalidation() {
    Alerts.showUnderConstructionAlert(this)
  }
}