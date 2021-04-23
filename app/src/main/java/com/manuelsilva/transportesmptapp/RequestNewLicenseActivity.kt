package com.manuelsilva.transportesmptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.manuelsilva.transportesmptapp.utils.Alerts
import com.manuelsilva.transportesmptapp.utils.Validators

class RequestNewLicenseActivity : AppCompatActivity() {
  lateinit var txtDni: EditText
  lateinit var txtNames: EditText
  lateinit var txtFirstLastName: EditText
  lateinit var txtSecondLastName: EditText
  lateinit var txtAddress: EditText
  lateinit var txtEmail: EditText
  lateinit var txtPhoneNumber: EditText

  lateinit var btnNext: Button
  lateinit var btnPrev: Button

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_new_license)

    txtDni = findViewById(R.id.txtDni)
    txtNames = findViewById(R.id.txtNames)
    txtFirstLastName = findViewById(R.id.txtFirstLastName)
    txtSecondLastName = findViewById(R.id.txtSecondLastName)
    txtAddress = findViewById(R.id.txtAddress)
    txtEmail = findViewById(R.id.txtEmail)
    txtPhoneNumber = findViewById(R.id.txtPhoneNumber)

    btnNext = findViewById(R.id.btnRequestNewLicenseNext)
    btnPrev = findViewById(R.id.btnRequestNewLicenseBack)

    btnPrev.setOnClickListener{ goBack() }
    btnNext.setOnClickListener{ goNext() }
  }

  private fun goBack() {
    finish()
  }

  private fun goNext() {
    val errorMessage = validateForm()
    if (errorMessage.isNotEmpty()) Alerts.showToast(AppGeneral.CONTEXT, errorMessage)
    else {
      Alerts.showToast(AppGeneral.CONTEXT, "Datos válidos")
    }
  }

  private fun validateForm(): String {
    var errorMessage = ""

    val dni = txtDni.text.toString()
    val names = txtNames.text.toString()
    val firstLastName = txtFirstLastName.text.toString()
    val secondLastName = txtSecondLastName.text.toString()
    val address = txtAddress.text.toString()
    val email = txtEmail.text.toString()
    val phoneNumber = txtPhoneNumber.text.toString()

    if (!Validators.validateDni(dni)) errorMessage = "Ingrese un dni válido"
    else if (!Validators.validateNames(names)) errorMessage = "Ingrese un nombre válido"
    else if (!Validators.validateNames(firstLastName)) errorMessage = "Ingrese un apellido paterno válido"
    else if (!Validators.validateNames(secondLastName)) errorMessage = "Ingrese un apellido materno válido"
    else if (!Validators.validateAddress(address)) errorMessage = "Ingrese una dirección válida"
    else if (!Validators.validateEmail(email)) errorMessage = "Ingrese un correo válido"
    else if (!Validators.validatePhoneNumber(phoneNumber)) errorMessage = "Ingrese un número de teléfono válido"

    return errorMessage
  }

}