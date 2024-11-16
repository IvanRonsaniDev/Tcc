package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.ClassEntity

@Dao
interface ClassDAO {

    @Insert
    suspend fun insert(classEntity: ClassEntity): Long

    @Query("SELECT * FROM class_table")
    suspend fun getAll(): List<ClassEntity>

    @Update
    suspend fun update(classEntity: ClassEntity)

    @Query("SELECT * FROM class_table WHERE classId =:id LIMIT 1")
    suspend fun getClassBy(id: Long): ClassEntity

}