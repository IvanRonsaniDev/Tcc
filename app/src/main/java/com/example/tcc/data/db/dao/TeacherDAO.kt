package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.TeacherEntity

@Dao
interface TeacherDAO {

    @Insert
    suspend fun insert(teacher: TeacherEntity): Long

    @Query("SELECT * FROM teacher_table")
    suspend fun getAll(): List<TeacherEntity>

    @Update
    suspend fun update(teacher: TeacherEntity)

}