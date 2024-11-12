package com.example.tcc.data.db.dao

import androidx.lifecycle.LiveData
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

    @Update
    suspend fun updateProgress(goal: GoalEntity)

    @Query("UPDATE goal_table SET quantityAchieved = :progress WHERE id = :goalId")
    suspend fun updateProgress(goalId: Long, progress: Int)

    @Query("SELECT * FROM goal_table WHERE teamId = :teamId")
    suspend fun getGoalsForTeam(teamId: Long): List<GoalEntity>

}