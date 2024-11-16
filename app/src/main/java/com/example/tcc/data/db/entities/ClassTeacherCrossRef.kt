package com.example.tcc.data.db.entities

import androidx.room.Entity

@Entity(tableName = "class_teacher_cross_ref_table", primaryKeys = ["classId", "teacherId"])
data class ClassTeacherCrossRef(
    val classId: Long,
    val teacherId: Long
)