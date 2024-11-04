package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ClassAndStudents(
    @Embedded val `class`: ClassEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "classId"
    )
    val students: List<StudentEntity>
)