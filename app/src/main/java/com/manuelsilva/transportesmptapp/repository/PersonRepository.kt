package com.manuelsilva.transportesmptapp.repository

import android.util.Log
import com.manuelsilva.transportesmptapp.AppGeneral
import com.manuelsilva.transportesmptapp.models.Person
import com.manuelsilva.transportesmptapp.utils.DateConversion
import java.lang.Exception

class PersonRepository {
  companion object {
    fun getPersonByDni(dni: String, callback: (Any) -> Unit): Person? {
      var person: Person? = null
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = "SELECT * FROM persona where dni = ?"
        try {
          val statement = connection.prepareStatement(query)
          statement.setString(1, dni)
          val resultSet = statement.executeQuery()
          resultSet.next()
          person = Person(
              resultSet.getInt("id"),
              resultSet.getString("dni"),
              resultSet.getString("nombres"),
              resultSet.getString("ap_paterno"),
              resultSet.getString("ap_materno"),
              resultSet.getString("direccion"),
              resultSet.getString("email"),
              resultSet.getString("celular"),
              resultSet.getDate("fecha_nac"),
          )
          resultSet.close()
          statement.close()
          callback(person)
        } catch (ex: Exception) {
          Log.e("Error: ", ex.message!!)
        }
      }
      return person
    }

    fun createPerson(person: Person): Int? {
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = """
          INSERT INTO 
          Persona(dni, nombres, ap_paterno, ap_materno, fecha_nac, direccion, email, celular, id_nacionalidad)
          VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)
          """
        val statement = connection.prepareStatement(query)
        statement.setString(1, person.dni.toString().toUpperCase())
        statement.setString(2, person.names.toString().toUpperCase())
        statement.setString(3, person.firstLastName.toString().toUpperCase())
        statement.setString(4, person.secondLastName.toString().toUpperCase())
        statement.setDate(5, DateConversion.convertJavaDateToSqlDate(person.birthDate!!))
        statement.setString(6, person.address.toString().toUpperCase())
        statement.setString(7, person.email.toString().toUpperCase())
        statement.setString(8, person.phoneNumber.toString().toUpperCase())
        statement.setInt(9, 1)
        statement.execute()
        return getPersonId(person.dni!!)
      }
      return null
    }

    fun getPersonId(dni: String): Int? {
      var id: Int? = null
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = "SELECT id FROM persona where dni = ?"
        try {
          val statement = connection.prepareStatement(query)
          statement.setString(1, dni)
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

    fun updatePerson(person: Person) {
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = """
          UPDATE 
          Persona
          SET
            dni = ?,
            nombres = ?,
            ap_paterno = ?,
            ap_materno = ?,
            fecha_nac = ?,
            direccion = ?,
            email = ?,
            celular = ?,
            id_nacionalidad = ?
          WHERE
            id = ?
          """
        val statement = connection.prepareStatement(query)
        statement.setString(1, person.dni.toString().toUpperCase())
        statement.setString(2, person.names.toString().toUpperCase())
        statement.setString(3, person.firstLastName.toString().toUpperCase())
        statement.setString(4, person.secondLastName.toString().toUpperCase())
        statement.setDate(5, DateConversion.convertJavaDateToSqlDate(person.birthDate!!))
        statement.setString(6, person.address.toString().toUpperCase())
        statement.setString(7, person.email.toString().toUpperCase())
        statement.setString(8, person.phoneNumber.toString().toUpperCase())
        statement.setInt(9, 1)
        statement.setInt(10, person.id!!)
        statement.execute()
      }
    }

    fun getPersons(): ArrayList<Person> {
      val personsList: ArrayList<Person> = ArrayList()
      val connection = AppGeneral.DB.connect()
      if (connection != null) {
        val query = "SELECT * FROM persona"
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(query)

        while (resultSet.next()) {
          personsList.add(
            Person(
              resultSet.getString("dni"),
              resultSet.getString("nombres"),
              resultSet.getString("ap_paterno"),
              resultSet.getString("ap_materno"),
              resultSet.getString("direccion"),
              resultSet.getString("email"),
              resultSet.getString("celular"),
              resultSet.getDate("fecha_nac"),
            )
          )
        }
        resultSet.close()
        statement.close()
      }
      return personsList
    }
  }
}