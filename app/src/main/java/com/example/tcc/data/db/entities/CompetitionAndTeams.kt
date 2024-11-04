package com.example.tcc.data.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class CompetitionAndTeams(
    @Embedded val competition: CompetitionEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "competitionId"
    )
    val teams: List<TeamEntity>
)