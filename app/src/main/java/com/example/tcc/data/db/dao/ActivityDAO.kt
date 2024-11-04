package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.ActivityEntity

@Dao
interface ActivityDAO {

    @Insert
    suspend fun insert(activityEntity: ActivityEntity): Long

    @Query("SELECT * FROM activity_table")
    suspend fun getAll(): List<ActivityEntity>

    @Update
    suspend fun update(activityEntity: ActivityEntity)


}