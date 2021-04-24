package com.manuelsilva.transportesmptapp.models

import java.util.*
import kotlin.collections.ArrayList

class LicenseRequest {
  var id: Int? = null
  var registeredDate: Date? = null
  var status: LicenseStatus? = null
  var personId: Int? = null
  var type: LicenseType? = null
  var licenseDetail: ArrayList<LicenseRequestRequirement> = ArrayList()

  constructor()

  constructor(
    id: Int?,
    registeredDate: Date?,
    status: LicenseStatus?,
    personId: Int?,
    type: LicenseType?,
    licenseDetail: ArrayList<LicenseRequestRequirement>
  ) {
    this.id = id
    this.registeredDate = registeredDate
    this.status = status
    this.personId = personId
    this.type = type
    this.licenseDetail = licenseDetail
  }

  constructor(
    registeredDate: Date?,
    status: LicenseStatus?,
    personId: Int?,
    type: LicenseType?,
    licenseDetail: ArrayList<LicenseRequestRequirement>
  ) {
    this.id = id
    this.registeredDate = registeredDate
    this.status = status
    this.personId = personId
    this.type = type
    this.licenseDetail = licenseDetail
  }


}