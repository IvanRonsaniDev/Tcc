package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val studentId: Long,
    @ColumnInfo
    val activityId: Long,
    @ColumnInfo
    val note: Double = 0.0
)