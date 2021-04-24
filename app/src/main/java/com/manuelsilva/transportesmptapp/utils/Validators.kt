package com.manuelsilva.transportesmptapp.utils

import java.util.*

class Validators {
  companion object {
    fun validateDni(dni: String): Boolean {
      var isValid = true
      if (dni.length != 8) isValid = false
      if (!dni.matches("^[0-9]+$".toRegex())) isValid = false
      return isValid
    }

    fun validateNames(name: String): Boolean {
      var isValid = true
      if (name.isEmpty()) isValid = false
      if (!name.matches("^[a-zA-Z ]+$".toRegex())) isValid = false
      return isValid
    }

    fun validateAddress(address: String): Boolean {
      var isValid = true
      if (address.isEmpty()) isValid = false
      if (!address.matches("^[#.0-9a-zA-ZÑÁÉÍÓÚñáéíóú\\s,-]+\$".toRegex())) isValid = false
      return isValid
    }

    fun validateEmail(email: String): Boolean {
      var isValid = true
      if (email.isEmpty()) isValid = false
      if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$".toRegex())) isValid = false
      return isValid
    }

    fun validatePhoneNumber(phoneNumber: String): Boolean {
      var isValid = true
      if (phoneNumber.isEmpty()) isValid = false
      if (!phoneNumber.matches("^^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*\$".toRegex())) isValid = false
      return isValid
    }

    fun validateBirthDate(birthDate: Date?): Boolean {
      var isValid = true
      if (birthDate == null) isValid = false
      else {
        val birthDateCalendar = Calendar.getInstance()
        val todayCalendar = Calendar.getInstance()

        birthDateCalendar.time = birthDate

        val birthYear = birthDateCalendar.get(Calendar.YEAR)
        val birthMonth = birthDateCalendar.get(Calendar.MONTH)
        val birthDay = birthDateCalendar.get(Calendar.DAY_OF_MONTH)

        val todayYear = todayCalendar.get(Calendar.YEAR)
        val todayMonth = todayCalendar.get(Calendar.MONTH)
        val todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH)

        if (todayYear < birthYear) isValid = false
        else {
          var age = todayYear - birthYear
          if (todayMonth < birthMonth) age--
          else if (todayMonth == birthMonth) {
            if (todayDay < birthDay) age--
          }
          if (age < 18) isValid = false
        }
      }
      return  isValid
    }

    fun validatePaymentDate(paymentDate: Date?): Boolean {
      var isValid = true
      if (paymentDate == null) isValid = false
      else {
        val paymentDateCalendar = Calendar.getInstance()
        val todayCalendar = Calendar.getInstance()

        paymentDateCalendar.time = paymentDate

        val paymentYear = paymentDateCalendar.get(Calendar.YEAR)
        val paymentMonth = paymentDateCalendar.get(Calendar.MONTH)
        val paymentDay = paymentDateCalendar.get(Calendar.DAY_OF_MONTH)

        val todayYear = todayCalendar.get(Calendar.YEAR)
        val todayMonth = todayCalendar.get(Calendar.MONTH)
        val todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH)

        if (todayYear - paymentYear == 1) {
          if (todayMonth != 1) isValid = false
          else if (paymentMonth != 12) isValid = false
        } else if (todayYear == paymentYear) {
          if (todayMonth - paymentMonth > 1) isValid = false
          else if (todayMonth == paymentMonth) {
            if (todayDay < paymentDay) isValid = false
          }
        } else {
          isValid = false
        }
      }
      return  isValid
    }

    fun validateMedicalTestDate(medicalTestDate: Date?): Boolean {
      var isValid = true
      if (medicalTestDate == null) isValid = false
      else {
        val paymentDateCalendar = Calendar.getInstance()
        val todayCalendar = Calendar.getInstance()

        paymentDateCalendar.time = medicalTestDate

        val paymentYear = paymentDateCalendar.get(Calendar.YEAR)
        val paymentMonth = paymentDateCalendar.get(Calendar.MONTH)
        val paymentDay = paymentDateCalendar.get(Calendar.DAY_OF_MONTH)

        val todayYear = todayCalendar.get(Calendar.YEAR)
        val todayMonth = todayCalendar.get(Calendar.MONTH)
        val todayDay = todayCalendar.get(Calendar.DAY_OF_MONTH)

        if (todayYear - paymentYear == 1) {
          if (todayMonth != 1) isValid = false
          else if (paymentMonth != 12) isValid = false
        } else if (todayYear == paymentYear) {
          if (todayMonth - paymentMonth > 1) isValid = false
          else if (todayMonth == paymentMonth) {
            if (todayDay < paymentDay) isValid = false
          }
        } else {
          isValid = false
        }
      }
      return  isValid
    }

    fun validateMedicalCenterName(name: String): Boolean {
      var isValid = true
      if (name.isEmpty()) isValid = false
      if (!name.matches("^[a-zA-Z0-9 ]+$".toRegex())) isValid = false
      return isValid
    }

    fun validateReceiptNumber(number: String): Boolean {
      var isValid = true
      if (number.length != 6) isValid = false
      if (!number.matches("^[a-zA-Z0-9]+$".toRegex())) isValid = false
      return isValid
    }
  }
}