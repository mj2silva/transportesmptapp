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
import com.manuelsilva.transportesmptapp.models.LicenseType
import com.manuelsilva.transportesmptapp.models.Person
import com.manuelsilva.transportesmptapp.repository.LicenseRequestRepository
import com.manuelsilva.transportesmptapp.repository.PersonRepository
import com.manuelsilva.transportesmptapp.utils.Alerts
import com.manuelsilva.transportesmptapp.utils.Validators
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.concurrent.thread

class RequestNewLicenseActivity : AppCompatActivity() {
  lateinit var txtDni: EditText
  lateinit var txtNames: EditText
  lateinit var txtFirstLastName: EditText
  lateinit var txtSecondLastName: EditText
  lateinit var txtAddress: EditText
  lateinit var txtEmail: EditText
  lateinit var txtPhoneNumber: EditText
  lateinit var txtBirthDate: EditText
  lateinit var datePickerDialog: DatePickerDialog

  lateinit var btnNext: Button
  lateinit var btnPrev: Button

  lateinit var person: Person

  @RequiresApi(Build.VERSION_CODES.N)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_request_new_license)

    person = Person()

    txtDni = findViewById(R.id.txtDni)
    txtNames = findViewById(R.id.txtNames)
    txtFirstLastName = findViewById(R.id.txtFirstLastName)
    txtSecondLastName = findViewById(R.id.txtSecondLastName)
    txtAddress = findViewById(R.id.txtAddress)
    txtEmail = findViewById(R.id.txtEmail)
    txtPhoneNumber = findViewById(R.id.txtPhoneNumber)
    txtBirthDate = findViewById(R.id.txtBirthDate)
    datePickerDialog = Alerts.getPickerDialog(this)


    btnNext = findViewById(R.id.btnRequestNewLicenseNext)
    btnPrev = findViewById(R.id.btnRequestNewLicenseBack)

    btnPrev.setOnClickListener{ goBack() }
    btnNext.setOnClickListener{ goNext() }
    txtDni.setOnFocusChangeListener { _, hasFocus -> if (!hasFocus) checkDni() }
    txtBirthDate.setOnClickListener { datePickerDialog.show() }
    datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
      val date = Date(year - 1900, month, dayOfMonth)
      val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(date)
      person.birthDate = date
      txtBirthDate.setText(formattedDate)
    }
  }

  private fun checkDni() {
    val dni = txtDni.text.toString()
    val isDniValid = Validators.validateDni(dni)
    if (isDniValid) {
      updateFormOnDniChange(dni)
    }
  }

  private fun goBack() {
    finish()
  }

  private fun goNext() {
    val errorMessage = validateForm()
    if (errorMessage.isNotEmpty()) Alerts.showToast(AppGeneral.CONTEXT, errorMessage)
    else {
      person.dni = txtDni.text.toString()
      person.names = txtNames.text.toString()
      person.firstLastName = txtFirstLastName.text.toString()
      person.secondLastName = txtSecondLastName.text.toString()
      person.address = txtAddress.text.toString()
      person.email = txtEmail.text.toString()
      person.phoneNumber = txtPhoneNumber.text.toString()
      if (person.id == null) {
        // Person birthday is already assigned in calendar dialog
        val personId = PersonRepository.createPerson(person)
        AppGeneral.licenseRequest.personId = personId
        AppGeneral.licenseRequest.type = LicenseType.NEW_LICENCE
        AppGeneral.personId = personId
        openNewLicenseMedicTest()
      } else {
        AppGeneral.licenseRequest.personId = person.id
        AppGeneral.licenseRequest.type = LicenseType.NEW_LICENCE
        val licenseRequestTemp = LicenseRequest()
        licenseRequestTemp.type = LicenseType.NEW_LICENCE
        licenseRequestTemp.personId = person.id
        val prevRequestId = LicenseRequestRepository.getRequestId(licenseRequestTemp)
        if (prevRequestId != null) {
          Alerts.showToast(this,"Usted ya tiene una solicitud pendiente, ingrese a la opción realizar seguimiento del menú principal para ver el estado de su solicitud")
          finish()
        } else {
          PersonRepository.updatePerson(person)
          Alerts.showToast(this, person.id.toString())
          AppGeneral.personId = person.id
          openNewLicenseMedicTest()
        }
      }

    }
  }

  private fun openNewLicenseMedicTest() {
    val intent = Intent(AppGeneral.CONTEXT, RequestNewLicenceMedicTestActivity::class.java)
    this.startActivity(intent)
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
    val birthDate =  person.birthDate

    if (!Validators.validateDni(dni)) errorMessage = "Ingrese un dni válido"
    else if (!Validators.validateNames(names)) errorMessage = "Ingrese un nombre válido"
    else if (!Validators.validateNames(firstLastName)) errorMessage = "Ingrese un apellido paterno válido"
    else if (!Validators.validateNames(secondLastName)) errorMessage = "Ingrese un apellido materno válido"
    else if (!Validators.validateAddress(address)) errorMessage = "Ingrese una dirección válida"
    else if (!Validators.validateEmail(email)) errorMessage = "Ingrese un correo válido"
    else if (!Validators.validatePhoneNumber(phoneNumber)) errorMessage = "Ingrese un número de teléfono válido"
    else if (!Validators.validateBirthDate(birthDate)) errorMessage = "La fecha debe ser menor a la de hoy y/o debes ser mayor de edad para solicitar tu licencia"

    return errorMessage
  }

  private fun cleanForm() {
    txtNames.setText("")
    txtFirstLastName.setText("")
    txtSecondLastName.setText("")
    txtAddress.setText("")
    txtEmail.setText("")
    txtPhoneNumber.setText("")
  }

  private fun updateFormPerson(person: Person) {
    cleanForm()
    this.person.id = person.id
    txtNames.setText(person.names)
    txtFirstLastName.setText(person.firstLastName)
    txtSecondLastName.setText(person.secondLastName)
    txtAddress.setText(person.address)
    txtEmail.setText(person.email)
    txtPhoneNumber.setText(person.phoneNumber)
    val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(person.birthDate)
    this.person.birthDate = person.birthDate
    txtBirthDate.setText(formattedDate)
  }

  private fun updateFormOnDniChange(dni: String) {
    val loadingAlert = Alerts.showLoadingAlert(this)
    loadingAlert.show()
    val thread = Thread {
      val person = PersonRepository.getPersonByDni(dni) { _ -> loadingAlert.dismiss() }
      if (person != null) {
        runOnUiThread {
          this.person = person
          updateFormPerson(person)
        }
      } else {
        loadingAlert.dismiss()
        runOnUiThread {
          cleanForm()
        }
      }
    }
    thread.start()
  }

}