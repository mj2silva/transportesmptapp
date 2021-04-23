package com.manuelsilva.transportesmptapp.models

class Person {
  var id: String? = null
  var dni: String? = null
  var names: String? = null
  var firstLastName: String? = null
  var secondLastName: String? = null
  var address: String? = null
  var email: String? = null
  var phoneNumber: String? = null
  var birthDate: String? = null

  constructor(
    dni: String?,
    names: String?,
    firstLastName: String?,
    secondLastName: String?,
    address: String?,
    email: String?,
    phoneNumber: String?
  ) {
    this.dni = dni
    this.names = names
    this.firstLastName = firstLastName
    this.secondLastName = secondLastName
    this.address = address
    this.email = email
    this.phoneNumber = phoneNumber
  }

  constructor(
    id: String?,
    dni: String?,
    names: String?,
    firstLastName: String?,
    secondLastName: String?,
    address: String?,
    email: String?,
    phoneNumber: String?
  ) {
    this.id = id
    this.dni = dni
    this.names = names
    this.firstLastName = firstLastName
    this.secondLastName = secondLastName
    this.address = address
    this.email = email
    this.phoneNumber = phoneNumber
  }


}