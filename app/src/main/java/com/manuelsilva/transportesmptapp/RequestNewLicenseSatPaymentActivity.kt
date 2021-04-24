package com.manuelsilva.transportesmptapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.manuelsilva.transportesmptapp.models.LicenseRequestRequirement
import com.manuelsilva.transportesmptapp.models.LicenseRequirementType
import com.manuelsilva.transportesmptapp.utils.Alerts
import com.manuelsilva.transportesmptapp.utils.Validators
import java.text.SimpleDateFormat
import java.util.*

class RequestNewLicenseSatPaymentActivity : AppCompatActivity() {
  lateinit var txtSatPaymentDate: EditText
  lateinit var txtReceiptNumber: EditText
  lateinit var btnAttachReceipt: Button
  lateinit var btnNext: Button
  lateinit var btnPrev: Button

  lateinit var satPayment: LicenseRequestRequirement

  lateinit var datePickerDialog: DatePickerDialog

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_new_license_sat_payment)
    datePickerDialog = Alerts.getPickerDialog(this)

    satPayment = LicenseRequestRequirement()
    satPayment.type = LicenseRequirementType.PAG

    txtSatPaymentDate = findViewById(R.id.txtNewLicenseReceiptPaymentDate)
    txtReceiptNumber = findViewById(R.id.txtNewLicenseReceiptNumber)
    btnAttachReceipt = findViewById(R.id.btnAttachReceipt)
    btnNext = findViewById(R.id.btnStaPaymentNext)
    btnPrev = findViewById(R.id.btnStaPaymentPrev)

    txtSatPaymentDate.setOnClickListener {  datePickerDialog.show() }

    datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
      val date = Date(year - 1900, month, dayOfMonth)
      val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)
      satPayment.date = date
      txtSatPaymentDate.setText(formattedDate)
    }

    btnAttachReceipt.setOnClickListener { openFileChooser() }
    btnNext.setOnClickListener { goNext() }
    btnPrev.setOnClickListener { goPrev() }
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
      satPayment.pathFile = data?.data?.path
    }
  }

  private fun goPrev() {
    finish()
  }

  private fun goNext() {
    val validationError = validateForm()
    if (validationError.isEmpty()) {
      Alerts.showToast(this, "Campos validados")
      satPayment.description = txtReceiptNumber.text.toString()
      AppGeneral.licenseRequest.licenseDetail.add(satPayment)
      openNewLicenseOtherData()
    } else {
      Alerts.showToast(this, validationError)
    }
  }

  private fun openNewLicenseOtherData() {
    val intent = Intent(AppGeneral.CONTEXT, RequestNewLicenseOtherDataActivity::class.java)
    this.startActivity(intent)
  }

  private fun validateForm(): String {
    var validationError = ""
    if (!Validators.validateMedicalTestDate(satPayment.date)) validationError = "El pago debe haberse realizado en los últimos 30 días"
    else if (!Validators.validateReceiptNumber(txtReceiptNumber.text.toString())) validationError = "El número de recibo ingresado no es válido"
    else if (satPayment.pathFile == null || satPayment.pathFile!!.isEmpty()) validationError = "Debe adjuntar el recibo"
    return validationError
  }
}