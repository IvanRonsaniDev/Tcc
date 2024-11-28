package com.example.tcc.data.repository

import com.example.tcc.AppApplication
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.dao.GoalDAO
import com.example.tcc.data.db.entities.GoalEntity

class GoalRepository(
    private val goalDAO: GoalDAO = AppDataBase.getInstance(AppApplication.getInstance()).goalDAO()
) {

    // Função para recuperar as metas de uma equipe
    suspend fun getGoalsForTeam(teamId: Long): List<GoalEntity> {
        return goalDAO.getGoalsForTeam(teamId)
    }


    // Função para adicionar uma meta
    suspend fun addGoal(goal: GoalEntity) {
        goalDAO.insert(goal)
    }

    suspend fun updateGoal(goal: GoalEntity) {
        goalDAO.update(goal)
    }

    // Função para atualizar o progresso de uma meta
    suspend fun updateProgress(goalId: Long, quantity: Int) {
        goalDAO.updateProgress(goalId, quantity)
    }
    //excluir meta
    suspend fun deleteGoal(goal: GoalEntity) {
        goalDAO.delete(goal)
    }

}