package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table")
data class TeacherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val teacherId: Long = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val cellphone: String,
    @ColumnInfo
    val password: String,
    @ColumnInfo
    val socialNumber: String,
    @ColumnInfo
    val teamId: Long
)