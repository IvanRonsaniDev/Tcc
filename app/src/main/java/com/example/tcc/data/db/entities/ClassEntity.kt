package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_table")
data class ClassEntity(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val classId: Long = 0,
    val name: String,
    val courseId: Long
)