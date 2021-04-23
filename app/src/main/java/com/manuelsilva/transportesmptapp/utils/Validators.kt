package com.manuelsilva.transportesmptapp.utils

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
      if (!name.matches("^[a-zA-Z]+$".toRegex())) isValid = false
      return isValid
    }

    fun validateAddress(address: String): Boolean {
      var isValid = true
      if (address.isEmpty()) isValid = false
      if (!address.matches("^[#.0-9a-zA-Z\\s,-]+\$".toRegex())) isValid = false
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
  }
}