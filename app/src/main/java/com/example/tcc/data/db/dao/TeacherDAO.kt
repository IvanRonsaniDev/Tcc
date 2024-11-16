package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.tcc.data.db.entities.ClassTeacherCrossRef
import com.example.tcc.data.db.entities.TeacherEntity
import com.example.tcc.data.db.entities.TeacherWithClasses
import com.example.tcc.data.db.entities.TeacherWithDisciplines
import com.example.tcc.data.db.entities.TeacherWithTeam

@Dao
interface TeacherDAO {

    @Insert
    suspend fun insert(teacher: TeacherEntity): Long

    @Insert
    suspend fun insert(classTeacher: ClassTeacherCrossRef)

    @Query("SELECT * FROM teacher_table")
    suspend fun getAll(): List<TeacherEntity>

    @Update
    suspend fun update(teacher: TeacherEntity)

    @Query("SELECT teacherId FROM teacher_table WHERE socialNumber =:login AND password =:password LIMIT 1")
    suspend fun getTeacher(login: String, password: String): Long

    @Query("SELECT * FROM teacher_table WHERE teacherId =:id LIMIT 1")
    suspend fun getTeacherBy(id: Long): TeacherEntity

    @Query("SELECT * FROM teacher_table WHERE teamId =:teamId LIMIT 1")
    suspend fun getTeacherByTeamId(teamId: Long): TeacherEntity

    @Query("SELECT * FROM teacher_table WHERE teacherId =:id LIMIT 1")
    suspend fun getTeacherWithTeamById(id: Long): TeacherWithTeam

    @Transaction
    @Query("SELECT * FROM teacher_table WHERE teacherId =:teacherId LIMIT 1")
    suspend fun getTeacherWithDisciplines(teacherId: Long): TeacherWithDisciplines

    @Transaction
    @Query("SELECT * FROM teacher_table WHERE teacherId =:teacherId LIMIT 1")
    suspend fun getTeacherWithClasses(teacherId: Long): TeacherWithClasses

}