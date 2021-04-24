package com.manuelsilva.transportesmptapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.manuelsilva.transportesmptapp.models.LicenseRequest
import com.manuelsilva.transportesmptapp.utils.Alerts

class HomeActivity : AppCompatActivity() {
  lateinit var btnRequestService: ImageButton
  lateinit var btnTrackRequestStatus: ImageButton
  lateinit var btnCheckLicenceStatus: ImageButton

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    btnRequestService = findViewById(R.id.btnRequestService)
    btnTrackRequestStatus = findViewById(R.id.btnTrackRequestStatus)
    btnCheckLicenceStatus = findViewById(R.id.btnCheckLicenceStatus)

    btnRequestService.setOnClickListener { openRequestService() }
    btnTrackRequestStatus.setOnClickListener { openTrackRequestStatus() }
    btnCheckLicenceStatus.setOnClickListener { openCheckLicenceStatus() }

    AppGeneral.licenseRequest = LicenseRequest()
  }

  private fun openRequestService() {
    val intent = Intent(AppGeneral.CONTEXT, RequestServiceActivity::class.java)
    this.startActivity(intent)
  }

  private fun openTrackRequestStatus() {
    Alerts.showUnderConstructionAlert(this)
  }

  private fun openCheckLicenceStatus() {
    Alerts.showUnderConstructionAlert(this)
  }
}