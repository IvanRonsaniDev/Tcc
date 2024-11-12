package com.example.tcc.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.repository.GoalRepository
import kotlinx.coroutines.launch

class GoalViewModel(private val goalRepository: GoalRepository) : ViewModel() {

    // Função para recuperar as metas de uma equipe
    fun getGoalsForTeam(teamId: Long): LiveData<List<GoalEntity>> {
        return goalRepository.getGoalsForTeam(teamId)
    }

    // Função para adicionar uma nova meta
    fun addGoal(goal: GoalEntity) {
        viewModelScope.launch {
            goalRepository.addGoal(goal)
        }
    }

    // Função para atualizar o progresso de uma meta
    fun updateGoalProgress(goalId: Long, progress: Int) {
        viewModelScope.launch {
            goalRepository.updateProgress(goalId, progress)
        }
    }
}
