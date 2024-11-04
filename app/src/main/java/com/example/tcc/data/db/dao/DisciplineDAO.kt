package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.data.db.entities.DisciplineTeacherCrossRef
import com.example.tcc.data.db.entities.DisciplineWithTeachers

@Dao
interface DisciplineDAO {

    @Insert
    suspend fun insert(discipline: DisciplineEntity): Long

    @Insert
    suspend fun insert(disciplineTeacher: DisciplineTeacherCrossRef)

    @Query("SELECT * FROM discipline_table")
    suspend fun getAll(): List<DisciplineEntity>

    @Transaction
    @Query("SELECT * FROM discipline_table")
    suspend fun getDisciplineWithTeachers(): List<DisciplineWithTeachers>

    @Update
    suspend fun update(discipline: DisciplineEntity)

}