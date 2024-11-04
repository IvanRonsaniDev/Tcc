package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class DisciplineWithTeachers(
    @Embedded val discipline: DisciplineEntity,
    @Relation(
        parentColumn = "disciplineId",
        entityColumn = "teacherId",
        associateBy = Junction(DisciplineTeacherCrossRef::class)
    )
    val teachers: List<TeacherEntity>
)