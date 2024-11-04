package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discipline_table")
data class DisciplineEntity(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val disciplineId: Long = 0,
    val name: String,
    val description: String
)