package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "student_table")
data class StudentEntity(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val registrationNumber: String,
    @ColumnInfo
    val birthdate: Date,
    @ColumnInfo
    val password: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    //adicionei uma coluna de notas
    val note: Double,
    @ColumnInfo
    val classId: Long
)