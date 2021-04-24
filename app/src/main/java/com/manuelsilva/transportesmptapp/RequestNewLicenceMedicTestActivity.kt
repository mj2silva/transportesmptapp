package com.manuelsilva.transportesmptapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.manuelsilva.transportesmptapp.models.LicenseRequest
import com.manuelsilva.transportesmptapp.models.LicenseRequestRequirement
import com.manuelsilva.transportesmptapp.models.LicenseRequirementType
import com.manuelsilva.transportesmptapp.utils.Alerts
import com.manuelsilva.transportesmptapp.utils.Validators
import java.text.SimpleDateFormat
import java.util.*

class RequestNewLicenceMedicTestActivity : AppCompatActivity() {
  lateinit var txtMedicalTestDate: EditText
  lateinit var txtMedicalCenterName: EditText
  lateinit var btnAttachMedicalTestPdf: Button
  lateinit var btnMedicalTestNext: Button
  lateinit var btnMedicalTestPrev: Button
  lateinit var datePickerDialog: DatePickerDialog
  lateinit var licenseRequest: LicenseRequest

  lateinit var medicalTest :LicenseRequestRequirement

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_new_licence_medic_test)
    datePickerDialog = Alerts.getPickerDialog(this)

    medicalTest = LicenseRequestRequirement()
    medicalTest.type = LicenseRequirementType.EXM
    AppGeneral.licenseRequest = LicenseRequest()
    licenseRequest = AppGeneral.licenseRequest

    txtMedicalTestDate = findViewById(R.id.txtNewLicenseMedicExamDate)
    txtMedicalCenterName = findViewById(R.id.txtMedicalCenterName)
    btnAttachMedicalTestPdf = findViewById(R.id.btnAttachMedicalTestPdf)
    btnMedicalTestNext = findViewById(R.id.btnMedicalTextNext)
    btnMedicalTestPrev = findViewById(R.id.btnMedicalTextPrev)

    txtMedicalTestDate.setOnClickListener {  datePickerDialog.show() }

    datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
      val date = Date(year - 1900, month, dayOfMonth)
      val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)
      medicalTest.date = date
      txtMedicalTestDate.setText(formattedDate)
    }

    btnAttachMedicalTestPdf.setOnClickListener { openFileChooser() }
    btnMedicalTestNext.setOnClickListener { goNext() }
    btnMedicalTestPrev.setOnClickListener { goPrev() }
  }

  private fun openFileChooser() {
    val intent = Intent()
      .setType("*/*")
      .setAction(Intent.ACTION_GET_CONTENT)

    startActivityForResult(Intent.createChooser(intent, "Selecciona un archivo"), 111)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == 111 && resultCode == RESULT_OK) {
      medicalTest.pathFile = data?.data?.path
    }
  }

  private fun goPrev() {
    finish()
  }

  private fun goNext() {
    val validationError = validateForm()
    if (validationError.isEmpty()) {
      Alerts.showToast(this, "Campos validados")
      medicalTest.place = txtMedicalCenterName.text.toString()
      AppGeneral.licenseRequest.licenseDetail.add(medicalTest)
      openSatPaymentData()
    } else {
      Alerts.showToast(this, validationError)
    }
  }

  private fun openSatPaymentData() {
    val intent = Intent(AppGeneral.CONTEXT, RequestNewLicenseSatPaymentActivity::class.java)
    this.startActivity(intent)
  }

  private fun validateForm(): String {
    var validationError = ""
    if (!Validators.validateMedicalTestDate(medicalTest.date)) validationError = "El exámen médico debe haberse realizado en los últimos 30 días"
    else if (!Validators.validateMedicalCenterName(txtMedicalCenterName.text.toString())) validationError = "Ingrese un centro Médico Válido"
    else if (medicalTest.pathFile == null || medicalTest.pathFile!!.isEmpty()) validationError = "Debe seleccionar un archivo adjunto"
    return validationError
  }
}