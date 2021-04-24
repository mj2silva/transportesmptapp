package com.manuelsilva.transportesmptapp.models

import java.util.*

class Person {
  var id: Int? = null
  var dni: String? = null
  var names: String? = null
  var firstLastName: String? = null
  var secondLastName: String? = null
  var address: String? = null
  var email: String? = null
  var phoneNumber: String? = null
  var birthDate: Date? = null

  constructor()

  constructor(
    dni: String?,
    names: String?,
    firstLastName: String?,
    secondLastName: String?,
    address: String?,
    email: String?,
    phoneNumber: String?,
    birthDate: Date?
  ) {
    this.dni = dni
    this.names = names
    this.firstLastName = firstLastName
    this.secondLastName = secondLastName
    this.address = address
    this.email = email
    this.phoneNumber = phoneNumber
    this.birthDate = birthDate
  }

  constructor(
    id: Int?,
    dni: String?,
    names: String?,
    firstLastName: String?,
    secondLastName: String?,
    address: String?,
    email: String?,
    phoneNumber: String?,
    birthDate: Date?
  ) {
    this.id = id
    this.dni = dni
    this.names = names
    this.firstLastName = firstLastName
    this.secondLastName = secondLastName
    this.address = address
    this.email = email
    this.phoneNumber = phoneNumber
    this.birthDate = birthDate
  }


}