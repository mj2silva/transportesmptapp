package com.manuelsilva.transportesmptapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.manuelsilva.transportesmptapp.models.LicenseRequestRequirement
import com.manuelsilva.transportesmptapp.models.LicenseRequirementType
import com.manuelsilva.transportesmptapp.models.LicenseStatus
import com.manuelsilva.transportesmptapp.models.LicenseType
import com.manuelsilva.transportesmptapp.repository.LicenseRequestRepository
import com.manuelsilva.transportesmptapp.utils.Alerts
import java.util.*


class RequestNewLicenseOtherDataActivity : AppCompatActivity() {
  lateinit var infractionsRecord: LicenseRequestRequirement
  lateinit var personPicture: LicenseRequestRequirement

  lateinit var btnAttachInfractionsRecord: Button
  lateinit var btnAttachPersonPicture: Button

  lateinit var btnSendRequest: Button
  lateinit var btnPrev: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_new_license_other_data2)

    infractionsRecord = LicenseRequestRequirement()
    infractionsRecord.type = LicenseRequirementType.RDI
    personPicture = LicenseRequestRequirement()
    personPicture.type = LicenseRequirementType.FTC

    btnAttachInfractionsRecord = findViewById(R.id.btnRequestNewLicenceInfractionsRecord)
    btnAttachPersonPicture = findViewById(R.id.btnAttachPersonPicture)
    btnPrev = findViewById(R.id.btnOtherDataPrev)
    btnSendRequest = findViewById(R.id.btnRequestNewLicenceEnd)

    btnAttachInfractionsRecord.setOnClickListener { openFileChooserInfractionsRecord() }
    btnAttachPersonPicture.setOnClickListener { openFileChooserPersonPicture() }

    btnPrev.setOnClickListener { goPrev() }
    btnSendRequest.setOnClickListener { sendRequest() }
  }

  private fun sendRequest() {
    val errorMessage = validateForm()
    if (!errorMessage.isEmpty()) {
      Alerts.showToast(this, errorMessage)
    } else {
      Alerts.showToast(this, "Solicitud creada")
      AppGeneral.licenseRequest.registeredDate = Date()
      AppGeneral.licenseRequest.personId = AppGeneral.personId
      AppGeneral.licenseRequest.licenseDetail.add(infractionsRecord)
      AppGeneral.licenseRequest.licenseDetail.add(personPicture)
      AppGeneral.licenseRequest.type = LicenseType.NEW_LICENCE
      AppGeneral.licenseRequest.status = LicenseStatus.PENDING
      LicenseRequestRepository.createLicenseRequest(AppGeneral.licenseRequest)
      val intent = Intent(this, HomeActivity::class.java)
      intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
      startActivity(intent)
    }
  }

  private fun goPrev() {
    finish()
  }

  private fun validateForm(): String {
    var validationError = ""
    if (infractionsRecord.pathFile == null || infractionsRecord.pathFile!!.isEmpty()) validationError = "Debe adjuntar el récord de infracciones"
    else if (personPicture.pathFile == null || personPicture.pathFile!!.isEmpty()) validationError = "Debe adjuntar la foto de la persona"
    return validationError
  }

  private fun openFileChooserInfractionsRecord() {
    val intent = Intent()
      .setType("*/*")
      .setAction(Intent.ACTION_GET_CONTENT)

    startActivityForResult(Intent.createChooser(intent, "Selecciona un archivo"), 111)
  }
  private fun openFileChooserPersonPicture() {
    val intent = Intent()
      .setType("*/*")
      .setAction(Intent.ACTION_GET_CONTENT)

    startActivityForResult(Intent.createChooser(intent, "Selecciona un archivo"), 112)
  }


  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 111 && resultCode == RESULT_OK) {
      infractionsRecord.pathFile = data?.data?.path
    }
    if (requestCode == 112 && resultCode == RESULT_OK) {
      personPicture.pathFile = data?.data?.path
    }
  }
}