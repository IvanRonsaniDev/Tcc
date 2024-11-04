package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "competition_table")
data class CompetitionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Long = 0
)