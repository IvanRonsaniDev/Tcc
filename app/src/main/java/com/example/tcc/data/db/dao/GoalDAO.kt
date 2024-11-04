package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.GoalEntity

@Dao
interface GoalDAO {

    @Insert
    suspend fun insert(goal: GoalEntity): Long

    @Query("SELECT * FROM goal_table")
    suspend fun getAll(): List<GoalEntity>

    @Update
    suspend fun update(goal: GoalEntity)

}