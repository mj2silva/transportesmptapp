package com.manuelsilva.transportesmptapp

import android.app.Application
import android.content.Context
import com.manuelsilva.transportesmptapp.repository.DbConnection
import java.sql.Connection

class AppGeneral : Application() {
  companion object{
    lateinit var CONTEXT: Context
    lateinit var DB: DbConnection
    /* lateinit var DB:FirebaseFirestore
    lateinit var STORAGE:FirebaseStorage
    lateinit var AUTH:FirebaseAuth
    var TABLENAME="productos"
    var TABLECLIENTENAME="clientes"
    var TABLEPEDIDONAME="pedido"
    lateinit var carrito : ArrayList<ItemCarrito>
    */
  }

  override fun onCreate() {
    super.onCreate()
    CONTEXT = applicationContext
    DB = DbConnection()
    /* DB=FirebaseFirestore.getInstance()
    STORAGE = FirebaseStorage.getInstance()
    carrito = ArrayList()
    AUTH = FirebaseAuth.getInstance()
    */
  }
}