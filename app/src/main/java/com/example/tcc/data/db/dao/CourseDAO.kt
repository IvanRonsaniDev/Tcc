package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.CourseEntity

@Dao
interface CourseDAO {

    @Insert
    suspend fun insert(course: CourseEntity): Long

    @Query("SELECT * FROM course_table")
    suspend fun getAll(): List<CourseEntity>

    @Update
    suspend fun update(course: CourseEntity)

    @Query("SELECT * FROM course_table WHERE id =:id LIMIT 1")
    suspend fun getCourseBy(id: Long): CourseEntity

}