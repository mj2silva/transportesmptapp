package com.manuelsilva.transportesmptapp.repository

import com.manuelsilva.transportesmptapp.AppGeneral
import com.manuelsilva.transportesmptapp.models.Person

class PersonRepository {
  companion object {
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
              resultSet.getString("celular")
            )
          )
        }
      }
      return personsList
    }
  }
}