package com.example.tcc.data.db.converters

import androidx.room.TypeConverter
import java.util.Date

object Converters {

    @TypeConverter
    fun toDate(dateLong: Long) = Date().apply {
        time = dateLong
    }

    @TypeConverter
    fun fromDate(date: Date) = date.time

}