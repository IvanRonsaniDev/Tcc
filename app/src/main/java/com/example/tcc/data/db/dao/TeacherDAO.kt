package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.tcc.data.db.entities.TeacherEntity
import com.example.tcc.data.db.entities.TeacherWithDisciplines

@Dao
interface TeacherDAO {

    @Insert
    suspend fun insert(teacher: TeacherEntity): Long

    @Query("SELECT * FROM teacher_table")
    suspend fun getAll(): List<TeacherEntity>

    @Transaction
    @Query("SELECT * FROM teacher_table")
    suspend fun getTeacherWithDisciplines(): List<TeacherWithDisciplines>

    @Update
    suspend fun update(teacher: TeacherEntity)

}