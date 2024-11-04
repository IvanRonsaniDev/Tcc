package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class Team(
    @Embedded val team: TeamEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val goals: List<GoalEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val teachers: List<TeacherEntity>,
    @Relation(
        parentColumn = "",
        entityColumn = ""
    )
    val students: List<StudentEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val courses: List<CourseEntity>
)