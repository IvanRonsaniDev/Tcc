package com.example.tcc.data.repository



    import androidx.lifecycle.LiveData
    import com.example.tcc.data.db.dao.GoalDAO
    import com.example.tcc.data.db.entities.GoalEntity

    class GoalRepository(private val goalDAO: GoalDAO) {

        // Função para recuperar as metas de uma equipe
        fun getGoalsForTeam(teamId: Long): LiveData<List<GoalEntity>> {
            return goalDAO.getGoalsForTeam(teamId.toInt())
        }

        // Função para adicionar uma meta
        suspend fun addGoal(goal: GoalEntity) {
            goalDAO.insert(goal)
        }

        // Função para atualizar o progresso de uma meta
        suspend fun updateProgress(goalId: Long, quantity: Int) {
            goalDAO.updateProgress(goalId, quantity)
        }
    }

