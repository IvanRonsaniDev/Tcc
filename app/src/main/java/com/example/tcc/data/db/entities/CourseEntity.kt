package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class CourseEntity(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val teamId: Long
)