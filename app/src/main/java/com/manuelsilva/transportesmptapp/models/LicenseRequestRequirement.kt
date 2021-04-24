package com.manuelsilva.transportesmptapp.models

import java.io.FileDescriptor
import java.util.*

class LicenseRequestRequirement {
  var requestId: Int? = null
  var date: Date? = null
  var place: String? = null
  var pathFile: String? = null
  var type: LicenseRequirementType? = null
  var description: String? = null

  constructor()

  constructor(
    requestId: Int?,
    date: Date?,
    place: String?,
    pathFile: String?,
    type: LicenseRequirementType?,
    description: String?
  ) {
    this.requestId = requestId
    this.date = date
    this.place = place
    this.pathFile = pathFile
    this.type = type
    this.description = description
  }

  constructor(date: Date?, place: String?, pathFile: String?, type: LicenseRequirementType?, description: String?) {
    this.date = date
    this.place = place
    this.pathFile = pathFile
    this.type = type
    this.description = description
  }


}