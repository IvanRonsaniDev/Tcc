package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.CompetitionEntity

@Dao
interface CompetitionDAO {

    @Insert
    suspend fun insert(competition: CompetitionEntity): Long

    @Query("SELECT * FROM competition_table")
    suspend fun getAll(): List<CompetitionEntity>

    @Update
    suspend fun update(competition: CompetitionEntity)

}