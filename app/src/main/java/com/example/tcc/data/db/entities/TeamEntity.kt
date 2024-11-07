package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team_table")
data class TeamEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Long = 0,
    @ColumnInfo
    val points: Double = 0.0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val competitionId: Long
)