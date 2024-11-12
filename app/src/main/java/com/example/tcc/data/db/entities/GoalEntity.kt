package com.example.tcc.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goal_table")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Long = 0,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    val quantityAchieved: Double,
    @ColumnInfo
    val totalQuantity: Double,
    @ColumnInfo
    val teamId: Long,
    @ColumnInfo
    val name: String
)