package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TeacherWithClasses(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "teacherId",
        entityColumn = "classId",
        associateBy = Junction(ClassTeacherCrossRef::class)
    )
    val classes: List<ClassEntity>
)