package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TeacherWithTeam(
    @Embedded val teacher: TeacherEntity,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "id"
    )
    val team: TeamEntity
)