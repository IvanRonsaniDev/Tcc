package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import java.util.Date

@Dao
interface ActivityDAO {

    @Insert
    suspend fun insert(activityEntity: ActivityEntity): Long

    @Query("SELECT * FROM activity_table")
    suspend fun getAll(): List<ActivityEntity>

    @Query("SELECT * FROM activity_table WHERE date= :date")
    suspend fun getAll(date: Date): List<ActivityEntity>

    @Update
    suspend fun update(activityEntity: ActivityEntity)

    @Query("SELECT * FROM activity_table WHERE classId = :classId AND disciplineId = :disciplineId AND isEvaluative = 1")
    suspend fun getEvaluativeActivitiesByClassAndDiscipline(
        classId: Long,
        disciplineId: Long
    ): List<ActivityEntity>

    @Query("SELECT * FROM activity_table WHERE classId = :classId AND date = :date")
    suspend fun getActivitiesByClassIdAndDate(classId: Long, date: Date): List<ActivityEntity>

    @Query("SELECT * FROM activity_table WHERE disciplineId = :disciplineId AND date = :date")
    suspend fun getActivitiesByDisciplineIdAndDate(disciplineId: Long, date: Date): List<ActivityEntity>

}