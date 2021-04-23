package com.manuelsilva.transportesmptapp.repository

import android.os.StrictMode
import android.util.Log
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager

class DbConnection {
  lateinit var connection: Connection
  lateinit var host: String
  lateinit var port: String
  lateinit var username: String
  lateinit var password: String
  lateinit var databaseName: String

  fun connect(): Connection? {
    host = "tranportes-mpt.database.windows.net"
    port = "1433"
    databaseName = "licencias_mpt"
    username = "esern@tranportes-mpt"
    password = "Qwerty147"

    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)

    var connection: Connection? = null
    var connectionString: String? = null

    try {
      Class.forName("net.sourceforge.jtds.jdbc.Driver")
      connectionString = "jdbc:jtds:sqlserver://$host:$port;databaseName=$databaseName;user=$username;password=$password;"
      connection = DriverManager.getConnection(connectionString)
    } catch (ex: Exception) {
      Log.e("Error: ", ex.stackTraceToString()!!)
    }
    return connection
  }
}