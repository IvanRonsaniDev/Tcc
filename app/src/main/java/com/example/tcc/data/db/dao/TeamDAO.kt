package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.TeamEntity

@Dao
interface TeamDAO {

    @Insert
    suspend fun insert(team: TeamEntity): Long

    @Query("SELECT * FROM team_table ORDER BY points DESC")
    suspend fun getAll(): List<TeamEntity>

    @Update
    suspend fun update(team: TeamEntity)

    @Query("SELECT * FROM team_table WHERE id =:id LIMIT 1")
    suspend fun getTeamBy(id: Long): TeamEntity

}