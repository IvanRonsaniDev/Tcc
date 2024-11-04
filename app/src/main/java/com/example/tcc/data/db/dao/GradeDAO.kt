package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.GradeEntity

@Dao
interface GradeDAO {

    @Insert
    suspend fun insert(grade: GradeEntity): Long

    @Query("SELECT * FROM grade_table")
    suspend fun getAll(): List<GradeEntity>

    @Update
    suspend fun update(grade: GradeEntity)

}