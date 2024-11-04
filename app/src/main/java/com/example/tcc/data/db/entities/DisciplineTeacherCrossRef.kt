package com.example.tcc.data.db.entities

import androidx.room.Entity

@Entity(tableName = "discipline_teacher_cross_ref_table", primaryKeys = ["disciplineId", "teacherId"])
data class DisciplineTeacherCrossRef(
    val disciplineId: Long,
    val teacherId: Long
)