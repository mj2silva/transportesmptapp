package com.manuelsilva.transportesmptapp.utils

import java.sql.Date

class DateConversion {
  companion object {
    fun convertJavaDateToSqlDate(uDate: java.util.Date): Date {
      return Date(uDate.time)
    }
  }
}