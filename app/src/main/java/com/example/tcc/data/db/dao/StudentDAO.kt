package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.StudentEntity

@Dao
interface StudentDAO {

    @Insert
    suspend fun insert(student: StudentEntity): Long

    @Query("SELECT * FROM student_table")
    suspend fun getAll(): List<StudentEntity>

    @Update
    suspend fun update(student: StudentEntity)

    @Query("SELECT id FROM student_table WHERE registrationNumber =:login AND password =:password LIMIT 1")
    suspend fun getStudent(login: String, password: String): Long

    @Query("SELECT * FROM student_table WHERE id =:id LIMIT 1")
    suspend fun getStudentBy(id: Long): StudentEntity

    @Query("SELECT * FROM student_table WHERE classId =:classId")
    suspend fun getStudentsByClassId(classId: Long): List<StudentEntity>

}