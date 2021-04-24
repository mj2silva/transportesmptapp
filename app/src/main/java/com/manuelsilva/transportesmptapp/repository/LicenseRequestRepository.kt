package com.manuelsilva.transportesmptapp.repository

import android.util.Log
import com.manuelsilva.transportesmptapp.AppGeneral
import com.manuelsilva.transportesmptapp.models.LicenseRequest
import com.manuelsilva.transportesmptapp.models.LicenseRequestRequirement
import com.manuelsilva.transportesmptapp.models.Person
import com.manuelsilva.transportesmptapp.utils.Alerts
import com.manuelsilva.transportesmptapp.utils.DateConversion
import java.lang.Exception
import java.sql.Date

class LicenseRequestRepository {
  companion object {
    fun createLicenseRequest(licenseRequest: LicenseRequest) {
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = """
          INSERT INTO 
          solicitud(fecha_registro, estado, id_persona, id_tipo)
          VALUES(?, ?, ?, ?)
          """
        val statement = connection.prepareStatement(query)
        statement.setDate(1, DateConversion.convertJavaDateToSqlDate(licenseRequest.registeredDate!!))
        statement.setString(2, licenseRequest.status!!.value)
        statement.setInt(3, licenseRequest.personId!!)
        statement.setInt(4, licenseRequest.type!!.id)
        statement.execute()
        statement.close()
      }
      val licenseId = getRequestId(licenseRequest)
      Alerts.showToast(AppGeneral.CONTEXT, licenseId.toString())
      insertRequestDetail(licenseRequest.licenseDetail, licenseId!!)
    }

    fun insertRequestDetail(licenseDetails: ArrayList<LicenseRequestRequirement>, requestId: Int) {
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = """
          INSERT INTO 
          detalle_solicitud(id_solicitud, id_tipo_requisito, fecha, lugar, pathfile, descripcion)
          VALUES(?, ?, ?, ?, ?, ?)
          """
        licenseDetails.forEach { licenseRequestRequirement ->
          val statement = connection.prepareStatement(query)
          statement.setInt(1, requestId!!)
          statement.setInt(2, licenseRequestRequirement.type!!.id)
          if (licenseRequestRequirement.date != null)  statement.setDate(3, DateConversion.convertJavaDateToSqlDate(licenseRequestRequirement.date!!))
          else statement.setString(3, null)
          statement.setString(5, licenseRequestRequirement.pathFile)
          if (licenseRequestRequirement.place != null) statement.setString(4, licenseRequestRequirement.place)
          else statement.setString(4, null)
          if (licenseRequestRequirement.description != null) statement.setString(6, licenseRequestRequirement.description)
          else statement.setString(6, null)
          statement.execute()
          statement.close()
        }
      }
    }

    fun getRequestId(licenseRequest: LicenseRequest): Int? {
      var id: Int? = null
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = "SELECT id FROM solicitud where id_persona = ? and id_tipo = 1"
        try {
          val statement = connection.prepareStatement(query)
          statement.setInt(1, licenseRequest.personId!!)
          val resultSet = statement.executeQuery()
          resultSet.next()
          id = resultSet.getInt("id")
          resultSet.close()
          statement.close()
        } catch (ex: Exception) {
          Log.e("Error: ", ex.message!!)
        }
      }
      return id
    }
  }
}