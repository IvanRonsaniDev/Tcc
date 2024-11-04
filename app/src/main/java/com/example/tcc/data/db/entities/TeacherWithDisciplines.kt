package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TeacherWithDisciplines(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "teacherId",
        entityColumn = "disciplineId",
        associateBy = Junction(DisciplineTeacherCrossRef::class)
    )
    val disciplines: List<DisciplineEntity>
)