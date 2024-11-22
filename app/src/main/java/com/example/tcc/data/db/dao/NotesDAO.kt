package com.example.tcc.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tcc.data.db.entities.NoteEntity

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity): Long

    @Update
    suspend fun update(note: NoteEntity)

    @Query("SELECT * FROM note_table WHERE studentId = :studentId AND activityId = :activityId LIMIT 1")
    suspend fun getNoteBy(studentId: Long, activityId: Long): NoteEntity?

}